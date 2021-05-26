package com.deltaApps.assessories.windows.linkcreator;

import com.deltaApps.assessories.windows.Terminal.CommandRunner;

public class LinkCreator {

    CommandRunner commandRunner = new CommandRunner();

    public LinkCreator(String link ,String linkFolderName, char param , String target){
        this("mklink \"" + link + "\\" + linkFolderName + "\" /" + param + " \"" + target +"\"");
    }

    public LinkCreator(String CMD){
        commandRunner.setCommand(CMD);
        commandRunner.executeCMD();

    }

}
