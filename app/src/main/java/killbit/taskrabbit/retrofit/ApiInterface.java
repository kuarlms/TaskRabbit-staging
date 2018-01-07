package killbit.taskrabbit.retrofit;

import android.content.Context;
import android.preference.PreferenceManager;

import killbit.taskrabbit.actvity.Launcher;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatResp;
import killbit.taskrabbit.retrofit.MyServices.MyServicesResp;
import killbit.taskrabbit.retrofit.activeTasks.ActiveTaskResp;
import killbit.taskrabbit.retrofit.bookingConfirmation.bookingConfirmation;
import killbit.taskrabbit.retrofit.bookingStep1.bookingStep1Resp;
import killbit.taskrabbit.retrofit.findTasker.FindTaskerResp;
import killbit.taskrabbit.retrofit.forgotPass.ForgoPassResp;
import killbit.taskrabbit.retrofit.home.Home_Resp;
import killbit.taskrabbit.retrofit.inbox.InboxResp;
import killbit.taskrabbit.retrofit.signIn.LoginResp;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import killbit.taskrabbit.retrofit.taskHistoryComplete.TaskHistoryCompleted;
import killbit.taskrabbit.retrofit.transactionList.transactionsResp;
import killbit.taskrabbit.retrofit.uploadPpic.UpdateAccountResp;
import killbit.taskrabbit.utils.sp_task;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public interface ApiInterface {
    Context applicationContext = Launcher.getContextOfApplication();
    sp_task sharedPrefferences = (sp_task) PreferenceManager.getDefaultSharedPreferences(applicationContext);

  static   String header = "app_id";
  static   String header_value = "pictus_service_rabbit_01";

 /* @POST("/commandService/addDevice")
  void Registration(@Body RegRequest body, Callback<RegResp> callback);*/

    @Multipart
    @POST("tasker_signup")
    Call<signupStatus> rf_signUp(@Header(header)String header_value,@Part("first_name") String first_name,
                        @Part("last_name") String last_name,
                        @Part("email") String email,
                        @Part("password") String password,
                        @Part("phone") String phone,
                        @Part("zipcode") String zipcode,
                        @Part("device_id")String device_id,
                        @Part("device_type")String device_type);

  @Multipart
  @POST("save_tasker_about_us")
  Call<signupStatus> rf_signUpMore(@Header(header)String header_value,
                                   @Part("work_city") String work_city,
                               @Part("home") String home,
                               @Part("email") String email,
                               @Part("street") String street,
                               @Part("city") String city,
                               @Part("state") String state,
                               @Part("zipcode") String zipcode,
                               @Part("dob")String dob,
                                   @Part("detail1")String detail1,
                                   @Part("detail2")String detail2,
                                   @Part("detail3")String detail3,
                                   @Part("hear_about")String hear_about,
                                   @Part("vehicle_types")String vehicle_types,
                                   @Part("tasker_step1")String tasker_step1,
                                   @Part("tasker_step2")String tasker_step2,
                               @Part("distance")String distance);

  @Multipart
  @POST("tasker_about_us")
  Call<signupStatus> rf_tasker_about_us(@Header(header)String header_value,
                                   @Part("work_city") String work_city,
                                   @Part("home") String home,
                                   @Part("email") String email,
                                   @Part("street") String street,
                                   @Part("city") String city,
                                   @Part("state") String state,
                                   @Part("zipcode") String zipcode,
                                   @Part("dob")String dob,
                                   @Part("detail1")String detail1,
                                   @Part("detail2")String detail2,
                                   @Part("detail3")String detail3,
                                   @Part("hear_about")String hear_about,
                                   @Part("vehicle_types")String vehicle_types,
                                   @Part("tasker_step1")String tasker_step1,
                                   @Part("tasker_step2")String tasker_step2,
                                   @Part("distance")String distance);

  @Multipart
  @POST("get_services_listing_inof")
  Call<MyServicesResp> rf_myServicesAdd(@Header(header)String header_value, @Part("email") String email);

  @Multipart
  @POST("get_blocked_dates")
  Call<StatusResp> rf_get_blocked_dates(@Header(header)String header_value, @Part("email") String email);

  @Multipart
  @POST("save_block_date")
  Call<StatusResp> rf_save_block_date(@Header(header)String header_value, @Part("email") String email,
                                          @Part("task_date") String task_date,
                                          @Part("task_time") String task_time);


 /* task_date [dates in comma ex: 2017-10-23,2017-10-24]
  task_time*/

  @Multipart
  @POST("tasker_login_process")
  Call<LoginResp> rf_signIn(@Header(header)String header_value, @Part("login_email") String login_email,
                            @Part("login_password") String login_password);


  @Multipart
  @POST("social_login_process")
  Call<LoginResp> rf_signIn_social(@Header(header)String header_value, @Part("email") String login_email);
    //https://stackoverflow.com/questions/39953457/how-to-upload-image-file-in-retrofit-2

  @Multipart
  @POST("upload_profile_picture")
  Call<UpdateAccountResp> rf_updateProfileDetails(@Header(header)String header_value,
                                                  @Part("email") String email,
                                                  @Part("first_name") String first_name,
                                                  @Part("last_name")String last_name,
                                                  @Part("phone")String phone,
                                                  @Part("zipcode")String zipcode);




  @Multipart
  @POST("upload_profile_picture")
  Call<UpdateAccountResp> rf_uploadPic(@Header(header)String header_value,@Part("email") String email,
                                       @Part MultipartBody.Part upload_profile_picture);

  @Multipart
  @POST("upload_tasker_document_picture")
  Call<UpdateAccountResp> rf_uploadDocument(@Header(header)String header_value,@Part("email") String email,
                                       @Part MultipartBody.Part upload_document_picture);


    @Multipart
    @POST("available_balance")
    Call<signupStatus> rf_avail_bal(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("change_user_password")
    Call<StatusResp> rf_password_change(@Header(header)String header_value,@Part("email") String email,
                                 @Part("old_password") String old_password,
                                 @Part("new_password") String new_password,
                                 @Part("confirm_password") String confirm_password);

    @Multipart
    @POST("forgot_password")
    Call<ForgoPassResp> rf_forgotPass(@Header(header)String header_value, @Part("email") String email);

    @Multipart
    @POST("deactivate_myaccount")
    Call<StatusResp> rf_Deact_account(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("save_notification")
    Call<StatusResp> rf_Notification(@Header(header)String header_value,@Part("email") String email,
                                       @Part("notify_type") String notify_type,@Part("task_email") String task_email
                                      ,@Part("task_sms") String task_sms);



    @Multipart
    @POST("home_page")
    Call<Home_Resp> rf_home_page(@Header(header)String header_value, @Part("email") String email);

    @Multipart
    @POST("booking_step1_new")
    Call<bookingStep1Resp> rf_booking_step1(@Header(header)String header_value, @Part("email") String email,
                                            @Part("cat_id") String cat_id, @Part("subcat_id") String subcat_id);

    @Multipart
    @POST("find_tasker")
    Call<FindTaskerResp> rf_find_tasker(@Header(header)String header_value, @Part("email") String email,
                                        @Part("cat_id") String cat_id, @Part("subcat_id") String subcat_id,
                                        @Part("task_date") String task_date, @Part("task_time") String task_time,
                                        @Part("city") String city, @Part("page") String page, @Part("vehicle_id") String vehicle_id);

    @Multipart
    @POST("booking_page")
    Call<signupStatus> rf_booking_page(@Header(header)String header_value,@Part("email") String email,
                                      @Part("cat_id") String cat_id,@Part("subcat_id") String subcat_id,
                                      @Part("task_date") String task_date,@Part("task_time") String task_time,
                                      @Part("city") String city,@Part("vehicle_id") String vehicle_id,
                                       @Part("tasker_id") String tasker_id);

    @Multipart
    @POST("save_booking_confirm")
    Call<bookingConfirmation> rf_booking(@Header(header)String header_value, @Part("email") String email,
                                         @Part("cat_id") String cat_id, @Part("subcat_id") String subcat_id,
                                         @Part("task_date") String task_date, @Part("task_time") String task_time,
                                         @Part("city") String city, @Part("vehicle_id") String vehicle_id,
                                         @Part("tasker_id") String tasker_id, @Part("task_description") String task_description,
                                         @Part("credit_card_type") String credit_card_type, @Part("number") String number,
                                         @Part("cvc") String cvc, @Part("exp_month") String exp_month, @Part("exp_year") String exp_year);

    @Multipart
    @POST("tasker_pending_task")
    Call<ActiveTaskResp> rf_dashboard_user_task_history_pending(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("tasker_approved_task")
    Call<ActiveTaskResp> rf_dashboard_user_task_history_approved(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("user_cancel_task")
    Call<StatusResp> rf_user_cancel_task(@Header(header)String header_value,@Part("email") String email,@Part("booking_id") String booking_id);

    @Multipart
    @POST("tasker_completed_task")
    Call<TaskHistoryCompleted> rf_dashboard_user_task_history_completed(@Header(header)String header_value, @Part("email") String email);

    @Multipart
    @POST("tasker_enquires_load_cancel")
    Call<ActiveTaskResp> rf_dashboard_user_task_history_Cancelled(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("tasker_active_task")
    Call<ActiveTaskResp> rf_user_active_task(@Header(header)String header_value, @Part("email") String email);

    @Multipart
    @POST("task_completed")
    Call<StatusResp> rf_task_completed(@Header(header)String header_value,@Part("email") String email,@Part("booking_id") String booking_id,
                                         @Part("task_hour") String task_hour);

    @Multipart
    @POST("tasker_transaction_list")
    Call<transactionsResp> rf_transaction_list(@Header(header)String header_value, @Part("email") String email, @Part("page") String page);

    @Multipart
    @POST("billing_info")
    Call<StatusResp> rf_billing_info(@Header(header)String header_value,@Part("email") String email,@Part("number") String number,
                                             @Part("cvc") String cvc,@Part("exp_month") String exp_month,@Part("exp_year") String exp_year);
      @Multipart
      @POST("inbox")
      Call<InboxResp> rf_inbox(@Header(header)String header_value, @Part("email") String email);

      @Multipart
      @POST("unreadmessage_count")
      Call<InboxResp> rf_unreadmessage_count(@Header(header)String header_value,@Part("email") String email);


    @Multipart
    @POST("save_review")
    Call<StatusResp> rf_save_review(@Header(header)String header_value,@Part("email") String email,
                            @Part("booking_id") String booking_id,@Part("review_star") String review_star
                            ,@Part("comments") String comments);

    @Multipart
    @POST("chat_inner")
    Call<ChatResp> rf_chat_inner(@Header(header)String header_value, @Part("email") String email, @Part("booking_id") String booking_id);

    @Multipart
    @POST("send_message")
    Call<StatusResp> rf_send_message(@Header(header)String header_value,@Part("email") String email,
                                   @Part("booking_id") String booking_id,@Part("message") String message);




}

