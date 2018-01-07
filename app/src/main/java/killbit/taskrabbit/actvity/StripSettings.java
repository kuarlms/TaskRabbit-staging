package killbit.taskrabbit.actvity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;
import okhttp3.MultipartBody;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 1/6/18.
 */

public class StripSettings extends Activity{

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;
    Activity _activity;
    String Email;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;
    @BindView(R.id.pb_account_loading)
    ProgressBar pb;

    @BindView(R.id.webview_stripe)
    WebView vistaWeb;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_stripe);
        ButterKnife.bind(this);
        _activity =this;

        tv_title.setText("Stripe Connect");
        pb.setVisibility(View.GONE);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();
        Email = sp.getString(sp_task.Sp_email,"");

    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @OnClick(R.id.btn_account)
    void btn_connect(){
        vistaWeb.loadUrl(ApiUtils.BASE_URL+"/stripe_connect");
        vistaWeb.setWebChromeClient(new WebChromeClient());
        vistaWeb.clearCache(true);
        vistaWeb.clearHistory();
        vistaWeb.getSettings().setJavaScriptEnabled(true);
        vistaWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
       // vistaWeb.setHttpAuthUsernamePassword (String host, String realm, String username, String password)


    }
}
