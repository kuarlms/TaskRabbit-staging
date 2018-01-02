package killbit.taskrabbit.objects;


import android.app.Activity;

public class ProgressDialog {

    private Activity _activity;
    android.app.ProgressDialog ringProgressDialog;

    public ProgressDialog(Activity _activity){

        this._activity = _activity;
    }

    public void ShowTheDialog(String title,String message,Boolean Cancelable){

      //  ringProgressDialog = ProgressDialog.show(_activity, title, message, true);

        ringProgressDialog = new android.app.ProgressDialog(_activity, android.app.ProgressDialog.THEME_HOLO_DARK);
// set indeterminate style
        ringProgressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
// set title and message
        ringProgressDialog.setTitle(title);
        ringProgressDialog.setMessage(message);
// and show it
        ringProgressDialog.show();
        ringProgressDialog.setCancelable(Cancelable);

    }
    public void DismissTheDialog(){

        if(ringProgressDialog.isShowing()){

            ringProgressDialog.dismiss();
        }
    }

}