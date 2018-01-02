package killbit.taskrabbit.retrofit.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public class signupStatus {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("result")
    @Expose
    private signupResult result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public signupResult getResult() {
        return result;
    }

    public void setResult(signupResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "signupStatus{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
