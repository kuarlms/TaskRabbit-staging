package killbit.taskrabbit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.active_tasks_data;

/**
 * Created by kural on 11/10/17.
 */

public class my_account_items extends RecyclerView.Adapter<my_account_items.MyViewHolder> {

private List<active_tasks_data> taskList;

public class MyViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.textView43)
    TextView tv_name;
    @BindView(R.id.textView44)
    TextView tv_task_name;
    @BindView(R.id.textView2)
    TextView tv_user_time;
    @BindView(R.id.textView45)
    TextView tv_location;
    @BindView(R.id.textView4)
    TextView tv_active_time;
    @BindView(R.id.textView5)
    TextView tv_vehicle;

//TextView tv;


    public MyViewHolder(View view) {
        super(view);
        // tv_name = view.findViewById(R.id.textView43);

        ButterKnife.bind(this, view);


    }
}


    public my_account_items(List<active_tasks_data> taskList) {
        this.taskList = taskList;
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
        holder.tv_name.setText(actvie_task.getActive_time());
       /* holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


