
package killbit.taskrabbit.retrofit.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainCatList {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("cat_title")
    @Expose
    private String catTitle;
    @SerializedName("cat_icon")
    @Expose
    private String catIcon;
    @SerializedName("subcat_list")
    @Expose
    private List<SubcatList> subcatList = null;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }

    public String getCatIcon() {
        return catIcon;
    }

    public void setCatIcon(String catIcon) {
        this.catIcon = catIcon;
    }

    public List<SubcatList> getSubcatList() {
        return subcatList;
    }

    public void setSubcatList(List<SubcatList> subcatList) {
        this.subcatList = subcatList;
    }

    @Override
    public String toString() {
        return "MainCatList{" +
                "catId='" + catId + '\'' +
                ", catName='" + catName + '\'' +
                ", catTitle='" + catTitle + '\'' +
                ", catIcon='" + catIcon + '\'' +
                ", subcatList=" + subcatList +
                '}';
    }
}
