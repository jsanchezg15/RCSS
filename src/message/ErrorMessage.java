package src.message;

public class ErrorMessage extends Message {

    public ErrorMessage(String error) {
        super(0, "ERROR_MESSAGE");
        System.out.println("ERROR_MESSAGE: " + error);
    }
    
}
