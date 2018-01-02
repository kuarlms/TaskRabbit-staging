
package killbit.taskrabbit.retrofit.MyServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MyServicesResp implements Serializable
{

    @SerializedName("main_cat_list")
    @Expose
    private List<MainCatList> mainCatList = null;
    @SerializedName("cleaning_list")
    @Expose
    private List<CleaningList> cleaningList = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = 6495200930933638110L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyServicesResp() {
    }

    /**
     * 
     * @param status
     * @param mainCatList
     * @param cleaningList
     */
    public MyServicesResp(List<MainCatList> mainCatList, List<CleaningList> cleaningList, Integer status) {
        super();
        this.mainCatList = mainCatList;
        this.cleaningList = cleaningList;
        this.status = status;
    }

    public List<MainCatList> getMainCatList() {
        return mainCatList;
    }

    public void setMainCatList(List<MainCatList> mainCatList) {
        this.mainCatList = mainCatList;
    }

    public List<CleaningList> getCleaningList() {
        return cleaningList;
    }

    public void setCleaningList(List<CleaningList> cleaningList) {
        this.cleaningList = cleaningList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
