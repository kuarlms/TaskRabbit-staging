package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.tasker_list_data;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class tasker_details_adapter extends RecyclerView.Adapter<tasker_details_adapter.MyViewHolder>{

    private List<tasker_list_data> ListDatas;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;
    tasker_list_data listData;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_adp_tasker_name, tv_adp_tasker_review,
                tv_adp_tasker_iv_verifed,tv_adp_tasker_tearms_txt_description
                ,tv_adp_tasker_since,tv_adp_tasker_number_of_tasks,
                tv_adp_tasker_quick_notes,tv_adp_tasker_details1,
                tv_adp_tasker_details2,tv_adp_tasker_details3,
                tv_adp_tasker_pleople_said,tv_adp_tasker_review_btn,tv_adp_tasker_about,
                tv_adp_rew_name,tv_adp_rew_name2,tv_adp_rew_description,tv_adp_rew_description2
                ,tv_adp_rew_date,tv_adp_rew_date2;
        RecyclerView rv_tsker_reviews;
        ImageView iv_taker_pic,iv_review_pic1,iv_review_pic2;
        Button btn_tasker_adp_rate;
        LinearLayout ll_rev1,ll_rev2;
        RatingBar rbr1,rbr2;



        public MyViewHolder(View view, Context context) {
            super(view);

            iv_taker_pic = view.findViewById(R.id.iv_tasker_details_adp);
            tv_adp_tasker_name = view.findViewById(R.id.tv_adp_tasker_name);
            tv_adp_tasker_review = view.findViewById(R.id.tv_adp_tasker_review);
            tv_adp_tasker_iv_verifed = view.findViewById(R.id.tv_adp_tasker_iv_verifed);
            tv_adp_tasker_tearms_txt_description= view.findViewById(R.id.tv_adp_tasker_tearms_txt_description);
            tv_adp_tasker_since= view.findViewById(R.id.tv_adp_tasker_since);
            tv_adp_tasker_number_of_tasks= view.findViewById(R.id.tv_adp_tasker_number_of_tasks);
            tv_adp_tasker_quick_notes= view.findViewById(R.id.tv_adp_tasker_quick_notes);
            tv_adp_tasker_details1= view.findViewById(R.id.tv_adp_tasker_details1);
            tv_adp_tasker_details2= view.findViewById(R.id.tv_adp_tasker_details2);
            tv_adp_tasker_details3= view.findViewById(R.id.tv_adp_tasker_details3);
            tv_adp_tasker_pleople_said= view.findViewById(R.id.tv_adp_tasker_pleople_said);
            btn_tasker_adp_rate = view.findViewById(R.id.btn_tasker_adp_rate);
            tv_adp_tasker_review_btn= view.findViewById(R.id.tv_adp_tasker_review_btn);
            tv_adp_tasker_about =view.findViewById(R.id.tv_adp_tasker_about);
            iv_review_pic1=view.findViewById(R.id.iv_adp_rew_pic);
            iv_review_pic2=view.findViewById(R.id.iv_adp_rew_pic2);
            ll_rev1=view.findViewById(R.id.ll_rev1);
            ll_rev2=view.findViewById(R.id.ll_rev2);
            tv_adp_rew_name=view.findViewById(R.id.tv_adp_rew_name);
            tv_adp_rew_name2=view.findViewById(R.id.tv_adp_rew_name2);
            rbr1=view.findViewById(R.id.rattingbar);
            rbr2=view.findViewById(R.id.rattingbar2);
            tv_adp_rew_description=view.findViewById(R.id.tv_adp_rew_description);
            tv_adp_rew_description2=view.findViewById(R.id.tv_adp_rew_description2);
            tv_adp_rew_date=view.findViewById(R.id.tv_adp_rew_date);
            tv_adp_rew_date2=view.findViewById(R.id.tv_adp_rew_date2);
        }
    }


    public tasker_details_adapter(List<tasker_list_data> ListData, Context context, OnRecyclerListener recyclerListener) {

        this.ListDatas = ListData;
        this.context = context;
        this.recyclerListener= recyclerListener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adp_tasker_details, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
         listData = ListDatas.get(holder.getAdapterPosition());

        Glide.with(context).load(listData.getProPic()).apply(bitmapTransform(new CircleCrop())).into(holder.iv_taker_pic);
        holder.tv_adp_tasker_name.setText(listData.getFirstName());
        holder.btn_tasker_adp_rate.setText("Pick me! - "+listData.getCurrencySymbol()+listData.getPrice()+"/hr");
        holder.tv_adp_tasker_about.setText(listData.getAbout().toString());
       /*



        tv_adp_tasker_since= view.findViewById(R.id.tv_adp_tasker_since);
        tv_adp_tasker_number_of_tasks= view.findViewById(R.id.tv_adp_tasker_number_of_tasks);
        tv_adp_tasker_quick_notes= view.findViewById(R.id.tv_adp_tasker_quick_notes);
        tv_adp_tasker_details1= view.findViewById(R.id.tv_adp_tasker_details1);
        tv_adp_tasker_details2= view.findViewById(R.id.tv_adp_tasker_details2);
        tv_adp_tasker_details3= view.findViewById(R.id.tv_adp_tasker_details3);
        tv_adp_tasker_pleople_said= view.findViewById(R.id.tv_adp_tasker_pleople_said);
        btn_tasker_adp_rate = view.findViewById(R.id.btn_tasker_adp_rate);*/
       holder.tv_adp_tasker_review.setText(listData.getReviewResponseRate().toString()+ "  % Positive review");
       holder.tv_adp_tasker_iv_verifed.setText(listData.getIdVerified().toString());
       holder.tv_adp_tasker_tearms_txt_description.setText( "Tasks have a one-hour minimum. A "+listData.getServicePercentage()+" % Trust and Support fee is added to the tasker's total rate");
       holder.tv_adp_tasker_details1.setText(listData.getDetail1().toString());
        holder.tv_adp_tasker_details2.setText(listData.getDetail2().toString());
        holder.tv_adp_tasker_details3.setText(listData.getDetail3().toString());
        holder.tv_adp_tasker_since.setText("Been a Servicer since "+listData.getServiceStartYear().toString());
        holder.tv_adp_tasker_number_of_tasks.setText("I've done "+ listData.getTaskDone()+" Tasks");
        holder.tv_adp_tasker_quick_notes.setText("I respond quickly");
        holder.btn_tasker_adp_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("siva",listData.getTaskerId());

                recyclerListener.onTaskerSelected(position,listData.getTaskerId(),listData.getProPic(),
                        listData.getCurrencySymbol()+" "+listData.getPrice(),listData.getFirstName());

                Toast.makeText(context, ""+listData.getTaskerId()+listData.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_adp_tasker_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv_adp_tasker_pleople_said.requestFocus();
            }
        });


        if(listData.getReviewArray().size()== 2){

        mtd_reviews(holder);}
        else {
            holder.ll_rev1.setVisibility(View.GONE);
            holder.ll_rev2.setVisibility(View.GONE);
        }






    }

    private void mtd_reviews(MyViewHolder holder) {

        String pic1 = null,pic2 = null;

        if(listData.getReviewArray().get(0).getProPic()!=null){
            pic1 =listData.getReviewArray().get(0).getProPic();
        }else {
            holder.ll_rev1.setVisibility(View.GONE);
        }


        if(listData.getReviewArray().get(1).getProPic()!=null){
            pic2 =listData.getReviewArray().get(1).getProPic();
        }else {
            holder.ll_rev2.setVisibility(View.GONE);
        }

        Glide.with(context).load(pic1).apply(bitmapTransform(new CircleCrop())).into(holder.iv_review_pic1);
        Glide.with(context).load(pic2).apply(bitmapTransform(new CircleCrop())).into(holder.iv_review_pic2);

        int rat1 = 0,rat2 =0 ;
        try {
            rat1  = Integer.parseInt(listData.getReviewArray().get(0).getReviewStar());
            rat2  = Integer.parseInt(listData.getReviewArray().get(1).getReviewStar());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        holder.rbr1.setNumStars(rat1);
        holder.rbr2.setNumStars(rat2);

        holder.tv_adp_rew_name.setText(listData.getReviewArray().get(0).getFirstName());
        holder.tv_adp_rew_name2.setText(listData.getReviewArray().get(1).getFirstName());

        holder.tv_adp_rew_description.setText(listData.getReviewArray().get(0).getReviewMessage());
        holder.tv_adp_rew_description2.setText(listData.getReviewArray().get(1).getReviewMessage());

        holder.tv_adp_rew_date.setText(listData.getReviewArray().get(0).getDate());
        holder.tv_adp_rew_date2.setText(listData.getReviewArray().get(1).getDate());

    }

    public interface OnRecyclerListener {
        void onTaskerSelected(int position, String tasker_id,String Profile_pic, String RatePerHr,String TaskerName);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
