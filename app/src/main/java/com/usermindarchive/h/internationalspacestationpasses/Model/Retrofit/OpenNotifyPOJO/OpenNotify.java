
package com.usermindarchive.h.internationalspacestationpasses.Model.Retrofit.OpenNotifyPOJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenNotify {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

}
