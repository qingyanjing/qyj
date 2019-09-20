package org.wisdom.wx.bp.model.custom;
/**
 * statusCode --->200 Success
 * statusCode --->400 failed
 * */
public class Message {
    private String statusCode;
    private Object data;

    public Message() {
        statusCode="400";
    }

    public Message(Object data) {
        statusCode="200";
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        statusCode="200";
        this.data = data;
    }
    public void setErrorData(Object data){
        statusCode="400";
        this.data = data;
    }
}
