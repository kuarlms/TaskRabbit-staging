package killbit.taskrabbit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.active_tasks_data;
import killbit.taskrabbit.retrofit.taskHistoryComplete.TaskPendingArray;
import killbit.taskrabbit.utils.GlideApp;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural on 11/10/17.
 */

public class taskHistory_completed_adapter extends RecyclerView.Adapter<taskHistory_completed_adapter.MyViewHolder> {

private List<TaskPendingArray> taskList;
Context context;
OnTaskDoneListner taskDoneListner;


public class MyViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.tv_thp_time)
    TextView tv_time;
    @BindView(R.id.textView2)
    TextView tv_description;
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
    @BindView(R.id.ll_postReview)
    LinearLayout llPostReveiw;
    @BindView(R.id.tv_postedreviewTwxt)
    TextView tvPostedText;
    @BindView(R.id.ratingBarCompleated)
    RatingBar ratingBar;



//TextView tv;


    public MyViewHolder(View view) {
        super(view);
        // tv_name = view.findViewById(R.id.textView43);

        ButterKnife.bind(this, view);


    }
}


    public taskHistory_completed_adapter(List<TaskPendingArray> taskList, Context context, OnTaskDoneListner taskDoneListner) {
        this.taskList = taskList;
        this.context =context;
        this.taskDoneListner =taskDoneListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_task_history_completed, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaskPendingArray actvie_task = taskList.get(holder.getAdapterPosition());

        holder.tv_name.setText(actvie_task.getFirstName());
        holder.tv_task_name.setText(actvie_task.getCatName());
       // Glide.with(context).load(actvie_task.getProPic()).apply(bitmapTransform(new CircleCrop())).into(holder.iv_profile_pic);
        GlideApp.with(context).load(actvie_task.getProPic()).circleCrop().into(holder.iv_profile_pic);
        holder.tv_description.setText(actvie_task.getCity());
        holder.tv_time.setText(actvie_task.getBookingTime());
        holder.btn_chat.setText(actvie_task.getBookingDay()+System.getProperty("line.separator")+actvie_task.getBookingMonth());

        if(actvie_task.getReviewDone().equalsIgnoreCase("yes")){

            holder.tvPostedText.setVisibility(View.GONE);
            holder.llPostReveiw.setVisibility(View.VISIBLE);

            int rat1 = 0 ;
            holder.ratingBar.setRating(rat1);
            try {
                rat1  = Integer.parseInt(actvie_task.getReviewStar());
                holder.ratingBar.setRating(rat1);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            holder.et_active_hrs.clearFocus();
            holder.ratingBar.setIsIndicator(true);
            holder.et_active_hrs.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

            holder.et_active_hrs.setBackground(null);
            holder.et_active_hrs.setFocusable(false);
            holder.et_active_hrs.setText(actvie_task.getReviewMessage());
            holder.btn_taskDone.setVisibility(View.GONE);

        }else {
            holder.ratingBar.setIsIndicator(false);
        }

      //  holder.ratingBar.setIsIndicator(false);
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
               // Toast.makeText(context, ""+v, Toast.LENGTH_SHORT).show();
            }
        });


        holder.btn_taskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.et_active_hrs.getText().length() > 10 ){

                    if(holder.ratingBar.getRating() <= 0){
                        Toast.makeText(context, "Please select a Rating.", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                taskDoneListner.onBtnTaskDone(actvie_task.getBookingId(),holder.et_active_hrs.getText().toString(),""+holder.ratingBar.getRating());}
                }else {
                    holder.et_active_hrs.setError("Enter the your review and post.");
                }

            }
        });


        holder.btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDoneListner.onBtnChat(actvie_task.getBookingId(),position);
            }
        });

        holder.tvPostedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.llPostReveiw.setVisibility(View.VISIBLE);

            }
        });

    }
    public interface OnTaskDoneListner{
    void onBtnTaskDone(String booking_id, String task_hour, String reviewvalues);
    void onBtnChat(String booking_id, int position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


