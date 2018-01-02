
package killbit.taskrabbit.retrofit.Chattingreceive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatMessage implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    private final static long serialVersionUID = -3869112036712371655L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ChatMessage() {
    }

    /**
     * 
     * @param position
     * @param message
     * @param bookingId
     * @param createdTime
     */
    public ChatMessage(String message, String position, String bookingId, String createdTime) {
        super();
        this.message = message;
        this.position = position;
        this.bookingId = bookingId;
        this.createdTime = createdTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
