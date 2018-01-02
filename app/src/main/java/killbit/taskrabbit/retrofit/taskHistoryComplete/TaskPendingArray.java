
package killbit.taskrabbit.retrofit.taskHistoryComplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskPendingArray {

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
    @SerializedName("review_star")
    @Expose
    private String reviewStar;
    @SerializedName("review_done")
    @Expose
    private String reviewDone;
    @SerializedName("review_message")
    @Expose
    private String reviewMessage;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public TaskPendingArray() {
    }

    /**
     * 
     * @param lastName
     * @param bookingId
     * @param currencyCode
     * @param bookingDay
     * @param bookingTime
     * @param subcatName
     * @param reviewDone
     * @param city
     * @param bookingMonth
     * @param proPic
     * @param needVehicle
     * @param currencySymbol
     * @param catName
     * @param totalAmount
     * @param taskerId
     * @param reviewStar
     * @param reviewMessage
     * @param firstName
     */
    public TaskPendingArray(String bookingId, String taskerId, String firstName, String lastName, String proPic, String catName, String subcatName, String bookingDay, String bookingMonth, String bookingTime, String reviewStar, String reviewDone, String reviewMessage, String needVehicle, String city, String totalAmount, String currencyCode, String currencySymbol) {
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
        this.reviewStar = reviewStar;
        this.reviewDone = reviewDone;
        this.reviewMessage = reviewMessage;
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

    public String getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(String reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getReviewDone() {
        return reviewDone;
    }

    public void setReviewDone(String reviewDone) {
        this.reviewDone = reviewDone;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
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
