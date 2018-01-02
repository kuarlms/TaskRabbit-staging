package killbit.taskrabbit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.active_tasks_data;

/**
 * Created by kural on 11/10/17.
 */

public class active_tasks_adp extends RecyclerView.Adapter<active_tasks_adp.MyViewHolder> {

private List<active_tasks_data> taskList;
Context context;
    OnTaskDoneListner taskDoneListner;


public class MyViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.textView43)
    TextView tv_name;
    @BindView(R.id.textView44)
    TextView tv_task_name;
    @BindView(R.id.imageView10)
    ImageView iv_profile_pic;
    @BindView(R.id.et_active_task_hours)
    EditText et_active_hrs;
    @BindView(R.id.button13)
    Button btn_chat;
    @BindView(R.id.button10_taskDone)
    Button btn_taskDone;


//TextView tv;


    public MyViewHolder(View view) {
        super(view);
        // tv_name = view.findViewById(R.id.textView43);

        ButterKnife.bind(this, view);


    }
}


    public active_tasks_adp(List<active_tasks_data> taskList,Context context, OnTaskDoneListner taskDoneListner) {
        this.taskList = taskList;
        this.context =context;
        this.taskDoneListner =taskDoneListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_active_tasks, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        active_tasks_data actvie_task = taskList.get(position);
        holder.tv_name.setText(actvie_task.getTasker_name());
        holder.tv_task_name.setText(actvie_task.getTask_name());
        Glide.with(context).load(actvie_task.getProfile_pic()).into(holder.iv_profile_pic);
        holder.btn_taskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.et_active_hrs.getText().length() >= 1){

                taskDoneListner.onBtnTaskDone(actvie_task.getBooking_id(),holder.et_active_hrs.getText().toString());
                }else {
                    holder.et_active_hrs.setError("Enter the number of hours the Task took to complete.");
                }

            }
        });


        holder.btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDoneListner.onBtnChat(actvie_task.getBooking_id(),actvie_task.getTasker_name());
            }
        });

    }
    public interface OnTaskDoneListner{
    void onBtnTaskDone(String booking_id, String task_hour);
        void onBtnChat(String booking_id, String name);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


