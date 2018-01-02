package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class changePassword extends Activity {
        String email,
                newPass,old_pass;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;



    @BindView(R.id.et_change_pass_old)
    EditText et_old_pass;

    @BindView(R.id.et_change_pass_new)
    EditText et_new_pass;

    @BindView(R.id.et_change_pass_new_confirm)
    EditText et_new_confirm;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;


    @BindView(R.id.button_change_pass)
    Button btn_confirm;

    ApiInterface mAPIService;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

         mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();


        email = sp.getString(sp_task.Sp_email,"");
        tv_title.setText("Change Password");

    }

    @OnClick(R.id.button_change_pass)
    public void setBtn_confirm(){


        if(et_old_pass.getText().toString() != null  && et_old_pass.getText().toString().length() > 5 ){

            old_pass = et_old_pass.getText().toString();



        }else {
            et_old_pass.setError("Required min 5 char");
            return;
        }

        if(et_new_pass.getText().toString() != null  && et_new_pass.getText().toString().length() > 5 ){

            newPass = et_new_pass.getText().toString();

        }else {
            et_new_pass.setError("Required min 5 char");

            return;
        }

        if(et_new_confirm.getText().toString() != null &&
                et_new_confirm.getText().toString().equals(et_new_pass.getText().toString()) ){


        }else {
            et_new_confirm.setError("Passwords do not match!");

            return ;
        }



        mAPIService.rf_password_change(ApiInterface.header_value, email,old_pass,newPass,newPass)
                .enqueue(new Callback<StatusResp>() {
                    @Override
                    public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {
                        if(response.body().getStatus().equals(1)){
                            Toast.makeText(changePassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            finish();
                        }else {
                            Toast.makeText(changePassword.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResp> call, Throwable t) {

                        Toast.makeText(changePassword.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }


    @Override
    protected void onStart() {
        super.onStart();





    }
}
