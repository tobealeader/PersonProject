package jsp.com.entry;

/**
 * Created by lenovo on 2016/10/14.
 */
public class MessageAndStatus {
    private String message;

    public MessageAndStatus(String message){
        super();
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
