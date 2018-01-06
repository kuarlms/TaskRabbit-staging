package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;

/**
 * Created by kural on 1/6/18.
 */

public class Availability extends Activity {


    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;
    Activity _activity;

    @BindView(R.id.pb_account_loading)
    ProgressBar pb;
    @BindView(R.id.tb_normal_title)
    TextView tv_title;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_availablity);
        ButterKnife.bind(this);


        _activity =this;

        tv_title.setText("Availablity");
        pb.setVisibility(View.GONE);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();




    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }
    @OnClick({R.id.btn_account})
    public void btn_save(){



    }



}

