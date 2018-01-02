package killbit.taskrabbit.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import killbit.taskrabbit.fragments.frag_home;
import killbit.taskrabbit.objects.data_main_home;
import killbit.taskrabbit.objects.data_sub_home;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */


public class FrgtPageAdapter extends FragmentPagerAdapter {


    Context mContext;
    List<data_main_home> list_main_cat = new ArrayList<>();


    public FrgtPageAdapter(FragmentManager fragmentManager, Context applicationContext, List<data_main_home> list_main_cat) {

        super(fragmentManager);
        this.mContext = applicationContext;
        this.list_main_cat = list_main_cat;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return list_main_cat.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        List<data_sub_home> list_sub_cat = new ArrayList<>();
        data_sub_home   sub_data;

        int i =position;

    //   Log.d("ixx--s",i+"-"+ list_main_cat.get(i).getCat_id());

        for (int j = 0; j <list_main_cat.get(i).getSub_data().size() ; j++) {

              sub_data = new data_sub_home(
              list_main_cat.get(i).getSub_data().get(j).getSubcat_id(),
              list_main_cat.get(i).getSub_data().get(j).getSubcat_name(),
              list_main_cat.get(i).getSub_data().get(j).getSubcat_image(),
              list_main_cat.get(i).getSub_data().get(j).getAvg_price()) ;

             list_sub_cat.add(sub_data);
         }

        return frag_home.newInstance(i, list_sub_cat,list_main_cat.get(i).getCat_id());
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        SpannableStringBuilder sb = new SpannableStringBuilder(list_main_cat.get(position).getCat_title()); // space added before text for convenience


     /*   Drawable drawable = null;
        Glide.with(mContext).load(list_main_cat.get(position).getCat_icon()).into(drawable);
*/

       /* try {
            drawable = drawableFromUrl(list_main_cat.get(position).getCat_icon());
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(mContext,Glide.with(mContext).asDrawable().load(list_main_cat.get(position).getCat_icon()), ImageSpan.ALIGN_BASELINE);
        //ImageSpan z = new ImageSpan(mContext, Glide.with(mContext).asBitmap().load(list_main_cat.get(position).getCat_icon()));
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);*/

        return sb;

     //   return "Page " + position;
    }
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }


}
