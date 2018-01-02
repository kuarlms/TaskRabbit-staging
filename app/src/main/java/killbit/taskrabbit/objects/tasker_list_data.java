
package killbit.taskrabbit.objects;

import java.util.List;

import killbit.taskrabbit.retrofit.findTasker.ReviewArray;

public class tasker_list_data {

    private String taskerId;
    private String proPic;
    private String firstName;
    private String lastName;
    private Integer reviewResponseRate;
    private String idVerified;
    private String currencySymbol;
    private String price;
    private String servicePercentage;
    private String about;
    private String serviceStartYear;
    private Integer taskDone;
    private String detail1;
    private String detail2;
    private String detail3;
    private List<ReviewArray> reviewArray = null;
    private List<Object> supplyArray = null;


    public tasker_list_data(String taskerId, String proPic, String firstName, String lastName, Integer reviewResponseRate, String idVerified, String currencySymbol, String price, String servicePercentage, String about, String serviceStartYear, Integer taskDone, String detail1, String detail2, String detail3, List<ReviewArray> reviewArray, List<Object> supplyArray) {
        this.taskerId = taskerId;
        this.proPic = proPic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reviewResponseRate = reviewResponseRate;
        this.idVerified = idVerified;
        this.currencySymbol = currencySymbol;
        this.price = price;
        this.servicePercentage = servicePercentage;
        this.about = about;
        this.serviceStartYear = serviceStartYear;
        this.taskDone = taskDone;
        this.detail1 = detail1;
        this.detail2 = detail2;
        this.detail3 = detail3;
        this.reviewArray = reviewArray;
        this.supplyArray = supplyArray;
    }

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
