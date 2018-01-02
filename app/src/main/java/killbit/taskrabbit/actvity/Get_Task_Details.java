package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.sgiosviews.SGPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.date_selector_adapter;
import killbit.taskrabbit.adapters.tasker_details_adapter;
import killbit.taskrabbit.adapters.vehicle_list_adp;
import killbit.taskrabbit.objects.ProgressDialog;
import killbit.taskrabbit.objects.date_obj;
import killbit.taskrabbit.objects.tasker_list_data;
import killbit.taskrabbit.objects.vehicle_list_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.bookingStep1.bookingStep1Resp;
import killbit.taskrabbit.retrofit.findTasker.FindTaskerResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */




public class Get_Task_Details extends Activity implements Validator.ValidationListener,
        date_selector_adapter.OnRecyclerListener,vehicle_list_adp.OnRecyclerListener, tasker_details_adapter.OnRecyclerListener {

    View BottomView;
    TextView tv_when;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rv_vehice_req,rv_tasker_details;
    ProgressDialog pd;
    EditText et_address,et_address_city;
    Button btn_address_done;
    Dialog  tasker_details;
    String vehicle_id ,email,task_date,task_time,city,adress,page;
    String vehicle_details,task_description;
    tasker_details_adapter tasker_adapter;
    ArrayList<String>sub_cat_list = new ArrayList<>();
    ArrayList<String>sub_cat_list_id = new ArrayList<>();

    List<vehicle_list_data> vehicle_list = new ArrayList<>();
    vehicle_list_data vehicle_data;

    List<tasker_list_data> tasker_list = new ArrayList<>();
    tasker_list_data tasker_data;


    ArrayList<String> time_lis = new ArrayList<>();
    ArrayList<String> time_lis_id = new ArrayList<>();

    vehicle_list_adp vehicle_adp;

    String main_cat,sub_cat_id;
    SGPickerView  pickerView;
    //View view = LayoutInflater.from(context).inflate(R.layout.thing, null);
    Dialog dialouge_task,dialouge_when,dialouge_vehicle,dialog_address;
    @NotEmpty
    @BindView(R.id.textView1x6)
    EditText textView_task_address;

    @NotEmpty
    @BindView(R.id.textView1)
    TextView textView_task_when;

    @NotEmpty
    @BindView(R.id.textView16)
    TextView textView_task_details;

    @NotEmpty
    @BindView(R.id.textView1zx6)
    TextView textView_task_vehicle;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.spinner_task)
    Spinner spin_sub_cat_list;

    @BindView(R.id.card_vehice)
    CardView cardView_vehicle;





    Validator validator;
    ApiInterface mAPIService;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_task_deatils);
        ButterKnife.bind(this);






        validator = new Validator(this);
        validator.setValidationListener(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        textView_task_address.clearFocus();


    }





    @OnClick(R.id.button_done)
    public void btn_submit(){


        email = sp.getString(sp_task.Sp_email,"");


        mtd_find_tasker();



    }

    private void mtd_find_tasker() {
        if(task_date != null  && task_time != null) {


            page = "1";

            if(task_description!=null){

            }else {
                Toast.makeText(this, "Fill in task description to proceed...", Toast.LENGTH_SHORT).show();
                return;


            }
            if(city!=null)
            {}else {
                Toast.makeText(this, "Fill in city and address to proceed...", Toast.LENGTH_SHORT).show();
                return;

            }


       /*     if(vehicle_list.isEmpty()){

            }else {
                if(vehicle_id == null){
                    Toast.makeText(this, "Select Vehicle type...", Toast.LENGTH_LONG).show();
                    return;
                }
            }*/



            pd = new ProgressDialog(this);
            pd.ShowTheDialog("Loading...", "please wait...", false);


            mAPIService.rf_find_tasker(ApiInterface.header_value, sp.getString(sp_task.Sp_email, ""),
                    main_cat, sub_cat_id,task_date,task_time,city,page,vehicle_id)
                    .enqueue(new Callback<FindTaskerResp>() {


                        @Override
                        public void onResponse(Call<FindTaskerResp> call, Response<FindTaskerResp> response) {



                            if(response.body().getStatus().equals(1)){


                                for (int i = 0; i < response.body().getTaskerList().size(); i++) {
                                    tasker_data = new tasker_list_data(response.body().getTaskerList().get(i).getTaskerId(),
                                            response.body().getTaskerList().get(i).getProPic(),
                                            response.body().getTaskerList().get(i).getFirstName(),
                                            response.body().getTaskerList().get(i).getLastName(),
                                            response.body().getTaskerList().get(i).getReviewResponseRate(),
                                            response.body().getTaskerList().get(i).getIdVerified(),
                                            response.body().getTaskerList().get(i).getCurrencySymbol(),
                                            response.body().getTaskerList().get(i).getPrice(),
                                            response.body().getTaskerList().get(i).getServicePercentage(),
                                            response.body().getTaskerList().get(i).getAbout(),
                                            response.body().getTaskerList().get(i).getServiceStartYear(),
                                            response.body().getTaskerList().get(i).getTaskDone(),
                                            response.body().getTaskerList().get(i).getDetail1(),
                                            response.body().getTaskerList().get(i).getDetail2(),
                                            response.body().getTaskerList().get(i).getDetail3(),
                                            response.body().getTaskerList().get(i).getReviewArray(),
                                            response.body().getTaskerList().get(i).getSupplyArray());

                                    tasker_list.add(tasker_data);
                                }
                                mtd_taskers();}
                                else {
                                Toast.makeText(Get_Task_Details.this, "Taskers not available..."+response.body().getStatus(),                               Toast.LENGTH_SHORT).show();
                            }
                               // tasker_adapter.notifyDataSetChanged();







                        }

                        @Override
                        public void onFailure(Call<FindTaskerResp> call, Throwable t) {

                        }
                    });

            pd.DismissTheDialog();
        }else {
            Toast.makeText(this, "Fill time details...", Toast.LENGTH_SHORT).show();
        }



    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void mtd_taskers() {
        tasker_details = new Dialog(this, R.style.AppTheme_NoActionBar);

        tasker_details.setContentView(R.layout.tasker_recycler);
        tasker_details.setCancelable(false);
        rv_tasker_details = tasker_details.findViewById(R.id.recycleView_tasker_details);
        ImageButton iv_back = tasker_details.findViewById(R.id.tb_normal_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasker_details.dismiss();
            }
        });
        TextView tv_title = tasker_details.findViewById(R.id.tb_normal_title);
        tv_title.setText(getIntent().getStringExtra("sub_cat"));



        rv_tasker_details.setHasFixedSize(true);
        rv_tasker_details.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        tasker_adapter = new tasker_details_adapter(tasker_list,getApplicationContext(),Get_Task_Details.this);
        rv_tasker_details.setAdapter(tasker_adapter);
        tasker_details.show();


    }


    @OnClick({R.id.textView16,R.id.card_task})
    public void on_click_task_details(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_task_details,null);
        dialouge_task = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_task.setContentView(BottomView);

        TextView tv_heading = dialouge_task.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Task Details");
        EditText et_task_details =  dialouge_task.findViewById(R.id.et_dia_task_details);

        Button btn_done = dialouge_task.findViewById(R.id.button_task_dialouge);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_task_details.getText().toString().trim().length() > 14) {

                    task_description = et_task_details.getText().toString().trim();
                    textView_task_details.setText(task_description);
                    dialouge_task.dismiss();

                }else {
                    et_task_details.setError("Minimum length 15 characters.");
                }


            }
        });
        dialouge_task.setCancelable(false);

        dialouge_task.show();
    }




    @OnClick({R.id.card_task_address,R.id.textView1x6})
    public void card_task_address(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_task_address_new,null);
        dialog_address = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialog_address.setContentView(BottomView);
        dialog_address.setCancelable(false);
        TextView tv_heading = dialog_address.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Task Address");

        et_address = dialog_address.findViewById(R.id.et_dia_task_address);
        et_address.setFocusable(false);
        et_address.setHint("Enter your home town/city below. ");
        et_address.setVisibility(View.INVISIBLE);
        et_address_city = dialog_address.findViewById(R.id.et_dia_task_address_city);
        btn_address_done = dialog_address.findViewById(R.id.button_task_dialouge);

        if(adress!= null ){
            et_address.setText(adress);
        }

        if(city!=null ){
            et_address_city.setText(city);

        }
        et_address.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == EditorInfo.IME_ACTION_GO || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    et_address_city.requestFocus();
                }
                return false;
            }
        });


        et_address_city.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == EditorInfo.IME_ACTION_GO || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    btn_address_done.performClick();
                }
                return false;
            }
        });

        btn_address_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   if(et_address.getText().length() <= 14){

                    et_address.setError("Minimum length 15 characters.");
                    return;
                }
