
package killbit.taskrabbit.retrofit.uploadPpic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateAccountResp implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;

    @SerializedName("pro_pic_doc")
    @Expose
    private String pro_pic_doc;

    public String getPro_pic_doc() {
        return pro_pic_doc;
    }

    public void setPro_pic_doc(String pro_pic_doc) {
        this.pro_pic_doc = pro_pic_doc;
    }

    @SerializedName("result")

    @Expose
    private Result result;
    private final static long serialVersionUID = -8295474221979617910L;

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

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
