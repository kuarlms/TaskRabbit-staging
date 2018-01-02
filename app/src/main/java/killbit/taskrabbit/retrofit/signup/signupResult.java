package killbit.taskrabbit.retrofit.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public class signupResult {


    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("pro_pic")
    @Expose
    private String proPic;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     *
     */
    public signupResult() {
    }

    /**
     *
     * @param lastName
     * @param proPic
     * @param email
     * @param userId
     * @param firstName
     */
    public signupResult(String userId, String firstName, String lastName, String proPic, String email) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.proPic = proPic;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "signupResult{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", proPic='" + proPic + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
