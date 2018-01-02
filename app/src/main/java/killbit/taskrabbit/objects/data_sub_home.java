package killbit.taskrabbit.objects;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class data_sub_home {
    String subcat_id,subcat_name,subcat_image,avg_price;

    public data_sub_home(String subcat_id, String subcat_name, String subcat_image, String avg_price) {
        this.subcat_id = subcat_id;
        this.subcat_name = subcat_name;
        this.subcat_image = subcat_image;
        this.avg_price = avg_price;
    }



    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getSubcat_name() {
        return subcat_name;
    }

    public void setSubcat_name(String subcat_name) {
        this.subcat_name = subcat_name;
    }

    public String getSubcat_image() {
        return subcat_image;
    }

    public void setSubcat_image(String subcat_image) {
        this.subcat_image = subcat_image;
    }

    public String getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(String avg_price) {
        this.avg_price = avg_price;
    }
}
