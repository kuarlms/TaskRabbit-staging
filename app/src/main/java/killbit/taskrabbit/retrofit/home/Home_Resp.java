
package killbit.taskrabbit.retrofit.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home_Resp {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("main_cat_list")
    @Expose
    private List<MainCatList> mainCatList = null;
    @SerializedName("featured_list")
    @Expose
    private List<Object> featuredList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MainCatList> getMainCatList() {
        return mainCatList;
    }

    public void setMainCatList(List<MainCatList> mainCatList) {
        this.mainCatList = mainCatList;
    }

    public List<Object> getFeaturedList() {
        return featuredList;
    }

    public void setFeaturedList(List<Object> featuredList) {
        this.featuredList = featuredList;
    }

    @Override
    public String toString() {
        return "Home_Resp{" +
                "status=" + status +
                ", mainCatList=" + mainCatList +
                ", featuredList=" + featuredList +
                '}';
    }
}
