package killbit.taskrabbit.retrofit.signIn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class LoginResp {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private LoginResult result;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LoginResult getResult() {
        return result;
    }


    public void setResult(LoginResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginResp{" +
                "status=" + status +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
