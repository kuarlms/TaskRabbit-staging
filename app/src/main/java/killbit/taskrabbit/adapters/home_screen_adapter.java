package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.data_sub_home;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class home_screen_adapter extends RecyclerView.Adapter<home_screen_adapter.MyViewHolder>{

    private List<data_sub_home> ListData;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_cost;
        ImageView iv_image;
        LinearLayout item_parent;



        public MyViewHolder(View view, Context context) {
            super(view);
            iv_image = view.findViewById(R.id.iv_adp_home);
            tv_title =view.findViewById(R.id.txt_adp_cat_name);
            tv_cost =view.findViewById(R.id.txt_adp_cost);
            item_parent = view.findViewById(R.id.parent_item_adp_home);


        }
    }


    public home_screen_adapter(List<data_sub_home> ListData, Context context,OnRecyclerListener recyclerListener) {
        this.ListData = ListData;
        this.context = context;
        this.recyclerListener = recyclerListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
        data_sub_home listData = ListData.get(position);
        holder.tv_title.setText(listData.getSubcat_name());
        holder.tv_cost.setText(listData.getAvg_price());
        Glide.with(context).load(listData.getSubcat_image()).into(holder.iv_image);

        holder.item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerListener.onItemClicked(position, listData.getSubcat_name(),listData.getSubcat_id());
            }
        });


    }

    public interface OnRecyclerListener {
        void onItemClicked(int position,String data,String sub_cat_id);

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }



}
