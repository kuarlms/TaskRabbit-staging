
package killbit.taskrabbit.retrofit.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcatList {

    @SerializedName("subcat_id")
    @Expose
    private String subcatId;
    @SerializedName("subcat_name")
    @Expose
    private String subcatName;
    @SerializedName("subcat_image")
    @Expose
    private String subcatImage;
    @SerializedName("avg_price")
    @Expose
    private String avgPrice;

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    public String getSubcatImage() {
        return subcatImage;
    }

    public void setSubcatImage(String subcatImage) {
        this.subcatImage = subcatImage;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public String toString() {
        return "SubcatList{" +
                "subcatId='" + subcatId + '\'' +
                ", subcatName='" + subcatName + '\'' +
                ", subcatImage='" + subcatImage + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                '}';
    }
}
