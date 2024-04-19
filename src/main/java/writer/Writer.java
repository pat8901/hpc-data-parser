package writer;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public void replaceHeader(ArrayList<String> lines) {
        try {
            System.out.println("Replacing headers...");
            String header = "qname;hostname;group;owner;jobname;jobnumber;account;priority;qsub_time;start_time;end_time;failed;exit_status;ru_wallclock;ru_utime;ru_stime;ru_maxrss;ru_ixrss;ru_ismrss;ru_idrss;ru_isrss;ru_minflt;ru_majflt;ru_nswap;ru_inblock;ru_oublock;ru_msgnd;ru_msgrcv;ru_nsignals;ru_nvcsw;ru_nivcsw;project;department;granted_pe;slots;taskid;cpu;mem;io;?1;iow;pe_taskid;?2;?3;?4;?5;deleted_by;?6;?7;submit_host;cwd;submit_cmd;wallclock;ioops;bound_cores;resource_map;devices;gpus;gpu_usage;failcnt;memsw.failcnt;max_usage_in_bytes;memsw.max_usage_in_bytes;max_cgroups_memory;holt_jid;?8;?9;?10;hard_resources;soft_resources;hard_queues;soft_queues;granted_req.;exec_host_list";
            lines.remove(0);
            lines.add(0, header);
        } catch (Exception e) {
            System.out.println("Headers could not be replaced!");
            e.printStackTrace();
        }
    }

    public void createFile(String path) {
        try {
            System.out.println("Creating file...");
            File newFile = new File(path);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile(String path, ArrayList<String> lines) {
        try {
            System.out.println("Writing to file...");
            FileWriter myWriter = new FileWriter(path);
            for (int i = 0; i < lines.size(); i++) {
                myWriter.write(lines.get(i) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printTest(ArrayList<String> lines) {
        // for (int i = 0; i < 10; i++) {
        // System.out.println("line: " + i + " " + lines.get(i) + "\n");
        // }
        System.out.println("first: " + lines.get(0));
        System.out.println("last: " + lines.get(lines.size() - 1));
    }

}
