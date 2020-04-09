package com.ex.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Magic8BallConfig {
    private String answersPath = "";
    private String questionPrompt = "";
    private String questAcknowledgeMsg = "";

    public Magic8BallConfig(String configPath) throws FileNotFoundException {
        FileReader fr = new FileReader(configPath);
        BufferedReader br = new BufferedReader(fr);

        try {
            String configLine;
            while ((configLine = br.readLine()) != null) {
                String[] splits = configLine.split("=", 2);
                if (splits.length != 2)
                    System.err.println("WARNING: Unrecognized configuration option " + configLine);

                switch (splits[0]) {
                    case "questionPrompt":
                        questionPrompt = splits[1];
                        break;
                    case "questAcknowledgeMsg":
                        questAcknowledgeMsg = splits[1];
                        break;
                    case "answersFilePath":
                        answersPath = splits[1];
                        break;
                    default:
                        System.err.println("WARNING: Unrecognized configuration option " + splits[0]);
                }
            }
        }
        catch (IOException e) {
            System.err.println("ERROR: Error encountered while reading configuration file!");
        }
    }

    public String getAnswersPath() {
        return answersPath;
    }

    public String getQuestionPrompt() {
        return questionPrompt;
    }

    public String getQuestAcknowledgeMsg() {
        return questAcknowledgeMsg;
    }
}
