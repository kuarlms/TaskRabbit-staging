package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Settings.Secure;
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
import killbit.taskrabbit.retrofit.signup.signupStatus;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */




public class Signup_email extends Activity implements Validator.ValidationListener {


    @NotEmpty
    @Email
    @BindView(R.id.editText)
    EditText et_email;

    @NotEmpty
    @BindView(R.id.editText2)
    EditText et_fname;

    @NotEmpty
    @BindView(R.id.editText6)
    EditText et_lname;

    @NotEmpty
    @BindView(R.id.editText7)
    EditText et_zip;

    @NotEmpty
    @Password(min = 6)
    @BindView(R.id.editText_password)
    EditText et_pass;

    @NotEmpty
    @BindView(R.id.editText_phone)
    EditText et_phone;

    Validator validator;
    ApiInterface mAPIService;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();



    }
    @OnClick(R.id.button9)
    public void btn_submit(){

        validator.validate();

    }



    @Override
    public void onValidationSucceeded() {

        String did= Secure.getString(getApplicationContext().getContentResolver(),
                Secure.ANDROID_ID);
        mAPIService.rf_signUp(ApiInterface.header_value,et_fname.getText().toString()
                ,et_lname.getText().toString(),et_email.getText().toString()
                ,et_pass.getText().toString(),et_phone.getText().toString(),
                et_zip.getText().toString(),did,"Android").enqueue(new Callback<signupStatus>() {
            @Override
            public void onResponse(Call<signupStatus> call, Response<signupStatus> response) {

                try {
                    if(response.body().getStatus() == 1){
                        Toast.makeText(getApplicationContext(), "Sign-up successful, signIn to proceed.", Toast.LENGTH_SHORT).show();

                        Intent i ;
                        i = new Intent(Signup_email.this,Signup_email_more.class);
                        editor.putBoolean(sp_task.Sp_IsLoggedIn,true);
                        editor.commit();

                        startActivity(i);

                        finish();



                    }else {
                        Toast.makeText(getApplicationContext(), "Already exists, Try forgot Password or using another email address.", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<signupStatus> call, Throwable t) {
                Toast.makeText(Signup_email.this, "Unable to reach server, Check connectivity. ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
