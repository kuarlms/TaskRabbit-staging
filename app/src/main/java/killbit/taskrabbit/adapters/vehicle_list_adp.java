package killbit.taskrabbit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.vehicle_list_data;

/**
 * Created by kural on 11/10/17.
 */

public class vehicle_list_adp extends RecyclerView.Adapter<vehicle_list_adp.MyViewHolder> {

    List<vehicle_list_data> Vehil_List;
    OnRecyclerListener recyclerListener;
    Context context;


public class MyViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.textView_adp_vehicle_list_name)
    TextView tv_vehicle;

//TextView tv;


    public MyViewHolder(View view) {
        super(view);
        // tv_name = view.findViewById(R.id.textView43);

        ButterKnife.bind(this, view);


    }
}


    public vehicle_list_adp(List<vehicle_list_data> Vehil_List, OnRecyclerListener onRecyclerListener, Context applicationContext) {
        this.Vehil_List = Vehil_List;
        this.recyclerListener=onRecyclerListener;
        this.context=applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_vehicle_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        vehicle_list_data vehicle_data = Vehil_List.get(position);
        holder.tv_vehicle.setText(vehicle_data.getVehicle_name());

        holder.tv_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(context, ""+vehicle_data.getVehicle_name(), Toast.LENGTH_SHORT).show();
                recyclerListener.onItemClickedVehicle(position,vehicle_data.getVehicle_name());

            }
        });
    }
    public interface OnRecyclerListener {
        void onItemClickedVehicle(int position, String data);

    }
    @Override
    public int getItemCount() {
        return Vehil_List.size();
    }
}


