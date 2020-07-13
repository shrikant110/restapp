package com.sbs.vc.config.util.shellscript;

import java.io.IOException;
import java.util.concurrent.Executors;


public class ScriptExecuter {
       
        public static int executeShellCommand(String fileName) throws InterruptedException, IOException {
                ProcessBuilder builder = new ProcessBuilder("/home/ec2-user/aurora/downloadscript/stock/"+fileName);
                Process process = builder.start();
                StreamGobbler streamGobbler =  new StreamGobbler(process.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);
                int exitCode = process.waitFor();
                System.out.println("---->"+exitCode);
                process.destroy();
                return exitCode;
              
        }

        public static void main(String[] v) throws InterruptedException, IOException {

                executeShellCommand("script_download_02032019.sh1");
                
        }
}