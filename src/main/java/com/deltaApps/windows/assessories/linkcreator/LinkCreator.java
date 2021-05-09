package com.deltaApps.windows.assessories.linkcreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkCreator {

    public LinkCreator(String CMD){
        try {
            Process process = Runtime.getRuntime().exec("cmd /c dir");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;
            System.out.println("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Read command errors
            System.out.println("Standard error: ");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
