package src.message;

public class InitMessage extends Message {
    
    public char   side;
    public int    num;
    public String play_mode;

    public InitMessage(char side, int num, String play_mode) {
        
        super(0, "INIT_MESSAGE");

        this.side      = side;
        this.num    = num;
        this.play_mode = play_mode;
    }

}
