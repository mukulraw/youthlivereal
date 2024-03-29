package com.app.youthlive;

import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.youthlive.Activitys.UserInformation;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.app.youthlive.DBHandler.SessionManager;
import com.app.youthlive.login.ForgotPasswordActivity;
import com.app.youthlive.login2POJO.login2Bean;
import com.app.youthlive.socialPOJO.socialBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class Signin extends AppCompatActivity {
    public static final String MyPREFERENCES = "userSession";
    private static final String BASEELOGIN_URL = "http://ec2-13-127-59-58.ap-south-1.compute.amazonaws.com/softcode/api/mobile_signin.php";
    private static final String KEY_NUM = "number";
    private static final String BASEOTP_URL = "http://ec2-13-127-59-58.ap-south-1.compute.amazonaws.com/softcode/api/sign_up.php";
    private static final String KEY_PASS = "passs";
    EditText phone_number, password_login, phoneno;
    Button Login;
    SessionManager session;
    CallbackManager mCallbackManager;
    String User_number, User_passwords, Counter_code, Phonenumber = "", verificationCode, str, userId, pass1 = "";
    AlertDialog.Builder builder;
    Button login;
    LinearLayout cancel_layout, cancel_ok;
    ProgressBar progress;
    ImageView facebook_login, googleLogin, twitter_login;
    TextView forgetpassword;
    GoogleApiClient googleApiClient;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    private ProgressDialog pDialog;
    private int RC_SIGN_IN = 22;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mCallbackManager = CallbackManager.Factory.create();
        // FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());
                                        Log.d("LoginJSONActivity", object.toString());
                                        try {
                                            JSONObject json = response.getJSONObject();
                                            final String name = json.getString("name");
                                            final String email = json.getString("email");
                                            final String id = json.getString("id");
                                            Log.d("FACEBOOKNAME", name + " Email > " + email);
                                            Log.d("FACEBOOKNAME", name + " Email > " + id);
                                            //   String profilePicUrl = json.getJSONObject("picture").getJSONObject("data").getString("url");
                                            String image = "https://graph.facebook.com/" + id + "/picture?type=large";


                                            socialLogin(email, id);


                                            //new FacebookloginAsyncTask().execute(email);
                                        } catch (Exception e) {

                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large)");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(Signin.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(Signin.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        edit = pref.edit();

        builder = new AlertDialog.Builder(Signin.this);
        phone_number = findViewById(R.id.phone_number);
        password_login = findViewById(R.id.password_login);
        forgetpassword = findViewById(R.id.forget_password);
        login = (Button) findViewById(R.id.login);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        session = new SessionManager(getApplicationContext());
        SharedPreferences settings = getSharedPreferences("mypref", MODE_PRIVATE);
        userId = settings.getString("userid", "");
        pass1 = settings.getString("password", "");


        try {
            mAuth = FirebaseAuth.getInstance();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Inbt = new Intent(Signin.this, ForgotPasswordActivity.class);
                startActivity(Inbt);

            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);



        googleLogin = findViewById(R.id.ggleLogin);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        facebook_login = (ImageView) findViewById(R.id.facebook_login);
        facebook_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Signin.this, Arrays.asList("public_profile", "email", "user_birthday"));
            }
        });

        twitter_login = (ImageView) findViewById(R.id.twitter);
        twitter_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });


        /*mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    Log.d("Firebase", "Signed In - " + user.getEmail());

                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    if (user.getDisplayName() != null) {


                        String id = user.getUid();
                        String email = user.getEmail();

                        socialLogin(email, id);

                        //nameTextView.setText("HI " + user.getDisplayName().toString());
                        //emailTextView.setText(user.getEmail().toString());

                    } else {
                        // User is signed out
                        //Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                    // ...
                }
            }
        };*/



    }

    public void showCustomDialog(View view) {
        forgotdialog();
    }

    private void forgotdialog() {
        final Dialog dialog = new Dialog(Signin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forgote_customedialog);
        cancel_layout = dialog.findViewById(R.id.cancel_layout);
        phoneno = (EditText) dialog.findViewById(R.id.txtphoneno);
        cancel_ok = dialog.findViewById(R.id.txtok);
        cancel_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        cancel_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneno.getText().toString().equals("")) {
                    Toast.makeText(Signin.this, "enter phone number", Toast.LENGTH_SHORT).show();
                }
                forgotPassword();
            }
        });
    }


    public void login() {

        final String phone = phone_number.getText().toString();
        final String password = password_login.getText().toString();

        if (phone.length() > 0) {

            if (password.length() > 0) {


                progress.setVisibility(View.VISIBLE);

                final bean b = (bean) getApplicationContext();


                SharedPreferences fcmPref = getSharedPreferences("fcm", Context.MODE_PRIVATE);

                String keey = fcmPref.getString("token", "");


                Call<login2Bean> call = b.getRetrofit().signIn(phone, password, keey);


                call.enqueue(new Callback<login2Bean>() {
                    @Override
                    public void onResponse(Call<login2Bean> call, retrofit2.Response<login2Bean> response) {


                        if (Objects.equals(response.body().getStatus(), "1")) {

                            if (response.body().getData().getUserName().length() > 0) {
                                Toast.makeText(Signin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                SharePreferenceUtils.getInstance().putString("userId", response.body().getData().getUserId());
                                SharePreferenceUtils.getInstance().putString("userName", response.body().getData().getUserName());


                                try {
                                    SharePreferenceUtils.getInstance().putString("userImage", response.body().getData().getUserImage());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                SharePreferenceUtils.getInstance().putString("type", "phone");
                                SharePreferenceUtils.getInstance().putString("user", phone);
                                SharePreferenceUtils.getInstance().putString("pass", password);
                                SharePreferenceUtils.getInstance().putString("userType", response.body().getData().getType());
                                SharePreferenceUtils.getInstance().putString("yid", response.body().getData().getYouthLiveId());


                                Intent Inbt = new Intent(Signin.this, HomeActivity.class);
                                Inbt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(Inbt);

                            } else {
                                SharePreferenceUtils.getInstance().putString("userId", response.body().getData().getUserId());
                                SharePreferenceUtils.getInstance().putString("userName", response.body().getData().getUserName());


                                try {
                                    SharePreferenceUtils.getInstance().putString("userImage", response.body().getData().getUserImage());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                SharePreferenceUtils.getInstance().putString("type", "phone");
                                SharePreferenceUtils.getInstance().putString("user", phone);
                                SharePreferenceUtils.getInstance().putString("pass", password);

                                Toast.makeText(Signin.this, "Please update your info", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signin.this, UserInformation.class);
                                intent.putExtra("userId", response.body().getData().getUserId());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);


                            }


                        } else {
                            Toast.makeText(Signin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<login2Bean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

            } else {
                password_login.setError("Invalid Password");
            }

        } else {
            phone_number.setError("Invalid Phone");
        }
    }


    private void forgotPassword() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://ec2-13-58-47-70.us-east-2.compute.amazonaws.com/softcode/api/sign_up.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Log.d(TAG, response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    String status = jObj.getString("status");
                    if (!status.equals("0")) {
                        JSONObject user = jObj.getJSONObject("data");
                        Counter_code = user.getString("countryCode");
                        Phonenumber = user.getString("phone");
                        verificationCode = user.getString("verificationCode");
                        str = jObj.getString("message");
                        userId = user.getString("userId");
                        SharedPreferences settings = getSharedPreferences("mypref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("userid", userId);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), OTP.class);
                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(Phonenumber, null, verificationCode, pi, null);
                    } else {
                        str = jObj.getString("message");
                        Toast.makeText(Signin.this, str, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("countryCode", Counter_code);
                params.put("phone", Phonenumber);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void OTP() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASEOTP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d(TAG, response.toString());
                Intent intent = new Intent(Signin.this, Signin.class);
                intent.putExtra("userId", userId);
                intent.putExtra("phone", Phonenumber);
                intent.putExtra("countryCode", Counter_code);
                startActivity(intent);
                StringRequest sr = new StringRequest(Request.Method.POST, "http://ec2-13-58-47-70.us-east-2.compute.amazonaws.com/softcode/api/sign_up.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("countryCode", Counter_code);
                        params.put("phone", Phonenumber);
                        return params;
                    }
                };
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signin.this, error.toString(), Toast.LENGTH_SHORT).show();
                //hidepDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", Phonenumber);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("asdasdasd", "Google sign in failed", e);
                // ...
            }
        }
    }


    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d("Firebase", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        socialLogin(acct.getEmail(), acct.getId());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(Signin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void socialLogin(final String email, final String pid) {
        progress.setVisibility(View.VISIBLE);

        final bean b = (bean) getApplicationContext();


        SharedPreferences fcmPref = getSharedPreferences("fcm", Context.MODE_PRIVATE);

        String keey = fcmPref.getString("token", "");


        Call<socialBean> call = b.getRetrofit().socialSignIn(pid, email, keey);

        call.enqueue(new Callback<socialBean>() {
            @Override
            public void onResponse(Call<socialBean> call, retrofit2.Response<socialBean> response) {

                if (response.body().getData().getUserName().length() > 0) {

                    SharePreferenceUtils.getInstance().putString("userId", response.body().getData().getUserId());
                    SharePreferenceUtils.getInstance().putString("userName", response.body().getData().getUserName());


                    try {
                        SharePreferenceUtils.getInstance().putString("userImage", response.body().getData().getUserImage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    SharePreferenceUtils.getInstance().putString("type", "social");
                    SharePreferenceUtils.getInstance().putString("user", email);
                    SharePreferenceUtils.getInstance().putString("pass", pid);
                    if (response.body() != null) {
                        SharePreferenceUtils.getInstance().putString("userType", response.body().getData().getType());
                    }
                    if (response.body() != null) {
                        SharePreferenceUtils.getInstance().putString("yid", response.body().getData().getYouthLiveId());
                    }


                    //    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signin.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {

                    SharePreferenceUtils.getInstance().putString("userId", response.body().getData().getUserId());
                    SharePreferenceUtils.getInstance().putString("userName", response.body().getData().getUserName());


                    try {
                        SharePreferenceUtils.getInstance().putString("userImage", response.body().getData().getUserImage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    SharePreferenceUtils.getInstance().putString("type", "social");
                    SharePreferenceUtils.getInstance().putString("user", email);
                    SharePreferenceUtils.getInstance().putString("pass", pid);

                    Toast.makeText(Signin.this, "Please update your info", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signin.this, UserInformation.class);
                    intent.putExtra("userId", response.body().getData().getUserId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                }


                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<socialBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

}
