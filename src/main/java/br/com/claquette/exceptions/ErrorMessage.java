package br.com.claquette.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private Integer status;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private String message;
    private List<String> errorMessages;

    public ErrorMessage(ArrayList<String> errorMessages, Integer status) {
        this.status = status;
        this.errorMessages = errorMessages;
    }

    public ErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
