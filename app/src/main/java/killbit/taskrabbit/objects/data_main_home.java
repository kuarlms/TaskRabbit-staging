package killbit.taskrabbit.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class data_main_home {
    String cat_name,cat_title,cat_icon,cat_id;
    List<data_sub_home>  sub_data = new ArrayList<>();

    public data_main_home(String cat_name, String cat_title, String cat_icon, String cat_id, List<data_sub_home> sub_data) {
        this.cat_name = cat_name;
        this.cat_title = cat_title;
        this.cat_icon = cat_icon;
        this.cat_id = cat_id;
        this.sub_data = sub_data;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(String cat_icon) {
        this.cat_icon = cat_icon;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public List<data_sub_home> getSub_data() {
        return sub_data;
    }

    public void setSub_data(List<data_sub_home> sub_data) {
        this.sub_data = sub_data;
    }
}
