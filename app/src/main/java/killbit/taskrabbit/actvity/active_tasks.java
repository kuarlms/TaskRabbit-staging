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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.active_tasks_adp;
import killbit.taskrabbit.objects.active_tasks_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.retrofit.activeTasks.ActiveTaskResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 11/10/17.
 */


public class active_tasks extends FragmentActivity implements active_tasks_adp.OnTaskDoneListner{

    active_tasks_data tasks_data;
    List<active_tasks_data> tasks_datas = new ArrayList<>();
    active_tasks_adp adapter_act_tsk;
      //  RecyclerView rv_at_list;
    @BindView(R.id.recycleView)
    RecyclerView rv_at_list;

    @BindView(R.id.pb_active_tasks_loading)
    ProgressBar pb_loading;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;


    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;

    @BindView(R.id.tv_empty)
    TextView tv_empty;
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_active_tasks);
       ButterKnife.bind(this);
       sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
       editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();
        tv_title.setText("Active Task");


        adapter_act_tsk = new active_tasks_adp(tasks_datas,getApplicationContext(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_at_list.setLayoutManager(mLayoutManager);
        rv_at_list.setItemAnimator(new DefaultItemAnimator());
        rv_at_list.setAdapter(adapter_act_tsk);



    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAPIService.rf_user_active_task(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<ActiveTaskResp>() {
            @Override
            public void onResponse(Call<ActiveTaskResp> call, Response<ActiveTaskResp> response) {

                if(response.body().getStatus().equals(1)){
                    for (int i = 0; i < response.body().getTaskPendingArray().size(); i++) {


                        tasks_data = new active_tasks_data(response.body().getTaskPendingArray().get(i).getFirstName(),
                                response.body().getTaskPendingArray().get(i).getCatName(),
                                response.body().getTaskPendingArray().get(i).getBookingDay(),
                                response.body().getTaskPendingArray().get(i).getBookingTime(),
                                response.body().getTaskPendingArray().get(i).getCity(),
                                response.body().getTaskPendingArray().get(i).getNeedVehicle(),
                                response.body().getTaskPendingArray().get(i).getProPic(),
                                response.body().getTaskPendingArray().get(i).getBookingId(),
                                response.body().getTaskPendingArray().get(i).getTaskerId(),
                                response.body().getTaskPendingArray().get(i).getBookingId(),
                                response.body().getTaskPendingArray().get(i).getBookingDay(),
                                response.body().getTaskPendingArray().get(i).getBookingMonth()
                        );

                        tasks_datas.add(tasks_data);




                    }
                  adapter_act_tsk.notifyDataSetChanged();
                  pb_loading.setVisibility(View.GONE);



                }else {
                    Toast.makeText(active_tasks.this, "Nothing to show."+response.body().getStatus(), Toast.LENGTH_LONG).show();
                    pb_loading.setVisibility(View.INVISIBLE);
                    tv_empty.setVisibility(View.VISIBLE);


                }


            }

            @Override
            public void onFailure(Call<ActiveTaskResp> call, Throwable t) {

                Toast.makeText(active_tasks.this, ""+t, Toast.LENGTH_LONG).show();

            }
        } );

    }

    @Override
    public void onBtnTaskDone(String booking_id, String task_hour) {

      mAPIService.rf_task_completed(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""),booking_id,task_hour).enqueue(new Callback<StatusResp>() {

          @Override
          public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {

             if (response.body().getStatus().equals(1)){
                 Toast.makeText(active_tasks.this, "Successfully post the the hours.", Toast.LENGTH_SHORT).show();
                 tasks_datas.clear();
                 onStart();
             }else {

             }

          }

          @Override
          public void onFailure(Call<StatusResp> call, Throwable t) {

          }});

    }

    @Override
    public void onBtnChat(String booking_id, String position) {

        Intent inChat = new Intent(active_tasks.this,chat.class);
        inChat.putExtra("taskId",booking_id);
        inChat.putExtra("taskerName",position);
        startActivity(inChat);

    }
}
