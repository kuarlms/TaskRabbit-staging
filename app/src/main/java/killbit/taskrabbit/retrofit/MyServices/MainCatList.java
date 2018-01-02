
package killbit.taskrabbit.retrofit.MyServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainCatList implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("main_cat_name")
    @Expose
    private String mainCatName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("tasker_description")
    @Expose
    private String taskerDescription;
    private final static long serialVersionUID = -8241021282858087500L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainCatList() {
    }

    /**
     * 
     * @param id
     * @param currencyCode
     * @param price
     * @param mainCatName
     * @param currencySymbol
     * @param taskerDescription
     */
    public MainCatList(String id, String mainCatName, String price, String currencyCode, String currencySymbol, String taskerDescription) {
        super();
        this.id = id;
        this.mainCatName = mainCatName;
        this.price = price;
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.taskerDescription = taskerDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainCatName() {
        return mainCatName;
    }

    public void setMainCatName(String mainCatName) {
        this.mainCatName = mainCatName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getTaskerDescription() {
        return taskerDescription;
    }

    public void setTaskerDescription(String taskerDescription) {
        this.taskerDescription = taskerDescription;
    }

}
