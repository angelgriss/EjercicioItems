package model;

import com.google.gson.JsonElement;

public class StandarResponse {

    private StatusResponse status;
    private String message;
    private JsonElement data;

    public StandarResponse(StatusResponse status)
    {
        this.status=status;
    }

    public StandarResponse(StatusResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonElement getData() {
        return data;
    }
}
