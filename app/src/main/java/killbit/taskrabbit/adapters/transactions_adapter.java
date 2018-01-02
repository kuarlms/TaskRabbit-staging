package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.transactionList.ExportResult;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class transactions_adapter extends RecyclerView.Adapter<transactions_adapter.MyViewHolder>{

    private List<ExportResult> ListDatas;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;
    ExportResult listData;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_task,tv_timing,tv_location,tv_vehicle,tv_cost;
        ImageView iv_pic;
        Button btnCurrency;




        public MyViewHolder(View view, Context context) {
            super(view);


            iv_pic=view.findViewById(R.id.imageView10);

            tv_name=view.findViewById(R.id.textView43);

            tv_timing=view.findViewById(R.id.textView2);

            tv_task=view.findViewById(R.id.textView44);

            tv_cost=view.findViewById(R.id.tv_thp_time);

            tv_location=view.findViewById(R.id.textView_adp_act_cost);

            tv_vehicle=view.findViewById(R.id.textView20);

            btnCurrency=view.findViewById(R.id.button13);


        }
    }


    public transactions_adapter(List<ExportResult> ListData, Context context, OnRecyclerListener recyclerListener) {

        this.ListDatas = ListData;
        this.context = context;
        this.recyclerListener= recyclerListener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_transactions, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
         listData = ListDatas.get(position);

         holder.tv_name.setText(listData.getTakserName());
         holder.tv_task.setText(listData.getTaskName());
         holder.tv_timing.setText("( "+listData.getBookingDay()+" "+listData.getBookingMonth()+"-"+listData.getBooking_time()+" )");
         //holder.tv_taskTime.setText(listData.getBookingDay());
         holder.tv_location.setText(listData.getAddress());
         holder.tv_vehicle.setText(listData.getTaskName());
         holder.tv_cost.setText('$'+listData.getPaidAmount());
        //holder.btnCurrency.setText(listData.getCurrencySymbol());
        Glide.with(context).load(listData.getProPic()).apply(bitmapTransform(new RoundedCorners(5))).into(holder.iv_pic);






    }



    public interface OnRecyclerListener {
        void onTransactionItenClicked(int position, String tasker_id, String Profile_pic, String RatePerHr, String TaskerName);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
