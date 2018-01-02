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
import killbit.taskrabbit.adapters.taskHistory_completed_adapter;
import killbit.taskrabbit.objects.active_tasks_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.retrofit.activeTasks.ActiveTaskResp;
import killbit.taskrabbit.retrofit.taskHistoryComplete.TaskHistoryCompleted;
import killbit.taskrabbit.retrofit.taskHistoryComplete.TaskPendingArray;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural mughil selvam on 03-12-2017.
 */

public class completed extends Fragment implements taskHistory_completed_adapter.OnTaskDoneListner {
    View view;
    TaskPendingArray tasks_data;
    List<TaskPendingArray> tasks_datas = new ArrayList<>();
    taskHistory_completed_adapter adapter;



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
        view = inflater.inflate(R.layout.taskshistory_completed, container, false);
        ButterKnife.bind(this, view);
        pb_task.setVisibility(View.VISIBLE);
        sp =  getActivity().getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();

        adapter = new taskHistory_completed_adapter(tasks_datas,getActivity(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_list.setLayoutManager(mLayoutManager);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(adapter);



        return (view);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAPIService.rf_dashboard_user_task_history_completed(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<TaskHistoryCompleted>() {
            @Override
            public void onResponse(Call<TaskHistoryCompleted> call, Response<TaskHistoryCompleted> response) {

                if(response.body().getStatus().equals(1)){
                    tasks_datas.clear();

                    for (int i = 0; i < response.body().getTaskPendingArray().size(); i++) {

                        tasks_data = new TaskPendingArray(response.body().getTaskPendingArray().get(i).getBookingId(),
                                response.body().getTaskPendingArray().get(i).getTaskerId(),
                                response.body().getTaskPendingArray().get(i).getFirstName(),
                                response.body().getTaskPendingArray().get(i).getLastName(),
                                response.body().getTaskPendingArray().get(i).getProPic(),
                                response.body().getTaskPendingArray().get(i).getCatName(),
                                response.body().getTaskPendingArray().get(i).getSubcatName(),
                                response.body().getTaskPendingArray().get(i).getBookingDay(),
                                response.body().getTaskPendingArray().get(i).getBookingMonth(),
                                response.body().getTaskPendingArray().get(i).getBookingTime(),
                                response.body().getTaskPendingArray().get(i).getReviewStar(),
                                response.body().getTaskPendingArray().get(i).getReviewDone(),
                                response.body().getTaskPendingArray().get(i).getReviewMessage(),
                                response.body().getTaskPendingArray().get(i).getNeedVehicle(),
                                response.body().getTaskPendingArray().get(i).getCity(),
                                response.body().getTaskPendingArray().get(i).getTotalAmount(),
                                response.body().getTaskPendingArray().get(i).getCurrencyCode(),
                                response.body().getTaskPendingArray().get(i).getCurrencySymbol()


                        );

                        tasks_datas.add(tasks_data);




                    }
                    adapter.notifyDataSetChanged();
                    pb_task.setVisibility(View.GONE);
                }else {
                    // Toast.makeText(getActivity(), "Failed  "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                    pb_task.setVisibility(View.GONE);
                    tv_empty.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<TaskHistoryCompleted> call, Throwable t) {

            }


        });


    }

    @Override
    public void onBtnTaskDone(String booking_id, String task_hour, String ratting) {

        mAPIService.rf_save_review(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""),booking_id,ratting,task_hour).enqueue(new Callback<StatusResp>() {

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
