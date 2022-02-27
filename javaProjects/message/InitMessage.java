package message;

public class InitMessage extends Message {
    
    public char   SIDE;
    public int    NUMBER;
    public String PLAY_MODE;

    public InitMessage(char side, int num, String playMode) {
        
        super(0, "INIT_MESSAGE");

        this.SIDE      = side;
        this.NUMBER    = num;
        this.PLAY_MODE = playMode;
    }

}
