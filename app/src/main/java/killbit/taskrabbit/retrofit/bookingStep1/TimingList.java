
package killbit.taskrabbit.retrofit.bookingStep1;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimingList implements Serializable
{

    @SerializedName("time_id")
    @Expose
    private String timeId;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -6134548183843008455L;

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
