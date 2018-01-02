
package killbit.taskrabbit.retrofit.transactionList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExportResult implements Serializable
{

    @SerializedName("takser_name")
    @Expose
    private String takserName;
    @SerializedName("task_name")
    @Expose
    private String taskName;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("booking_day")
    @Expose
    private String bookingDay;
    @SerializedName("booking_month")
    @Expose
    private String bookingMonth;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("paid_amount")
    @Expose
    private String paidAmount;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("booking_time")
    @Expose
    private String booking_time;
    private final static long serialVersionUID = 3410349639409297894L;


    public ExportResult(String takserName, String taskName, String proPic, String bookingDate, String bookingDay, String bookingMonth, String currencySymbol,String paidAmount,String booking_time,String address) {
        this.takserName = takserName;
        this.taskName = taskName;
        this.proPic = proPic;
        this.bookingDate = bookingDate;
        this.bookingDay = bookingDay;
        this.bookingMonth = bookingMonth;
        this.currencySymbol = currencySymbol;
        this.paidAmount = paidAmount;
        this.address=address;
        this.booking_time=booking_time;
    }


    public String getTakserName() {
        return takserName;
    }

    public void setTakserName(String takserName) {
        this.takserName = takserName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

}
