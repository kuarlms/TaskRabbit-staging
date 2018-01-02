
package killbit.taskrabbit.retrofit.Chattingreceive;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingInfo implements Serializable
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
    private Object subcatName;
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
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    private final static long serialVersionUID = -1023844045738349865L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BookingInfo() {
    }

    /**
     * 
     * @param lastName
     * @param bookingId
     * @param currencyCode
     * @param bookingDay
     * @param bookingTime
     * @param subcatName
     * @param bookingMonth
     * @param city
     * @param proPic
     * @param needVehicle
     * @param currencySymbol
     * @param catName
     * @param totalAmount
     * @param taskerId
     * @param firstName
     */
    public BookingInfo(String bookingId, String taskerId, String firstName, String lastName, String proPic, String catName, Object subcatName, String bookingDay, String bookingMonth, String bookingTime, String needVehicle, String city, String totalAmount, String currencyCode, String currencySymbol) {
        super();
        this.bookingId = bookingId;
        this.taskerId = taskerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.proPic = proPic;
        this.catName = catName;
        this.subcatName = subcatName;
        this.bookingDay = bookingDay;
        this.bookingMonth = bookingMonth;
        this.bookingTime = bookingTime;
        this.needVehicle = needVehicle;
        this.city = city;
        this.totalAmount = totalAmount;
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
    }

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

    public Object getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(Object subcatName) {
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

}
