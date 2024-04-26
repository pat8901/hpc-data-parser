package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import writer.*;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;

public class Database {

    public Boolean checkForSqlite() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("/bin/bash", "-c", "which sqlite3");
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                if (output.length() != 0) {
                    // System.out.println(output);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Calls bash script to convert and organize csv file into sqlite database
    public void callBashScript(String path) throws IOException {
        System.out.println("Running bash script ...");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder
                    .command(
                            "/bin/bash", "-c",
                            path + "/lib/make_db.sh");
            processBuilder.start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Bash script complete.");
    }

    // Not used:
    public Connection createTable() throws SQLException {
        Connection conn = null;
        // String header =
        // "qname;hostname;group;owner;jobname;jobnumber;account;priority;qsub_time;start_time;end_time;failed;exit_status;ru_wallclock;ru_utime;ru_stime;ru_maxrss;ru_ixrss;ru_ismrss;ru_idrss;ru_isrss;ru_minflt;ru_majflt;ru_nswap;ru_inblock;ru_oublock;ru_msgnd;ru_msgrcv;ru_nsignals;ru_nvcsw;ru_nivcsw;project;department;granted_pe;slots;taskid;cpu;mem;io;?1;iow;pe_taskid;?2;?3;?4;?5;deleted_by;?6;?7;submit_host;cwd;submit_cmd;wallclock;ioops;bound_cores;resource_map;devices;gpus;gpu_usage;failcnt;memsw.failcnt;max_usage_in_bytes;memsw.max_usage_in_bytes;max_cgroups_memory;holt_jid;?8;?9;?10;hard_resources;soft_resources;hard_queues;soft_queues;granted_req.;exec_host_list";
        String url = "jdbc:sqlite:" + "/path/to/database";

        // create a connection to the database
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");

        String command = "CREATE TABLE IF NOT EXISTS \"GELOG\"(\n" + "qname TEXT, "
                + "hostname TEXT, " + "\"group\" TEXT, " + "owner TEXT, " + "jobname TEXT, " + "jobnumber TEXT, "
                + "account TEXT, " + "priority TEXT, " + "qsub_time TEXT, " + "start_time TEXT, " + "end_time TEXT, "
                + "failed TEXT, " + "exit_status TEXT, " + "ru_wallclock TEXT, " + "ru_utime TEXT, " + "ru_stime TEXT, "
                + "ru_maxrss TEXT, " + "ru_ixrss TEXT, " + "ru_ismrss TEXT, " + "ru_idrss TEXT, "
                + "ru_isrss TEXT, " + "ru_minflt TEXT, " + "ru_majflt TEXT, " + "ru_nswap TEXT, " + "ru_inblock TEXT, "
                + "ru_oublock TEXT, "
                + "ru_msgnd TEXT, " + "ru_msgrcv TEXT, " + "ru_nsignals TEXT, " + "ru_nvcsw TEXT, " + "ru_nivcsw TEXT, "
                + "project TEXT, " + "department TEXT, " + "granted_pe TEXT, " + "slots TEXT, " + "taskid TEXT, "
                + "cpu TEXT, " + "mem TEXT, " + "io TEXT, " + "\"?1\" TEXT, " + "iow TEXT, "
                + "pe_taskid TEXT, " + "\"?2\" TEXT, " + "\"?3\" TEXT, " + "\"?4\" TEXT, " + "\"?5\" TEXT, "
                + "deleted_by TEXT, " + "\"?6\" TEXT, " + "\"?7\" TEXT, " + "submit_host TEXT, " + "cwd TEXT, "
                + "submit_cmd TEXT, "
                + "wallclock TEXT, " + "ioops TEXT, " + "bound_cores TEXT, " + "resource_map TEXT, " + "devices TEXT, "
                + "gpus TEXT, " + "gpu_usage TEXT, "
                + "failcnt TEXT, " + "\"memsw.failcnt\" TEXT, " + "max_usage_in_bytes TEXT, "
                + "\"memsw.max_usage_in_bytes\" TEXT, " + "max_cgroups_memory TEXT, " + "holt_jid TEXT, "
                + "\"?8\" TEXT, "
                + "\"?9\" TEXT, " + "\"?10\" TEXT, " + "hard_resources TEXT, " + "soft_resources TEXT, "
                + "hard_queues TEXT, "
                + "soft_queues TEXT, " + "\"granted_req.\" TEXT, " + "exec_host_list TEXT)";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(command);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Not used:
    public void populateTable(Connection conn, ArrayList<String> lines) throws SQLException {
        // StringBuilder s = new StringBuilder();
        // ArrayList<String> entries = new ArrayList<>();

        // for (int i = 0; i < lines.size(); i++) {
        // // s.append("insert into GELOG values(");
        // String substring = lines.get(i).replace(";", ", ");
        // entries.add(substring);
        // }

        // for (int i = 0; i < entries.size(); i++) {
        // System.out.println(entries.get(i));
        // }

        // for (int j = 0; j < entry.length - 1; j++) {
        // s.append("\'" + entry[j] + "\', ");
        // }
        // s.append("\'" + entry[entry.length - 1] + "\')");

        // try (Statement stmt = conn.createStatement()) {
        // // System.out.print("Lines: " + i);
        // stmt.executeUpdate(s.toString());
        // s.setLength(0);
        // } catch (SQLException e) {
        // e.printStackTrace();
        // return;
        // }
        // }
    }

}
