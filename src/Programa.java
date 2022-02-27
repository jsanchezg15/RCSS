package src;

import src.team.*;
import src.message.*;
import src.parser.*;

public class Programa {
    
    public static void main(String[] args) {

        System.out.println("Start");

        Team t1 = new Team("JorgeSanchez");
        Team t2 = new Team("Valero");

        t1.start();
        t2.start();

        /*
        Player p = new Player("TeamJorge");
        p.start();
        */

        
        String str = "(see 272 ((flag r b)          26.6   31   -0     0.2) ((ball)              24.5   33   -0     0.2) ((player Jorge 1)    16.4   22    0     0.2) ((player Jorge 3)    12.2   17    0     0.1) ((player Jorge 4)    16.4   28   -0     0.3) ((player Jorge 5)    24.5   24   -0     0.2) ((player Jorge 6)    12.2   16   -0     0.1) ((player Jorge)      24.5   29             ) ((player Jorge 8)    22.2   27    0     0  ) ((player Jorge 9)    18.2   17   -0     0.1) ((player Jorge 10)   14.9   18   -0.298 0.2) ((player Jorge 11)   22.2   21    0     0.3) ((player Valero 1)   16.4   26   -0     0.2) ((player Valero 2)   16.4   43   -0     0.4) ((player Valero 3)   16.4   21   -0     0.2) ((player Valero 4)   18.2   13    0     0.2) ((player Valero 5)   16.4   24   -0     0.2) ((player Valero 6)   18.2   19   -0     0.2) ((player Valero 7)   14.9   20   -0.298 0.2) ((player Valero 8)   14.9   21    0     0.2) ((player Valero 9)   20.1   14   -0     0.1) ((player Valero 10)  18.2   17   -0     0.1) ((player Valero 11)  16.4   22   -0     0.2) ((line r) 7.7 42))";
    
        long time1 = System.currentTimeMillis();
        
        Message msg = Parser.parse(str);
        
        SeeMessage seeMsg = (SeeMessage) msg;
        
        String[] players = seeMsg.players;
        String[] flags   = seeMsg.flags;
        
        System.out.println(seeMsg.type);
        System.out.println(seeMsg.turn);
        System.out.println(seeMsg.ball);

        for(int i = 0; i < players.length; i++)
        	System.out.println(players[i]);
        
        for(int i = 0; i < flags.length; i++)
        	System.out.println(flags[i]);
        
        long time2 = System.currentTimeMillis();

        System.out.println(time2 - time1);
        
    }

}
