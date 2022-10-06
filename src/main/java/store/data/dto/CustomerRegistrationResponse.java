package store.data.dto;

import lombok.Data;

@Data
public class CustomerRegistrationResponse {
    private String message;
    private int StatusCode;
    private int userId;
}
