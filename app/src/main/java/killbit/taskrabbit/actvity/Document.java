package killbit.taskrabbit.actvity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.uploadPpic.UpdateAccountResp;
import killbit.taskrabbit.utils.GlideApp;
import killbit.taskrabbit.utils.sp_task;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural on 11/10/17.
 */

public class Document extends FragmentActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {


    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;
    Activity _activity;
    Uri Imageuri;
    MultipartBody.Part requestFilebtn;
    @BindView(R.id.pb_account_loading)
    ProgressBar pb;
    @BindView(R.id.tb_normal_title)
    TextView tv_title;
    @BindView(R.id.iv_account_pic)
    ImageView iv_pic;


    private static final int STORAGE_PERMISSION_REQUEST_CODE = 91;
    Dialog cropping_dialog;
    String Permission4;
    CropImageView mCropImageView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.actvity_document);
       ButterKnife.bind(this);

       Permission4  = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        _activity =this;

       tv_title.setText("Documents");
       pb.setVisibility(View.GONE);
       sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
       editor =sp.edit();
       mAPIService = ApiUtils.getAPIService();

        GlideApp.with(this).load(sp.getString(sp_task.Sp_profile_doc, ""))
               .error(R.drawable.ic_logo) .into(iv_pic);

         iv_pic.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                CheckAndRequestPermission();
            else
                permissionGranted();



        }
    });


    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }
    @OnClick({R.id.btn_account})
    public void btn_save(){



    }


    public void CheckAndRequestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) { // Marshmallow+
            if (checkPermission(Permission4)) {
                // Intent2Activity();
                return;
            }
            requestPermission(Permission4);
        } else {
            // Intent2Activity();
        }

    }

    private boolean checkPermission(String permission) {

        int result = ContextCompat.checkSelfPermission(_activity, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            permissionGranted();
            return true;
        } else {

            return false;
        }
    }

    private void permissionGranted() {
        cropping_dialog = new Dialog(this, R.style.com_facebook_auth_dialog);
        cropping_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        cropping_dialog.setContentView(R.layout.cropping_image_document);


        mCropImageView = (CropImageView) cropping_dialog.findViewById(R.id.croppingImageView);
        init();

        CropImage.startPickImageActivity(this);

        Button close = (Button) cropping_dialog.findViewById(R.id.btn_ci_exit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cropping_dialog.dismiss();

                // onBackPressed();
            }
        });
        ImageButton rotate = (ImageButton) cropping_dialog.findViewById(R.id.rotate);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCropImageView.rotateImage(90);
            }
        });

        Button crop = (Button) cropping_dialog.findViewById(R.id.cropping);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCropImageView.getCroppedImageAsync();

            }
        });
    }

    private void requestPermission(String Permission) {
        ActivityCompat.requestPermissions(_activity, new String[]{Permission}, STORAGE_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE &&
                resultCode == AppCompatActivity.RESULT_OK && data != null && data.getData() != null) {

            Imageuri = data.getData();

            cropping_dialog.show();


            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            mCropImageView.setImageUriAsync(imageUri);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted();
                    //Intent2Activity();
                } else {
                   /*CheckAndRequestPermission();
                    Toast.makeText(_context, "Kindly, give storage permission to store and" +
                            " access the video and audio", Toast.LENGTH_SHORT).show();*/
                    //  this.finish();
                    Toast.makeText(getApplicationContext(), "Kindly, give storage permission to store and" +
                            " access the video and audio", Toast.LENGTH_SHORT).show();
                    // CheckAndRequestPermission();

                }
                break;

        }
    }

    private void init() {

        mCropImageView.setOnSetImageUriCompleteListener(this);
        mCropImageView.setOnCropImageCompleteListener(this);


    }


    @Override
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null) {


        }else {
            Toast.makeText(this, "Image loading failed "+error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        if(result != null){
            MultipartBody.Part body = null;
            File f = new File(getApplicationContext().getCacheDir(), "ProfilePicx.jpg");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Convert bitmap to byte array
            FileOutputStream fos;
            Bitmap bitmaps =result.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmaps.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            try {
                fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/form-data"), f);
                body = MultipartBody.Part.createFormData("upload_document_picture", f.getName(), requestFile);

                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }






            mtd_updateProfile(body);
            cropping_dialog.dismiss();
        }
    }


    private void mtd_updateProfile(MultipartBody.Part requestFile) {



                mAPIService.rf_uploadDocument(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""),
                requestFile).enqueue(new Callback<UpdateAccountResp>() {
            @Override
            public void onResponse(Call<UpdateAccountResp> call, Response<UpdateAccountResp> response) {

                if (response.body().getStatus().equals(1)){
                    Toast.makeText(_activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();



                    editor.putString(sp_task.Sp_profile_doc,response.body().getPro_pic_doc());

                    editor.commit();
                    GlideApp.with(_activity).load(sp.getString(sp_task.Sp_profile_doc, ""))
                            .error(R.drawable.ic_logo) .into(iv_pic);


                }else {
                    Toast.makeText(_activity, "Failed retry later..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateAccountResp> call, Throwable t) {

            }
        });




    }
}
