import java.io.*;
import java.io.IOException;

public class argtest {
    public static void main(String[] args) {
        /* 
        if (args.length == 0) {
            System.out.println("ERROR: Write hostname;" +"\n"+ "example: java app 10.0.1.1");
            
        }
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        */

        String WhatToRun = "pwd";
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(WhatToRun);
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue:" + exitVal);
        } catch (Exception e) {
            {
                e.printStackTrace();
            }
        }
    }
    
}
