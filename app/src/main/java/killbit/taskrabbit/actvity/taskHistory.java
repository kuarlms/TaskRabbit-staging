package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.fragments.approved;
import killbit.taskrabbit.fragments.cancelled;
import killbit.taskrabbit.fragments.completed;
import killbit.taskrabbit.fragments.pending;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 11/10/17.
 */


public class taskHistory extends FragmentActivity {



    @BindView(R.id.pb_taskhistory)
    ProgressBar pb_taskhistory;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.viewpager_task_history)
            ViewPager mViewPager;

    @BindView(R.id.tabLayout_task_history)
    TabLayout tabLayout;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskhistory);
       ButterKnife.bind(this);
       sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
       editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();
        tv_title.setText("Task History");


        setUpViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(1);


        tabLayout.setupWithViewPager(mViewPager);

        pb_taskhistory.setVisibility(View.GONE);


    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

 /*   @Override
    protected void onStart() {
        super.onStart();

        mAPIService.rf_user_active_task(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<ActiveTaskResp>() {
            @Override
            public void onResponse(Call<ActiveTaskResp> call, Response<ActiveTaskResp> response) {

                if(response.body().getStatus().equals(1)){
                    for (int i = 0; i < response.body().getTaskPendingArray().size(); i++) {


                    }
                  pb_loading.setVisibility(View.GONE);
                }else {
                    Toast.makeText(taskHistory.this, "Failed  "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ActiveTaskResp> call, Throwable t) {

                Toast.makeText(taskHistory.this, ""+t, Toast.LENGTH_LONG).show();

            }
        } );

    }*/
 private void setUpViewPager(ViewPager mViewPager) {

     ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
     adapter.addFragment(new pending(), "Pending");
     adapter.addFragment(new approved(), "Approved");
     adapter.addFragment(new completed(), "Completed");
     adapter.addFragment(new cancelled(), "Cancelled");


     mViewPager.setAdapter(adapter);

 }
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
