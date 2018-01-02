package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import killbit.taskrabbit.retrofit.MyServices.MyServicesResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class MyServices extends FragmentActivity implements vehicle_list_adp.OnRecyclerListener{

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

    Boolean isAddService = false;

    @BindView(R.id.btn_addServices)
    Button btnAdd;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_my_services);
        ButterKnife.bind(this);



        tv_title.setText("My Services");
        pb.setVisibility(View.GONE);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();





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

        if(isAddService){












        }}

    @OnClick(R.id.btn_addServices)
    public void addService() {

        isAddService = true;
        tasks_datas.clear();
        tv_title.setText("Add Services");
        mAPIService.rf_myServicesAdd(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""))
                .enqueue(new Callback<MyServicesResp>() {
                    @Override
                    public void onResponse(Call<MyServicesResp> call, Response<MyServicesResp> response) {

                        if(response.body().getStatus().equals(1)){
                            for (int i = 0; i < response.body().getMainCatList().size(); i++) {
                                tasks_data = new vehicle_list_data(response.body().getMainCatList().get(i).getMainCatName(),
                                        response.body().getMainCatList().get(i).getId());

                                tasks_datas.add(tasks_data);

                                adapter_my_account.notifyDataSetChanged();

                            }


                        }else{

                        }

                    }

                    @Override
                    public void onFailure(Call<MyServicesResp> call, Throwable t) {

                    }
                });
        btnAdd.setVisibility(View.INVISIBLE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAPIService.rf_myServicesAdd(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""))
                .enqueue(new Callback<MyServicesResp>() {
            @Override
            public void onResponse(Call<MyServicesResp> call, Response<MyServicesResp> response) {

                if(response.body().getStatus().equals(1)){
                    for (int i = 0; i < response.body().getMainCatList().size(); i++) {
                        tasks_data = new vehicle_list_data(response.body().getMainCatList().get(i).getMainCatName(),
                                response.body().getMainCatList().get(i).getId());

                        tasks_datas.add(tasks_data);

                        adapter_my_account.notifyDataSetChanged();

                    }


                }else{

                }

            }

            @Override
            public void onFailure(Call<MyServicesResp> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {


        if(isAddService){
            tasks_datas.clear();
            onStart();
            btnAdd.setVisibility(View.VISIBLE);
            isAddService = false;
            tv_title.setText("My Services");
        }else {
            super.onBackPressed();
            finish();

        }


    }
}
