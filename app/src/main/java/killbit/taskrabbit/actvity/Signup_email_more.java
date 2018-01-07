package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import retrofit2.http.Part;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */




public class Signup_email_more extends Activity  {


    @BindView(R.id.spinner_city)
    Spinner spinnerCity;

    @BindView(R.id.editText2)
            EditText etHomeAddess;

    @BindView(R.id.editText6)
            EditText etStreetAddress;

    @BindView(R.id.editText_password)
            EditText etTownCity;

    @BindView(R.id.editText_phone)
            EditText etState;

    @BindView(R.id.editText7X)
            EditText etZipCode;

    @BindView(R.id.editTextC7)
            EditText etDOb;

    @BindView(R.id.editText7)
            EditText etDistanceKm;

    @BindView(R.id.checkboxv1)
    CheckBox cbv1;

    @BindView(R.id.checkboxv2)
    CheckBox cbv2;

    @BindView(R.id.checkboxv3)
    CheckBox cbv3;



   /* @BindView(R.id.checkboxv1C)
    CheckBox cbvRef;

    @BindView(R.id.checkboxv2C)
    CheckBox cbvFacebook;

    @BindView(R.id.checkboxv3C)
    CheckBox cbvGoogle;


    @BindView(R.id.checkboxv4C)
    CheckBox cbvBing;
*/
    @BindView(R.id.checkboxv5C)
    RadioButton cbvOthers;

    @BindView(R.id.editText_phonex)
            EditText etRightPerson;

    @BindView(R.id.editVBText_phone)
    EditText etWhenNotServicingn;

    @BindView(R.id.editTexCVSt_phone)
    EditText etWhenServicingn;

    @BindView(R.id.radiogrp)
    RadioGroup radioGroupfrom;


    String work_city,home,email,street,
    city,state,zipcode,dob,detail1,detail2,
    detail3,hear_about,vehicle_types,tasker_step1,
    tasker_step2,distance;

    ApiInterface mAPIService;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    RadioButton selectedRadioButton;

    ArrayList<String>vehiclesList = new ArrayList<>();
    ArrayList<String>CityList = new ArrayList<>();



    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email_more);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        email = sp.getString(sp_task.Sp_email,"");
        cbvOthers.setChecked(true);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("citysjson.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line

            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        radioGroupfrom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i ==R.id.checkboxv1C){
                    hear_about = "Reffered by a friend";
                }else if(i == R.id.checkboxv2C){
                    hear_about = "Facebook";
                }else if(i == R.id.checkboxv3C){
                    hear_about = "Google";
                }else if(i == R.id.checkboxv4C){
                    hear_about = "Bing";
                }else if(i == R.id.checkboxv5C){
                    hear_about = "Others";
                }

            }
        });

      // spinnerCity.setAdapter(CityList,);












    }
    @OnClick(R.id.button9)
    public void btn_submit(){




        if(cbv1.isChecked()){
            vehiclesList.add("1");
        }
        if(cbv2.isChecked()){
            vehiclesList.add("2");
        }
        if(cbv3.isChecked()){
            vehiclesList.add("3");
        }

        work_city = spinnerCity.getSelectedItem().toString();

        if(etHomeAddess.getText().toString().length() >= 2){
            home = etHomeAddess.getText().toString();
        }else {
            etHomeAddess.setError("");
            return;
        }

        if(etStreetAddress.getText().toString().length() >= 2){
            street = etStreetAddress.getText().toString();
        }else {
            etStreetAddress.setError("");
            return;
        }

        if(etTownCity.getText().toString().length() >= 2){
            city = etTownCity.getText().toString();
        }else {
            etTownCity.setError("");
            return;
        }

        if(etDOb.getText().toString().length() >= 2){
            dob = etDOb.getText().toString();
        }else {
            etDOb.setError("");
            return;
        }

        if(etZipCode.getText().toString().length() >= 2){
            zipcode = etZipCode.getText().toString();
        }else {
            etZipCode.setError("");
            return;
        }

        if( etState.getText().toString().length() >= 2){
            state = etState.getText().toString();
        }else {
            etState.setError("");
            return;
        }

        if( etWhenServicingn.getText().toString().length() >= 2){
            detail3 = etWhenServicingn.getText().toString();
        }else {
            etWhenServicingn.setError("");
            return;
        }

        if( etWhenNotServicingn.getText().toString().length() >= 2){
            detail2 = etWhenNotServicingn.getText().toString();

        }else {
            etWhenNotServicingn.setError("");
            return;
        }

        if( etRightPerson.getText().toString().length() >= 2){
            detail1 = etRightPerson.getText().toString();
        }else {
            etRightPerson.setError("");
            return;
        }



    onValidationSucceeded();

    }




    public void onValidationSucceeded() {

        tasker_step1 = "0";
        tasker_step2 = "1";
        Intent i ;
        i = new Intent(Signup_email_more.this,MainActivity.class);

        mAPIService.rf_signUpMore(ApiInterface.header_value,work_city,home,email,street,city,state,zipcode,
                dob,detail1,detail2,detail3,hear_about,vehiclesList.toString(),tasker_step1,tasker_step2,
                distance).enqueue(new Callback<signupStatus>() {
            @Override
            public void onResponse(Call<signupStatus> call, Response<signupStatus> response) {

                try {
                    if(response.body().getStatus() == 1){
                        Toast.makeText(getApplicationContext(), "Sign-up successful, signIn to proceed.", Toast.LENGTH_SHORT).show();


                        startActivity(i);
                        finish();



                    }else {
                        Toast.makeText(getApplicationContext(), "Already exists, Try forgot Password or using another email address."+response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<signupStatus> call, Throwable t) {
                Toast.makeText(Signup_email_more.this, "Unable to reach server, Check connectivity. ", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
