package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.forgotPass.ForgoPassResp;
import killbit.taskrabbit.retrofit.signIn.LoginResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */


public class SignIn_email extends Activity implements Validator.ValidationListener{

    Validator validator;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    Intent i ;
    ApiInterface mAPIService;
    Boolean forgotPass = false;
    @NotEmpty
    @Email
    @BindView(R.id.editText) EditText et_email;

    @NotEmpty
    @Password
    @BindView(R.id.editText2)EditText et_pas;
    @BindView(R.id.textView14)TextView tv_pass_head;
    @BindView(R.id.textView19) TextView tv_forgot;

    @BindView(R.id.button_login)Button btn_login;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_email);
        ButterKnife.bind(this);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        mAPIService = ApiUtils.getAPIService();
        i = new Intent(SignIn_email.this,taskHistory.class);

        btn_login = findViewById(R.id.button_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(SignIn_email.this, "cc", Toast.LENGTH_LONG).show();
                if(forgotPass){
                    tv_pass_head.performClick();
                }else {

                validator.validate();}
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);


    }



    @OnClick(R.id.button_login)
    public void submit(){
        //Toast.makeText(SignIn_email.this, "cc", Toast.LENGTH_LONG).show();
        validator.validate();
    }
    @OnClick(R.id.button_register)
    public void sign_up_int(){
        Intent in = new Intent(SignIn_email.this, Signup_email.class);
        startActivity(in);
        finish();
    }

    @OnClick(R.id.textView19)
    public void forgotPass(){

        forgotPass = true;
        et_pas.setVisibility(View.INVISIBLE);
        tv_pass_head.setVisibility(View.INVISIBLE);
     //   btn_login.setVisibility(View.INVISIBLE);
        et_pas.setText("Pass@123");
        validator.validate();

        btn_login.setText("Reset");




        mAPIService.rf_forgotPass(ApiInterface.header_value, String.valueOf(et_email.getText())).enqueue(new Callback<ForgoPassResp>() {


            @Override
            public void onResponse(Call<ForgoPassResp> call, Response<ForgoPassResp> response) {
                Toast.makeText(SignIn_email.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ForgoPassResp> call, Throwable t) {

            }});
        }


    @Override
    public void onValidationSucceeded() {

        if(!forgotPass) {
          /*  Map<String,String>  user_id = new HashMap<>();
            user_id.put("login_email","sivauser@gmail.com");
            Map<String,String> pass =   new HashMap<>();
            pass.put("login_password","siva123");

            mAPIService.rf_signIn_map(ApiInterface.header_value, user_id, pass).enqueue(new Callback<LoginResp>() {

                @Override
                public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {

                    if (response.body().getStatus() == 1) {

                        editor.putString(sp_task.Sp_profile_pic, response.body().getResult().getProPic());
                        editor.putString(sp_task.Sp_name, response.body().getResult().getFirstName());
                        editor.putString(sp_task.Sp_email, et_email.getText().toString().trim());
                        editor.putBoolean(sp_task.Sp_IsLoggedIn, true);
                        editor.commit();

                        startActivity(i);
                        finish();


                    } else if (response.body().getStatus().equals("0")) {

                        Toast.makeText(SignIn_email.this, "Login Failed ,Invalid login detail.", Toast.LENGTH_SHORT).show();

                    }


                }

                @Override
                public void onFailure(Call<LoginResp> call, Throwable t) {
                    Toast.makeText(SignIn_email.this, "Login Failed....Check your connectivity.", Toast.LENGTH_SHORT).show();

                }
            });*/
          mtd();

        }
        else {
            tv_forgot.performClick();
        }


    }
void mtd(){

  mAPIService.rf_signIn(ApiInterface.header_value, String.valueOf(et_email.getText()), et_pas.getText().toString()).enqueue(new Callback<LoginResp>() {

        @Override
        public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {

            if (response.body().getStatus().equals(1)) {

                editor.putString(sp_task.Sp_profile_pic, response.body().getResult().getProPic());
                editor.putString(sp_task.Sp_name, response.body().getResult().getFirstName());
                editor.putString(sp_task.Sp_email, et_email.getText().toString().trim());
                editor.putBoolean(sp_task.Sp_IsLoggedIn, true);

                editor.commit();

                startActivity(i);
                finish();


            } else if (response.body().getStatus().equals(0)) {

                Toast.makeText(SignIn_email.this, "Login Failed ,Invalid login detail.", Toast.LENGTH_SHORT).show();

            }


        }

        @Override
        public void onFailure(Call<LoginResp> call, Throwable t) {
            Toast.makeText(SignIn_email.this, "Login Failed....Check your connectivity.", Toast.LENGTH_SHORT).show();

        }
    });

}

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
         //   Toast.makeText(this, "scsc", Toast.LENGTH_LONG).show();
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }



    }

    @Override
    public void onBackPressed() {

        if(forgotPass) {
            forgotPass = false;
            et_pas.setVisibility(View.VISIBLE);
            tv_pass_head.setVisibility(View.VISIBLE);
            et_pas.setText("");
         //   btn_login.setVisibility(View.VISIBLE);
            btn_login.setText("Login");
        }else {
            finish();
            super.onBackPressed();
        }
    }
}
