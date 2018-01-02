package killbit.taskrabbit.retrofit.bookingConfirmation;

/**
 * Created by kural mughil selvam on 23-11-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class bookingConfirmation implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("total_available_balance")
    @Expose
    private String totalAvailableBalance;
    private final static long serialVersionUID = -5355889339764441568L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getTotalAvailableBalance() {
        return totalAvailableBalance;
    }

    public void setTotalAvailableBalance(String totalAvailableBalance) {
        this.totalAvailableBalance = totalAvailableBalance;
    }

}