
package killbit.taskrabbit.retrofit.Chattingreceive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatUserInfo implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("city")
    @Expose
    private String city;
    private final static long serialVersionUID = -7616195069632139205L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ChatUserInfo() {
    }

    /**
     * 
     * @param profileImage
     * @param name
     * @param city
     */
    public ChatUserInfo(String name, String profileImage, String city) {
        super();
        this.name = name;
        this.profileImage = profileImage;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
