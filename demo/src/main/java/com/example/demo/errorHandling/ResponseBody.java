package com.example.demo.errorHandling;

import com.example.demo.Domain.Employee;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.Objects;

public class ResponseBody {

    private int responseStatus;
    private HttpStatus responseMessage;
    private String errorMessage;

    private List<?> data;


    public ResponseBody() {
    }

    public ResponseBody(int responseStatus, HttpStatus responseMessage, String errorMessage, List<?> data) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getResponseStatus() {

        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(HttpStatus responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
