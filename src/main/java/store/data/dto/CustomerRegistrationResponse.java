package store.data.dto;

import lombok.Data;

@Data
public class CustomerRegistrationResponse {
    private String message;
    private int statusCode;
    private int userId;
@Override
    public String toString(){
        return String.format(
                "{\n\tuserId:%s.\n\tmessage:%s\n\tstatusCode:%d", userId, message, statusCode


             );
    }
}