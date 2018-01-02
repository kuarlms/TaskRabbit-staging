package killbit.taskrabbit.utils;

import android.app.Application;

import killbit.taskrabbit.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by kural on 12/6/17.
 */

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("src/main/assets/fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }



}
