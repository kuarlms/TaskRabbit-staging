
package killbit.taskrabbit.retrofit.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InboxResp {

    @SerializedName("unreadmessage_count")
    @Expose
    private String unreadmessageCount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message_list")
    @Expose
    private List<MessageList> messageList = null;

    public String getUnreadmessageCount() {
        return unreadmessageCount;
    }

    public void setUnreadmessageCount(String unreadmessageCount) {
        this.unreadmessageCount = unreadmessageCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MessageList> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageList> messageList) {
        this.messageList = messageList;
    }

}
