package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class deactvation extends Activity {
        String email;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;



    ApiInterface mAPIService;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deactvation);
        ButterKnife.bind(this);

         mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        tv_title.setText("Deactivate");

        email = sp.getString(sp_task.Sp_email,"");


    }

    @OnClick(R.id.button_change_noti)
    public void setBtn_confirm(){

        mAPIService.rf_Deact_account(ApiInterface.header_value, email)
                .enqueue(new Callback<StatusResp>() {
                    @Override
                    public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {
                        if(response.body().getStatus().equals(1)){
                            Toast.makeText(deactvation.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            finish();
                        }else {
                            Toast.makeText(deactvation.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResp> call, Throwable t) {

                        Toast.makeText(deactvation.this, ""+t, Toast.LENGTH_SHORT).show();
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
