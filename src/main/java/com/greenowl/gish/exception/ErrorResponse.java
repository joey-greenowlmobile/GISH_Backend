package com.greenowl.gish.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

/**
 * Created by Ahmed on 2015-05-28.
 * simple POJO representation of a Server Error Response.
 */
public class ErrorResponse {

    private Long timestamp;

    private Integer status;

    private String error;

    private String exception;

    private String message;

    private String path;

    @JsonIgnore
    private Builder builder;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static Builder getBuidler(){
        return new Builder();
    }

    static class Builder{

        private ErrorResponse response;

        private Builder(){
            response = new ErrorResponse();
        }

        public Builder message(String message){
            response.setMessage(message);
            return this;
        }

        public Builder path(String path){
            response.setPath(path);
            return this;
        }

        public Builder error(String error){
            response.setError(error);
            return this;
        }

        public Builder exception(String exception){
            response.setException(exception);
            return this;
        }

        public Builder status(Integer status){
            response.setStatus(status);
            return this;
        }

        public ErrorResponse build(){
            response.setTimestamp(DateTime.now().getMillis());
            return response;
        }

    }
}
