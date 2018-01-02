package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.transactions_adapter;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.transactionList.ExportResult;
import killbit.taskrabbit.retrofit.transactionList.transactionsResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class transactionList extends Activity implements transactions_adapter.OnRecyclerListener {


     String email;
     int page = 1;

     ExportResult transactionData;

     List<ExportResult> transactionList = new ArrayList<>();

     transactions_adapter adapter;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.pb_active_tasks_loading)
    ProgressBar pb;
    @BindView(R.id.recycleView)
    RecyclerView rv_at_list;


    ApiInterface mAPIService;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvity_active_tasks);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        tv_title.setText("Transactions");

        email = sp.getString(sp_task.Sp_email,"");



        adapter = new transactions_adapter(transactionList,getApplicationContext(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_at_list.setLayoutManager(mLayoutManager);
        rv_at_list.setItemAnimator(new DefaultItemAnimator());
        rv_at_list.setAdapter(adapter);


    }



    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAPIService.rf_transaction_list(ApiInterface.header_value, email,""+page)
                .enqueue(new Callback<transactionsResp>() {
                    @Override
                    public void onResponse(Call<transactionsResp> call, Response<transactionsResp> response) {
                        if(response.body().getStatus().equals(1)){


                            for (int i = 0; i <response.body().getExportResult().size() ; i++) {

                                transactionData = new ExportResult(response.body().getExportResult().get(i).getTakserName(),
                                        response.body().getExportResult().get(i).getTaskName(),
                                        response.body().getExportResult().get(i).getProPic(),
                                        response.body().getExportResult().get(i).getBookingDate(),
                                        response.body().getExportResult().get(i).getBookingDay(),
                                        response.body().getExportResult().get(i).getBookingMonth(),
                                        response.body().getExportResult().get(i).getCurrencySymbol(),
                                        response.body().getExportResult().get(i).getPaidAmount(),
                                        response.body().getExportResult().get(i).getBooking_time(),
                                        response.body().getExportResult().get(i).getAddress());


                                transactionList.add(transactionData);

                            }
                            adapter.notifyDataSetChanged();


                        }else {


                        }
                    }

                    @Override
                    public void onFailure(Call<transactionsResp> call, Throwable t) {

                        Toast.makeText(transactionList.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });




    }

    @Override
    public void onTransactionItenClicked(int position, String tasker_id, String Profile_pic, String RatePerHr, String TaskerName) {

    }
}
