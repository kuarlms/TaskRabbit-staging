package killbit.taskrabbit.retrofit.signup;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public class SignUpReq {
   String first_name,last_name,email,password,phone;

    SignUpReq(String first_name,String last_name, String email, String password, String phone){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email= email;
        this.password= password;
        this.phone=phone;
    }


}
