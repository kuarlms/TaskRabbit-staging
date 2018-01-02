
package killbit.taskrabbit.retrofit.findTasker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FindTaskerResp implements Serializable
{

    @SerializedName("tasker_list")
    @Expose
    private List<TaskerList> taskerList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = -2328496605015505023L;

    public List<TaskerList> getTaskerList() {
        return taskerList;
    }

    public void setTaskerList(List<TaskerList> taskerList) {
        this.taskerList = taskerList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
