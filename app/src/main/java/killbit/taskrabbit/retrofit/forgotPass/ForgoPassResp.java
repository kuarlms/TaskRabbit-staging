package killbit.taskrabbit.retrofit.forgotPass;

/**
 * Created by kural mughil selvam on 22-10-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgoPassResp {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ForgoPassResp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}