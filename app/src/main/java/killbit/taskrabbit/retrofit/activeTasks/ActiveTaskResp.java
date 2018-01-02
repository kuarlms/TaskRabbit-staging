
package killbit.taskrabbit.retrofit.activeTasks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ActiveTaskResp implements Serializable
{

    @SerializedName("task_pending_array")
    @Expose
    private List<TaskPendingArray> taskPendingArray = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = 8159159355124871097L;

    public List<TaskPendingArray> getTaskPendingArray() {
        return taskPendingArray;
    }

    public void setTaskPendingArray(List<TaskPendingArray> taskPendingArray) {
        this.taskPendingArray = taskPendingArray;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
