package parser;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Parse {
    public ArrayList<String> lines;
    public int colon_count;

    public void parseFileV2(File file) {
        lines = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        try {
            System.out.println("Parsing the file...");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String modified_temp = null;
            while ((line = reader.readLine()) != null) {
                String[] tempArray = line.split(":");
                if (temp.length() != 0 && containString(tempArray[0])) {
                    modified_temp = temp.toString().replace(":", ";");
                    this.lines.add(modified_temp);
                    temp.setLength(0);
                }
                temp.append(line + " ");
            }
            if (checkFormat(temp)) {
                this.lines.add(temp.toString().replace(":", ";"));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Checks to see if a line contains a substring, determines if the line is at
    // the beginning of a new entry.
    // This is necessary as the AGE log file lines sometimes have entries mid-way
    // through on new lines.
    public boolean containString(String word) {
        String[] queues = new String[] { "debug", "gpu", "gpu-long", "hpc", "hpc-debug", "largemem", "long" };
        for (int i = 0; i < queues.length; i++) {
            if (queues[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    // Will make sure the last entry is properly formated before inserted into csv
    public boolean checkFormat(StringBuilder entry) {
        return true;
    }

    public int getElementCount() {
        return this.lines.size();
    }

    public int getMinimumSemiColonCount() {
        int location = 4;

        for (int i = 4; i < lines.size(); i++) {
            int colons = 0;
            String[] line = lines.get(i).split("");

            for (int j = 0; j < line.length; j++) {
                if (line[j].equals(";")) {
                    colons++;
                }
            }
            if (colons < this.colon_count) {
                location = i;
                this.colon_count = colons;
            }
        }
        System.out.println("line found at: " + location);
        System.out.println(lines.get(location));
        return this.colon_count;
    }

}