*/

                if(et_address_city.getText().length() <= 2 ){
                    et_address_city.setError("Required");
                    return;
                }else {
                    city = et_address_city.getText().toString().trim();
                    dialog_address.dismiss();
                }

                textView_task_address.setText(et_address.getText()+" - "+et_address_city.getText());

                dialog_address.dismiss();
            }
        });


        dialog_address.show();
    }

    @OnClick({R.id.card_vehice,R.id.textView1zx6})
    public void card_vehice(){
        BottomView = getLayoutInflater().inflate(R.layout.dailouge_vechicle_requirement,null);
        dialouge_vehicle = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_vehicle.setContentView(BottomView);
        dialouge_vehicle.setCancelable(false);
        TextView tv_heading = dialouge_vehicle.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Vehicle Requirement");
        Button btn_vehicle_done = dialouge_vehicle.findViewById(R.id.btn_vehicle_done);


        rv_vehice_req = dialouge_vehicle.findViewById(R.id.rv_vehice_req);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_vehice_req.setLayoutManager(mLayoutManager);
        rv_vehice_req.setItemAnimator(new DefaultItemAnimator());
        vehicle_adp = new vehicle_list_adp(vehicle_list,this,getApplicationContext());
        rv_vehice_req.setAdapter(vehicle_adp);

        if( vehicle_list.isEmpty()){

            Toast.makeText(this, "Vehicle not required...", Toast.LENGTH_SHORT).show();

        }else {
            dialouge_vehicle.show();


        }

        btn_vehicle_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vehicle_details = textView_task_vehicle.getText().toString();

                if(vehicle_details!= null){
                    dialouge_vehicle.dismiss();
                }else {
                    textView_task_vehicle.setText("Vehicle not required.");
                    //textView_task_vehicle.setError("Please select a vehicle type.");
                    dialouge_vehicle.dismiss();
                }


            }
        });




    }


    @OnClick({R.id.card_when,R.id.textView1})
    public void card_when(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_when_time,null);


        dialouge_when = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_when.setContentView(BottomView);
        dialouge_when.setCancelable(false);
        TextView tv_heading = dialouge_when.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("When");
        date_selector_adapter rv_adapter;
        tv_when = dialouge_when.findViewById(R.id.tv_dialouge_when_day);
        RecyclerView rv_date = dialouge_when.findViewById(R.id.rv_task_when_grid);
        List<date_obj> List_dates = new ArrayList<>();
        pickerView = dialouge_when.findViewById(R.id.pickerView);
        pickerView.setItems(time_lis);
        Button btn_when_done = dialouge_when.findViewById(R.id.button_task_dialouge);

        pickerView.setPickerListener(new SGPickerView.SGPickerViewListener() {
            @Override
            public void itemSelected(String item, int index) {

            /*    pickerView.getCurrentSelectedItemIndex();
                pickerView.getCurrentSelectedItem();*/
            task_time = time_lis_id.get(pickerView.getCurrentSelectedItemIndex());



            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-EEEE-dd ");
        Calendar calendar_month = new GregorianCalendar();

            for (int i = 0; i < 7; i++) {

            calendar_month.add(Calendar.DAY_OF_WEEK, 1);
            String month = sdf.format(calendar_month.getTime());
            int year1 = calendar_month.get(Calendar.YEAR);
            String year=String.valueOf(year1);
            String[] sep = month.split("-");
            date_obj  dates_s = new date_obj(sep[0],sep[1],sep[2],year);
            List_dates.add(dates_s);

        }


        rv_date.setHasFixedSize(true);
        rv_date.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_adapter = new date_selector_adapter(List_dates,getApplicationContext(),Get_Task_Details.this);
        rv_date.setAdapter(rv_adapter);
        dialouge_when.show();
        rv_adapter.notifyDataSetChanged();

        btn_when_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task_time != null || task_date!=null){

                    dialouge_when.dismiss();
                }else {
                    Toast.makeText(Get_Task_Details.this, "Fill all details...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //card_task_address


    @Override
    protected void onStart() {
        super.onStart();
        tv_title.setText(getIntent().getStringExtra("sub_cat"));
        sub_cat_list.addAll(getIntent().getStringArrayListExtra("list_cat"));
        sub_cat_list_id.addAll(getIntent().getStringArrayListExtra("list_cat_ids"));
      //  Toast.makeText(this, ""+sub_cat_list_id, Toast.LENGTH_SHORT).show();

        main_cat = getIntent().getStringExtra("main_cat");
      //  Toast.makeText(this, "xx--"+main_cat, Toast.LENGTH_SHORT).show();


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sub_cat_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_sub_cat_list.setAdapter(adapter);


        spin_sub_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sub_cat_id = sub_cat_list_id.get(i);
                mtd_booking_step1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       /* sub_cat_id = sub_cat_list_id.get(spin_sub_cat_list.getSelectedItemPosition());

        mtd_booking_step1();*/

    }

    private void mtd_booking_step1() {

        pd = new ProgressDialog(this);
        pd.ShowTheDialog("Loading...","Please wait...",false);


        mAPIService.rf_booking_step1(ApiInterface.header_value,sp.getString(sp_task.Sp_email,""),main_cat,sub_cat_id)
                .enqueue(new Callback<bookingStep1Resp>() {


                    @Override
                    public void onResponse(Call<bookingStep1Resp> call, Response<bookingStep1Resp> response) {

                        for (int i = 0; i <response.body().getDropdownData().getTimingList().size() ; i++) {

                         time_lis.add(response.body().getDropdownData().getTimingList().get(i).getName());
                         time_lis_id.add(response.body().getDropdownData().getTimingList().get(i).getTimeId());

                        }
                 /*       Toast.makeText(Get_Task_Details.this, ""+time_lis, Toast.LENGTH_SHORT).show();
                     card_when();*/

                        if(response.body().getDropdownData().getVehicleList().size()!= 0){


                        for (int i = 0; i <response.body().getDropdownData().getVehicleList().size() ; i++) {

                            vehicle_data = new vehicle_list_data(response.body().getDropdownData().getVehicleList().get(i).getVehicleName(),
                                    response.body().getDropdownData().getVehicleList().get(i).getVehicleId());

                            vehicle_list.add(vehicle_data);
                        }
                        }else {
                            cardView_vehicle.setVisibility(View.GONE);
                        }
                    pd.DismissTheDialog();

                    }

                    @Override
                    public void onFailure(Call<bookingStep1Resp> call, Throwable t) {
                        Toast.makeText(Get_Task_Details.this, ""+t, Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemClicked(int position, String data,String formated_date) {

        tv_when.setText(data);
        task_date = formated_date;
        //Toast.makeText(Get_Task_Details.this, "Booking Failed Retry later."+formated_date, Toast.LENGTH_SHORT).show();

        textView_task_when.setText(data);



    }

    @Override
    public void onItemClickedVehicle(int position, String data) {
        //Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
        textView_task_vehicle.setText(data);
        vehicle_id = vehicle_list.get(position).getVehicle_id();
        dialouge_vehicle.dismiss();

    }

    @Override
    public void onTaskerSelected(int position, String TaskerId, String ProfilePic, String RatePer, String TaskerName) {
        Intent selectTasker = new Intent(Get_Task_Details.this, confirm_booking.class);
        Bundle datas ;

        datas = new Bundle();
        datas.putString("email",sp.getString(sp_task.Sp_email,""));
        datas.putString("cat_id",main_cat);
        datas.putString("subcat_id",sub_cat_id);
        datas.putString("task_date",task_date);
        datas.putString("task_time",task_time);
        datas.putString("city",city);
        if(vehicle_id!=null){
        datas.putString("vehicle_id",vehicle_id);}
        else {
            datas.putString("vehicle_id"," ");
        }

        datas.putString("task_description",task_description);
        datas.putString("tasker_id",TaskerId);
        datas.putString("ProfilePic",ProfilePic);
        datas.putString("RatePer",RatePer);
        datas.putString("TaskerName",TaskerName);


        selectTasker.putExtras(datas);

        tasker_details.dismiss();

        startActivity(selectTasker);
      //  finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

       finish();

    }
}
