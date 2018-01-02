
package killbit.taskrabbit.retrofit.MyServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CleaningList implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("checked_status")
    @Expose
    private String checkedStatus;
    private final static long serialVersionUID = -9114997883905328303L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CleaningList() {
    }

    /**
     * 
     * @param checkedStatus
     * @param id
     * @param title
     * @param description
     */
    public CleaningList(String id, String title, String description, String checkedStatus) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.checkedStatus = checkedStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

}
