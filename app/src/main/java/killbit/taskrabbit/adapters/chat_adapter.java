package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatMessage;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatUserInfo;
import killbit.taskrabbit.utils.GlideApp;
import killbit.taskrabbit.utils.sp_task;


/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.MyViewHolder>{


    private List<ChatMessage> ListDatas;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;
    ChatMessage DataItem;

    List <ChatUserInfo> chatUserInfox;
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
    LinearLayout.LayoutParams.WRAP_CONTENT);



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        ImageView iv_review_pic1,ivChatImg;
        TextView tv_chatmsg;
        LinearLayout ll_rev1;

        public MyViewHolder(View view, Context context) {
         super(view);


             iv_review_pic1=view.findViewById(R.id.iv_adp_chat_pic);
             ll_rev1=view.findViewById(R.id.llChatParent);
             tv_time = view.findViewById(R.id.tv_adp_chat_time);
             tv_chatmsg=view.findViewById(R.id.tv_adp_chat_text);
             ivChatImg=view.findViewById(R.id.iv_adp_chat_attachedImage);


        }
    }


    public chat_adapter(List<ChatMessage> ListDatas, Context context, OnRecyclerListener recyclerListener
    , List<ChatUserInfo> chatUserInfo) {

        this.ListDatas = ListDatas;
        this.context = context;
        this.recyclerListener= recyclerListener;
        this.chatUserInfox=chatUserInfo;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adp_chat, parent, false);
        sharedpreferences =  context.getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {

        DataItem = ListDatas.get(position);

        lp.gravity = Gravity.RIGHT;


            if(DataItem.getPosition().equalsIgnoreCase("left")){

                holder.tv_chatmsg.setBackgroundResource(R.drawable.button_background);
                holder.tv_chatmsg.setTextColor(ContextCompat.getColor(context,(R.color.white)));

                try {
                    GlideApp.with(context).load(chatUserInfox.get(0).getProfileImage())
                            .error(R.mipmap.ic_launcher)
                            .placeholder(R.drawable.com_facebook_profile_picture_blank_portrait)
                            .circleCrop()
                            .into(holder.iv_review_pic1);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }else {
                holder.tv_chatmsg.setLayoutParams(lp);
               /* GlideApp.with(context).load(sharedpreferences.getString(sp_task.Sp_profile_pic,""))
                        .circleCrop().into(holder.iv_review_pic1);*/

            }






        holder.tv_chatmsg.setText(DataItem.getMessage());

        holder.tv_time.setText(DataItem.getCreatedTime());


    }



    public interface OnRecyclerListener {
        void onChatItemSelected(int position, String Booking_id, String TaskerName);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
