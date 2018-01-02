
package killbit.taskrabbit.retrofit.bookingStep1;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bookingStep1Resp implements Serializable
{

    @SerializedName("dropdown_data")
    @Expose
    private DropdownData dropdownData;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = 2051756219914119963L;

    public DropdownData getDropdownData() {
        return dropdownData;
    }

    public void setDropdownData(DropdownData dropdownData) {
        this.dropdownData = dropdownData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
