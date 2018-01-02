package killbit.taskrabbit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.actvity.Get_Task_Details;
import killbit.taskrabbit.adapters.home_screen_adapter;
import killbit.taskrabbit.objects.data_sub_home;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */

public class frag_home extends Fragment implements home_screen_adapter.OnRecyclerListener {
    private String title,cat_idx;

    private int page;

    ApiInterface mAPIService;
    RecyclerView recyclerView;
    home_screen_adapter recy_screen_adapter;
    List< data_sub_home> list_data = new ArrayList<>();

    // newInstance constructor for creating fragment with arguments
    public static frag_home newInstance(int page, List<data_sub_home> list_datas, String cat_id) {
        frag_home fragmentFirst = new frag_home();
        fragmentFirst.list_data=list_datas;

        Bundle  args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", list_datas.get(0).getSubcat_name());
        args.putString("cat_id", cat_id);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", page);
        title = getArguments().getString("someTitle",title);
        cat_idx = getArguments().getString("cat_id",cat_idx);


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_frag_home);
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_home_frag);
        tvLabel.setVisibility(View.GONE);

        recy_screen_adapter = new home_screen_adapter(list_data,getActivity().getApplicationContext(),frag_home.this);
        recyclerView.setAdapter(recy_screen_adapter);


       // tvLabel.setText(page + " -- " + list_data.size());
        mAPIService = ApiUtils.getAPIService();

        try {
          //  mtd_calReg();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }



    @Override
    public void onItemClicked(int position, String data, String sub_cat_id) {
      //  Toast.makeText(getActivity(), "-- "+cat_idx, Toast.LENGTH_SHORT).show();
        Intent in_gettask = new Intent(this.getActivity(), Get_Task_Details.class);
        in_gettask.putExtra("main_cat",cat_idx);
        in_gettask.putExtra("sub_cat",data);
        ArrayList<String> pass = new ArrayList<>();
        ArrayList<String> pass_ids = new ArrayList<>();

        pass.add(data);
        pass_ids.add(sub_cat_id);

        for (int i = 0; i <list_data.size() ; i++) {
            if(list_data.get(i).getSubcat_name().equalsIgnoreCase(data)){

            }else {
            pass.add(list_data.get(i).getSubcat_name());
            pass_ids.add(list_data.get(i).getSubcat_id());
            }
        }

        in_gettask.putStringArrayListExtra("list_cat",pass);
        in_gettask.putStringArrayListExtra("list_cat_ids",pass_ids);
        startActivity(in_gettask);
        //Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
    }
}
