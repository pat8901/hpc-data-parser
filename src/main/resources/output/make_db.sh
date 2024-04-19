#!/bin/bash
CWD=$(echo "$PWD")

/usr/bin/sqlite3 $CWD/garbage.db <<'END_SQL'
.separator ";"
.import output_full.csv gelog
create table qsub_table as SELECT qsub_time/1800000*1800000 AS bin, COUNT(*) as count from GELOG GROUP by bin;
create table start_table as SELECT start_time/1800000*1800000 AS bin, COUNT(*) as count from GELOG GROUP by bin;
create table end_table as SELECT end_time/1800000*1800000 AS bin, COUNT(*) as count from GELOG GROUP by bin;
create table qsub_table_normalized as SELECT datetime(bin/1000, 'unixepoch', 'localtime'), count from qsub_table;
create table start_table_normalized as SELECT datetime(bin/1000, 'unixepoch', 'localtime'), count from start_table;
create table end_table_normalized as SELECT datetime(bin/1000, 'unixepoch', 'localtime'), count from end_table;
DELETE FROM qsub_table_normalized WHERE "datetime(bin/1000, 'unixepoch', 'localtime')" = "1969-12-31 19:00:00";
DELETE FROM start_table_normalized WHERE "datetime(bin/1000, 'unixepoch', 'localtime')" = "1969-12-31 19:00:00";
DELETE FROM end_table_normalized WHERE "datetime(bin/1000, 'unixepoch', 'localtime')" = "1969-12-31 19:00:00";
END_SQL