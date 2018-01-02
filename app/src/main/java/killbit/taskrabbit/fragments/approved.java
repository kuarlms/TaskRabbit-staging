package killbit.taskrabbit.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.taskHistory_Approved_adapter;
import killbit.taskrabbit.objects.active_tasks_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.retrofit.activeTasks.ActiveTaskResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural mughil selvam on 03-12-2017.
 */

public class approved extends Fragment implements taskHistory_Approved_adapter.OnTaskDoneListner {
    View view;
    active_tasks_data tasks_data;
    List<active_tasks_data> tasks_datas = new ArrayList<>();
    taskHistory_Approved_adapter adapter;



    @BindView(R.id.recycleView)
    RecyclerView rv_list;

    @BindView(R.id.pb_taskhistory)
    ProgressBar pb_task;

    @BindView(R.id.tv_empty)
    TextView tv_empty;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.taskshistory_fragment_approved, container, false);
        ButterKnife.bind(this, view);
        pb_task.setVisibility(View.VISIBLE);
        sp =  getActivity().getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();

        adapter = new taskHistory_Approved_adapter(tasks_datas,getActivity(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_list.setLayoutManager(mLayoutManager);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(adapter);



        return (view);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAPIService.rf_dashboard_user_task_history_approved(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<ActiveTaskResp>() {
            @Override
            public void onResponse(Call<ActiveTaskResp> call, Response<ActiveTaskResp> response) {

                if(response.body().getStatus().equals(1)){
                    tasks_datas.clear();

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
                    adapter.notifyDataSetChanged();
                    pb_task.setVisibility(View.GONE);

                }else {
                   // Toast.makeText(getActivity(), "Failed  "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                    tv_empty.setVisibility(View.VISIBLE);
                    pb_task.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<ActiveTaskResp> call, Throwable t) {

                Toast.makeText(getContext(), ""+t, Toast.LENGTH_LONG).show();

            }
        } );



    }


    @Override
    public void onBtnTaskDone(String booking_id, String task_hour) {

        mAPIService.rf_user_cancel_task(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""),booking_id).enqueue(new Callback<StatusResp>() {

            @Override
            public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {
                if(response.body().getStatus().equals(1)){
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    onStart();
                }else {
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusResp> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBtnChat(String booking_id, int position) {

    }
}
