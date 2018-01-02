
package killbit.taskrabbit.retrofit.Chattingreceive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChatResp implements Serializable
{

    @SerializedName("booking_info")
    @Expose
    private BookingInfo bookingInfo;
    @SerializedName("chat_user_info")
    @Expose
    private ChatUserInfo chatUserInfo;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("chat_messages")
    @Expose
    private List<ChatMessage> chatMessages = null;
    private final static long serialVersionUID = 5312385183226165716L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ChatResp() {
    }

    /**
     * 
     * @param chatMessages
     * @param bookingInfo
     * @param status
     * @param chatUserInfo
     */
    public ChatResp(BookingInfo bookingInfo, ChatUserInfo chatUserInfo, Integer status, List<ChatMessage> chatMessages) {
        super();
        this.bookingInfo = bookingInfo;
        this.chatUserInfo = chatUserInfo;
        this.status = status;
        this.chatMessages = chatMessages;
    }

    public BookingInfo getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public ChatUserInfo getChatUserInfo() {
        return chatUserInfo;
    }

    public void setChatUserInfo(ChatUserInfo chatUserInfo) {
        this.chatUserInfo = chatUserInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

}
