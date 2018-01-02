
package killbit.taskrabbit.retrofit.findTasker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TaskerList implements Serializable
{

    @SerializedName("tasker_id")
    @Expose
    private String taskerId;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("review_response_rate")
    @Expose
    private Integer reviewResponseRate;
    @SerializedName("id_verified")
    @Expose
    private String idVerified;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("service_percentage")
    @Expose
    private String servicePercentage;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("service_start_year")
    @Expose
    private String serviceStartYear;
    @SerializedName("task_done")
    @Expose
    private Integer taskDone;
    @SerializedName("detail1")
    @Expose
    private String detail1;
    @SerializedName("detail2")
    @Expose
    private String detail2;
    @SerializedName("detail3")
    @Expose
    private String detail3;
    @SerializedName("review_array")
    @Expose
    private List<ReviewArray> reviewArray = null;
    @SerializedName("supply_array")
    @Expose
    private List<Object> supplyArray = null;
    private final static long serialVersionUID = 7630439978022193833L;

    public String getTaskerId() {
        return taskerId;
    }

    public void setTaskerId(String taskerId) {
        this.taskerId = taskerId;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
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

    public Integer getReviewResponseRate() {
        return reviewResponseRate;
    }

    public void setReviewResponseRate(Integer reviewResponseRate) {
        this.reviewResponseRate = reviewResponseRate;
    }

    public String getIdVerified() {
        return idVerified;
    }

    public void setIdVerified(String idVerified) {
        this.idVerified = idVerified;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServicePercentage() {
        return servicePercentage;
    }

    public void setServicePercentage(String servicePercentage) {
        this.servicePercentage = servicePercentage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getServiceStartYear() {
        return serviceStartYear;
    }

    public void setServiceStartYear(String serviceStartYear) {
        this.serviceStartYear = serviceStartYear;
    }

    public Integer getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(Integer taskDone) {
        this.taskDone = taskDone;
    }

    public String getDetail1() {
        return detail1;
    }

    public void setDetail1(String detail1) {
        this.detail1 = detail1;
    }

    public String getDetail2() {
        return detail2;
    }

    public void setDetail2(String detail2) {
        this.detail2 = detail2;
    }

    public String getDetail3() {
        return detail3;
    }

    public void setDetail3(String detail3) {
        this.detail3 = detail3;
    }

    public List<ReviewArray> getReviewArray() {
        return reviewArray;
    }

    public void setReviewArray(List<ReviewArray> reviewArray) {
        this.reviewArray = reviewArray;
    }

    public List<Object> getSupplyArray() {
        return supplyArray;
    }

    public void setSupplyArray(List<Object> supplyArray) {
        this.supplyArray = supplyArray;
    }

}
