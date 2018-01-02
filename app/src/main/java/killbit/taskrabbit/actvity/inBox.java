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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.inbox_adapter;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.inbox.InboxResp;
import killbit.taskrabbit.retrofit.inbox.MessageList;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 11/10/17.
 */

public class inBox extends FragmentActivity implements  inbox_adapter.OnRecyclerListener{

    MessageList data;
    List<MessageList> Listdata = new ArrayList<>();
    inbox_adapter adapter;

    @BindView(R.id.recycleView_tasker_details)
    RecyclerView rv_at_list;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    ApiInterface mAPIService;
    String email;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasker_recycler);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        tv_title.setText("Inbox");
        email = sp.getString(sp_task.Sp_email,"");

        adapter = new inbox_adapter(Listdata,this,inBox.this);
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
        mAPIService.rf_inbox(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<InboxResp>() {



            @Override
            public void onResponse(Call<InboxResp> call, Response<InboxResp> response) {
                if(response.body().getStatus().equals(1)){
                    for (int i = 0; i <response.body().getMessageList().size() ; i++) {

                        data = new MessageList(response.body().getMessageList().get(i).getName(),
                                response.body().getMessageList().get(i).getProfileImage(),
                                response.body().getMessageList().get(i).getTaskName(),
                                response.body().getMessageList().get(i).getCreatedTime(),
                                response.body().getMessageList().get(i).getMessage(),
                                response.body().getMessageList().get(i).getBookingId());

                        Listdata.add(data);
                        adapter.notifyDataSetChanged();
                    }


                }else {

                }

            }

            @Override
            public void onFailure(Call<InboxResp> call, Throwable t) {

            }  });


    }



    @Override
    public void onInboxItemSelected(int position, String tasker_id, String TaskerName) {

       // Toast.makeText(this, "Clk", Toast.LENGTH_SHORT).show();
        Intent inChat = new Intent(inBox.this,chat.class);
        inChat.putExtra("taskId",tasker_id);
        inChat.putExtra("taskerName",TaskerName);
        startActivity(inChat);
    }
}
