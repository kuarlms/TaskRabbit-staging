package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import killbit.taskrabbit.utils.sp_task;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public class Launcher extends AppCompatActivity {


    private static final int SPLASH_TIME_OUT = 100;
    public static Context contextOfApplication;
    Intent i ;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);



        if(sp.getBoolean(sp_task.Sp_IsLoggedIn,false)){
            i = new Intent(Launcher.this,MainActivity.class);
        }else {
            i = new Intent(Launcher.this,SignIn_email.class);
        }




        Intent2Activity();

    }

    private void Intent2Activity() {

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
