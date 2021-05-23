package com.deltaApps.assessories.windows.Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRunner {

    private String commandLineHeader = "cmd /c ";
    private String command;
    private String standardOut;
    private String standardError;

    public CommandRunner(){

    }

    public String[] executeCMD2() throws IOException{
        String[] stdValues = {};

        executeCMD();
        stdValues[0] = this.getStandardOut();
        stdValues[1] = this.getStandardError();

        return stdValues;
    }

    public void executeCMD(){
        try {
            runCMD();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runCMD() throws IOException{
        String commandLine;
        commandLine = commandLineHeader + command;
        Process process = Runtime.getRuntime().exec(commandLine);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

//        System.out.println("Standard output: ");
        while ((standardOut = stdInput.readLine()) != null)

            // Read command errors
//        System.out.println("Standard error: ");
            while ((standardError = stdError.readLine()) != null);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getStandardOut() {
        return standardOut;
    }

    public String getStandardError() {
        return standardError;
    }
}
