package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.signIn.LoginResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class Login extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {

    Intent in_signup;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    SignInButton GSignInButton;
    private String TAG = "gsin";
    CallbackManager mCallbackManager;
    LoginButton loginButton;
    Button btn_sigin_email,btn_signup_email;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    int RC_SIGN_IN = 9001;
    Intent i ;
    ApiInterface  mAPIService = ApiUtils.getAPIService();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Facebook Login button
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        i = new Intent(Login.this,MainActivity.class);


        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        btn_sigin_email = findViewById(R.id.button8);
        btn_signup_email = findViewById(R.id.button7);

        GSignInButton = findViewById(R.id.btn_google_sign_in);
         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(Login.this, this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        GSignInButton.setSize(SignInButton.COLOR_DARK);

        GSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



        loginButton = findViewById(R.id.button6);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);

            }
        });
            btn_sigin_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    in_signup = new Intent(Login.this,Signup_email.class);
                    startActivity(in_signup);

                }
            });

        btn_signup_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                in_signup = new Intent(Login.this,SignIn_email.class);
                startActivity(in_signup);
            }
        });


    }


    private void handleFacebookAccessToken(AccessToken accessToken) {
        Toast.makeText(this, ""+accessToken.getUserId(), Toast.LENGTH_SHORT).show();
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {


            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Bundle bFacebookData = getFacebookData(object);
                Log.d("fb",object.toString());

            }});
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();

        }
    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            URL profile_pic;
            try {

                 profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
                editor.putString(sp_task.Sp_profile_pic, profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            editor.putString(sp_task.Sp_name,object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
                editor.putString(sp_task.Sp_email,object.getString("email"));
                editor.putBoolean(sp_task.Sp_IsLoggedIn,true);

            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
        //    editor.commit();

            mtd_social_login(object.getString("email"),object.getString("first_name"), profile_pic.toString());

            return bundle;
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing JSON");
        }
        return null;
    }

    void mtd_social_login(String email,String name, String profile_pic){

        mAPIService.rf_signIn_social(ApiInterface.header_value, email.toLowerCase()).enqueue(new Callback<LoginResp>() {

            @Override
            public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {

                if (response.body().getStatus().equals(1)) {

                    editor.putString(sp_task.Sp_profile_pic, profile_pic);
                    editor.putString(sp_task.Sp_name, name);
                    editor.putString(sp_task.Sp_email, email);
                    editor.putBoolean(sp_task.Sp_IsLoggedIn, true);
                    editor.commit();

                    startActivity(i);
                    finish();


                } else if (response.body().getStatus().equals(0)) {

                    Toast.makeText(Login.this,response.body().getMessage() , Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<LoginResp> call, Throwable t) {

                Toast.makeText(Login.this, "Login Failed....Check your connectivity.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
        /*    editor.putString(sp_task.Sp_email,acct.getEmail());
            editor.putString(sp_task.Sp_name,acct.getDisplayName());
            editor.putString(sp_task.Sp_profile_pic, String.valueOf(acct.getPhotoUrl()));
            editor.putBoolean(sp_task.Sp_IsLoggedIn,true);
            editor.commit();*/
            /*startActivity(i);
            finish();*/

            mtd_social_login(acct.getEmail(),acct.getDisplayName(),String.valueOf(acct.getPhotoUrl()));

           // Toast.makeText(Login.this, ""+acct.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        Toast.makeText(Login.this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        Toast.makeText(Login.this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sp.getBoolean(sp_task.Sp_IsLoggedIn,false)){
            finish();
        }

    }
}
