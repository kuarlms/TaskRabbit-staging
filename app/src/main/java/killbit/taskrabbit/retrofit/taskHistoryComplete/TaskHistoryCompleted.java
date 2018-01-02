
package killbit.taskrabbit.retrofit.taskHistoryComplete;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskHistoryCompleted {

    @SerializedName("task_pending_array")
    @Expose
    private List<TaskPendingArray> taskPendingArray = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TaskHistoryCompleted() {
    }

    /**
     * 
     * @param status
     * @param taskPendingArray
     */
    public TaskHistoryCompleted(List<TaskPendingArray> taskPendingArray, Integer status) {
        super();
        this.taskPendingArray = taskPendingArray;
        this.status = status;
    }

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
