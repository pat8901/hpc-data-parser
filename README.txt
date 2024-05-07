Grid Engine Log Analyzer

Overview:

This program is designed to analyze Grid Engine log files and convert them into a format suitable for time series analysis using Grafana. 
It consists of a Java program for log file parsing and CSV creation, followed by a bash script to convert the CSV into a SQLite database.

Features:

    Reads Grid Engine log files and extracts relevant data.
    Converts log file data into a CSV format.
    Uses a bash script to create a SQLite database from the CSV file.
    Generates binned data tables for use in time series analysis.

Installation: (This program only runs on Unix systems. Only been tested on Ubuntu.)

    Ensure Java (JDK 11 or higher) is installed
    Clone the repository: git clone https://github.com/yourusername/grid-engine-log-analyzer.git
    Compile the Java program using the gradle wrapper: ./gradlew build
    Ensure SQLite is installed on your system for the bash script to work.

Usage:

    Ensure that ge-log-analyzer-1.0.0.jar, make_db.sh, and ge_log_file are all in the same directory. (This limitation will be fixed in a later version release)

    For example:
        john@john-pc:~/Desktop/grid_engine_program$ ls
        ge-log-analyzer-1.0.0.jar  make_db.sh  source_log.txt

    Run the Java program: java -jar ge-log-analyzer-1.0.0.jar
    Enter the path to the Grid Engine log file when prompted.
    The program will create a CSV file with the reformatted data. 
    Using the newly created CSV file the program will automatically call the bash script to create a SQLite database with binned data tables.

Requirements:

    Java (JDK 11 or higher)
    SQLite
    Access to a Grafana server for visualization (optional)

Example: (Bash)

$ java -jar ge-log-analyzer-1.0.0
Enter the path to the Grid Engine log file: /path/to/grid_engine.log

License:

This project is licensed under the MIT License - see the LICENSE file for details.
