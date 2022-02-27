package message;

public class SeeMessage extends Message {

    public String   ball;
    public String[] flags;
    public String[] players;

    public SeeMessage(int turn, String ball, String[] flags, String[] players) {
        
        super(turn, "SEE_MESSAGE");
        
        this.ball    = ball;
        this.flags   = flags;
        this.players = players;
    }
    
}
