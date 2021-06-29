package com.example.planetmanagement.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    protected static final boolean ERROR = true;
    protected static final boolean SUCCESS = false;

    private T data;
    private List<String> errorMessages;
    private boolean isError;
    private String message;

    public Response(List<String> errorMessages) {
        this.isError = ERROR;
        this.errorMessages = errorMessages;
    }

    public Response(T data) {
        this.isError = SUCCESS;
        this.data = data;
    }

    public Response(String message) {
        this.isError = SUCCESS;
        this.message = message;
    }

}
