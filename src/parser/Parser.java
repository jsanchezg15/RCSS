package src.parser;

import src.message.*;

public class Parser {

    public static Message parse(String msg) {

        if(msg.startsWith("(init")) 
            return parseInitMessage(msg);

        if(msg.startsWith("(see")) 
            return parseSeeMessage(msg);

        if(msg.startsWith("(sense_body")) 
            return parseSenseBodyMessage(msg);

        return new ErrorMessage(msg);
    }

    private static InitMessage parseInitMessage(String msg) {
        
        String[] words = msg.split(" ");

		char   side     = words[1].charAt(0);
		int    num      = Integer.parseInt(words[2]);
        String playMode = words[3].replace(")", "");

        return new InitMessage(side, num, playMode);
    }

    private static SeeMessage parseSeeMessage(String msg) {

        String[] sub  = msg.split("[(][(]");
        String[] sub1 = new String[sub.length -1];

        int flagsNum   = 0;
        int playersNum = 0;

        for(int i = 1; i < sub.length; i++) {
            sub1[i - 1] = "((" + sub[i];

            if(sub[i].startsWith("flag")) 
                flagsNum++;
            
            else if(sub[i].startsWith("player"))
                playersNum++;

        }

        int      turn    = Integer.parseInt(sub[0].split(" ")[1]);
        String   ball    = "NOT_FOUND";
        String[] flags   = new String[flagsNum];
        String[] players = new String[playersNum];

        for(int i = 1; i < sub.length; i++) {
            if(sub[i].startsWith("flag")) 
                flags[--flagsNum] = sub[i];
            
            else if(sub[i].startsWith("player")) 
                players[--playersNum] = sub[i];
            
            else if(sub[i].startsWith("ball"))
                ball = sub[i];
        }

        return new SeeMessage(turn, ball, flags, players);        
    }

    private static SenseBodyMessage parseSenseBodyMessage(String msg) {
        return new SenseBodyMessage(0);
    }

}