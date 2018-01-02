
package killbit.taskrabbit.retrofit.activeTasks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskPendingArray implements Serializable
{

    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("tasker_id")
    @Expose
    private String taskerId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("subcat_name")
    @Expose
    private String subcatName;
    @SerializedName("booking_day")
    @Expose
    private String bookingDay;
    @SerializedName("booking_month")
    @Expose
    private String bookingMonth;
    @SerializedName("booking_time")
    @Expose
    private String bookingTime;
    @SerializedName("need_vehicle")
    @Expose
    private String needVehicle;
    @SerializedName("city")
    @Expose
    private String city;
    private final static long serialVersionUID = 7182583052578890338L;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(String taskerId) {
        this.taskerId = taskerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    public String getBookingDay() {
        return bookingDay;
    }

    public void setBookingDay(String bookingDay) {
        this.bookingDay = bookingDay;
    }

    public String getBookingMonth() {
        return bookingMonth;
    }

    public void setBookingMonth(String bookingMonth) {
        this.bookingMonth = bookingMonth;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getNeedVehicle() {
        return needVehicle;
    }

    public void setNeedVehicle(String needVehicle) {
        this.needVehicle = needVehicle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
