package message;

public class SenseBodyMessage extends Message {

    /*
    (
        sense_body 0 
        (view_mode high normal) 
        (stamina 8000 1) 
        (speed 0) 
        (kick 0) 
        (dash 0) 
        (turn 0) 
        (say 0)
    )
    */

    public SenseBodyMessage(int turn) {
        super(turn, "SENSE_BODY_MESSAGE");
    }
    
}
