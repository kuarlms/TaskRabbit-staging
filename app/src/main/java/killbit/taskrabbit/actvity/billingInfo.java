package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.utils.FourDigitsView;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class billingInfo extends Activity {
        String email,
                credit_card_type,number,cvc,exp_month,exp_year;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @BindView(R.id.iv_confirm_tasker_pic)
    ImageView iv_tasker_pic;

    @BindView(R.id.tv_confim_tasker_name)
    TextView tv_taskername;

    @BindView(R.id.tv_confim_tasker_cost)
    TextView tv_taskerCost;

    @BindView(R.id.tv_confim_tasker_payment_message)
    TextView tv_taskerPayment;

    @BindView(R.id.tv_confim_tasker_cancellation_fee)
    TextView tv_tasker_cancellation;

    @BindView(R.id.tv_confim_tasker_trust)
    TextView tv_tasker_trust;


    @BindView(R.id.et_confim_tasker_card_number)
    EditText et_card_number;

    @BindView(R.id.et_confim_tasker_card_cvv)
    EditText et_card_cvv;

    @BindView(R.id.et_confim_tasker_card_exp_date)
    EditText et_card_expMonth;

    @BindView(R.id.et_confim_tasker_card_exp_dateYear)
    EditText et_card_expYear;

    @BindView(R.id.btn_confim_tasker_payment)
    Button btn_confirm;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    ApiInterface mAPIService;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvity_billing_info);
        ButterKnife.bind(this);

         mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        tv_title.setText("Billing info");
        email = sp.getString(sp_task.Sp_email,"");
        et_card_number.setText(sp.getString(sp_task.Sp_cardnumber,""));
        et_card_expMonth.setText(sp.getString(sp_task.Sp_card_mm,""));
        et_card_expYear.setText(sp.getString(sp_task.Sp_card_yr,""));
        et_card_number.addTextChangedListener(new FourDigitsView());

    }

    @OnClick(R.id.btn_confim_tasker_payment)
    public void setBtn_confirm(){


        if(et_card_number.getText() != null  && et_card_number.getText().length() > 8 ){

            number = et_card_number.getText().toString();

            credit_card_type = "1";

        }else {
            et_card_number.setError("Required");
            return;
        }

        if(et_card_expMonth.getText() != null && et_card_expMonth.getText().length() > 1){

            exp_month =et_card_expMonth.getText().toString();

        }else {
            et_card_expMonth.setError("Required");
            return;
        }


        if(et_card_expYear.getText() != null && et_card_expYear.getText().length() > 1){

            exp_year = et_card_expYear.getText().toString();;

        }else {
            et_card_expYear.setError("Required");
            return;
        }


        if(et_card_cvv.getText() != null && et_card_cvv.getText().length() > 2){

            cvc = et_card_cvv.getText().toString();
        }else {
            et_card_cvv.setError("Required");
            return;
        }


        mAPIService.rf_billing_info(ApiInterface.header_value, email,number,cvc,exp_month,exp_year)
                .enqueue(new Callback<StatusResp>() {
                    @Override
                    public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {
                        if(response.body().getStatus().equals(1)){
                            Toast.makeText(billingInfo.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            editor.putString(sp_task.Sp_cardnumber, String.valueOf(et_card_number.getText()));
                            editor.putString(sp_task.Sp_card_mm, String.valueOf(et_card_expMonth.getText()));
                            editor.putString(sp_task.Sp_card_yr, String.valueOf(et_card_expYear.getText()));
                            editor.putString(sp_task.Sp_card_cvv, String.valueOf(et_card_cvv.getText()));

                            editor.commit();

                            finish();
                        }else {
                            Toast.makeText(billingInfo.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResp> call, Throwable t) {

                        Toast.makeText(billingInfo.this, ""+t, Toast.LENGTH_SHORT).show();
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
