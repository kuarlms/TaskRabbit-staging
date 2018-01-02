
package killbit.taskrabbit.retrofit.bookingStep1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DropdownData implements Serializable
{

    @SerializedName("subcat_list")
    @Expose
    private List<SubcatList> subcatList = null;
    @SerializedName("vehicle_list")
    @Expose
    private List<VehicleList> vehicleList = null;
    @SerializedName("timing_list")
    @Expose
    private List<TimingList> timingList = null;
    private final static long serialVersionUID = 5094350711170649483L;

    public List<SubcatList> getSubcatList() {
        return subcatList;
    }

    public void setSubcatList(List<SubcatList> subcatList) {
        this.subcatList = subcatList;
    }

    public List<VehicleList> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<VehicleList> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<TimingList> getTimingList() {
        return timingList;
    }

    public void setTimingList(List<TimingList> timingList) {
        this.timingList = timingList;
    }

}
