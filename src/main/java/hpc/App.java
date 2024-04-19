package hpc;

import prompt.*;
import parser.*;
import writer.*;
import tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
//import java.sql.Connection;
import java.sql.SQLException;
import database.Database;

public class App {

    public String getGreeting() {
        return "Starting Program...";
    }

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println(new App().getGreeting());
        String jarPath = "";
        try {
            jarPath = App.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            System.out.println("JAR Path : " + jarPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Prompt prompt = new Prompt();
        Tools tools = new Tools();
        Parse parse = new Parse();
        Writer writer = new Writer();
        Database database = new Database();

        Boolean haveSqlite = database.checkForSqlite();
        if (!haveSqlite) {
            System.out.println("Please install SQLite3 to run this program.");
            System.exit(0);
        }
        String source_path = prompt.startPrompt();
        Boolean pathExist = tools.checkPath(source_path);

        if (pathExist) {
            File source_file = new File(source_path);
            String[] jarDir = jarPath.split("/hpc-data-parser.jar");
            String csv_save_path = jarDir[0] + "/output_full.csv";

            parse.parseFileV2(source_file);
            writer.replaceHeader(parse.lines);
            writer.createFile(csv_save_path);
            writer.writeFile(csv_save_path, parse.lines);
            database.callBashScript(jarDir[0]);

        } else {
            System.out.println("Path does not exist!");
        }
        System.out.println("Shutting down...");
    }

}
