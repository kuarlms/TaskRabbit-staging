package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.vehicle_list_adp;
import killbit.taskrabbit.objects.vehicle_list_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 11/10/17.
 */

public class myAccount extends FragmentActivity implements vehicle_list_adp.OnRecyclerListener{

    vehicle_list_data tasks_data;
    List<vehicle_list_data> tasks_datas = new ArrayList<>();
    vehicle_list_adp adapter_my_account;
      //  RecyclerView rv_at_list;
    @BindView(R.id.recycleView)
    RecyclerView rv_at_list;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;
    @BindView(R.id.pb_active_tasks_loading)
    ProgressBar pb;
    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.tv_empty)
    TextView tv_empty;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_active_tasks);
       ButterKnife.bind(this);



       tv_title.setText("My Account");
       pb.setVisibility(View.GONE);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();

        tasks_data = new vehicle_list_data("Task History","1");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Account","2");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Password","3");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Notification","4");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Billing info","5");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Transactions","6");
        tasks_datas.add(tasks_data);
        tasks_data = new vehicle_list_data("Deactivate","7");
        tasks_datas.add(tasks_data);



        adapter_my_account = new vehicle_list_adp(tasks_datas,this,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_at_list.setLayoutManager(mLayoutManager);
        rv_at_list.setItemAnimator(new DefaultItemAnimator());
        rv_at_list.setAdapter(adapter_my_account);



    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @Override
    public void onItemClickedVehicle(int position, String data) {

        switch (position){
            case 0:
                Intent inth = new Intent(myAccount.this,taskHistory.class);
                startActivity(inth);

                break;
            case 1:
                Intent inacc = new Intent(myAccount.this,Account.class);
                startActivity(inacc);


                break;
            case 2:
                Intent inP = new Intent(myAccount.this,changePassword.class);
                startActivity(inP);
                break;
            case 3:

                Intent inNo = new Intent(myAccount.this,notificationOptions.class);
                startActivity(inNo);
                break;
            case 4:

                Intent inbilling = new Intent(myAccount.this,billingInfo.class);
                startActivity(inbilling);


                break;
            case 5:

                Intent inbillingax = new Intent(myAccount.this,transactionList.class);
                startActivity(inbillingax);

                break;
            case 6:
                Intent inbillingx = new Intent(myAccount.this,deactvation.class);
                startActivity(inbillingx);

                break;

        }





    }
}
