package com.filenber.pics_bag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Auth  extends AppCompatActivity {
  TextInputEditText phonenumber;
  //CountryCodePicker codePicker;
  Button login_btn;
  ProgressDialog progressDialog;
  FirebaseAuth firebaseAuth;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";
  public static String URL_PRODUCTS ="http://localhost/ACA/api/api.php?op=register_user";

  private static final String TAG = "PhoneAuthActivity";
  // [START declare_auth]
  private FirebaseAuth mAuth;
  FirebaseAnalytics mFirebaseAnalytics;
  CountryCodePicker codePicker;
  // [END declare_auth]
  private boolean mVerificationInProgress = false;
  private String mVerificationId;
  private PhoneAuthProvider.ForceResendingToken mResendToken;
  private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Obtain the FirebaseAnalytics instance.
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    setContentView(R.layout.activity_auth);
    phonenumber= (TextInputEditText)findViewById(R.id.phone_number);
    codePicker = (CountryCodePicker) findViewById(R.id.ccp);

    progressDialog  =new ProgressDialog(Auth.this);
    login_btn = (Button) findViewById(R.id.login_btn);

    mAuth = FirebaseAuth.getInstance();
    if (FirebaseAuth.getInstance().getCurrentUser()!= null) {
      // User is signed in

      //setLanguge(SharedPrefManager.getInstance(Login_Page.this).lan());
      startActivity(new Intent(Auth.this,MainActivity.class));
      finish();
    }



    //SharedPrefManager.getInstance(this).lan();

    login_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        attempt_login();
      }
    });

    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

      @Override
      public void onVerificationCompleted(PhoneAuthCredential credential) {
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.



        Log.d(TAG, "onVerificationCompleted:" + credential);

        signInWithPhoneAuthCredential(credential);
      }

      @Override
      public void onVerificationFailed(FirebaseException e) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w(TAG, "onVerificationFailed", e);

        if (e instanceof FirebaseAuthInvalidCredentialsException) {
          // Invalid request
          // ...
          Log.d("Check" ,e.toString());
          Toast.makeText(Auth.this, e.toString(), Toast.LENGTH_SHORT).show();
        } else if (e instanceof FirebaseTooManyRequestsException) {
          // The SMS quota for the project has been exceeded
          // ...
          Log.d("Check" ,e.toString());
          Toast.makeText(Auth.this, e.toString(), Toast.LENGTH_SHORT).show();

        }

        // Show a message and update the UI
        // ...
      }

      @Override
      public void onCodeSent(String verificationId,
                             PhoneAuthProvider.ForceResendingToken token) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        Log.d(TAG, "onCodeSent:" + verificationId);

        // Save verification ID and resending token so we can use them later
        mVerificationId = verificationId;
        mResendToken = token;


        // ...
      }
    };
  }
  private void verifyVerificationCode(String otp) {
    //creating the credential
    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);

    //signing the user
    signInWithPhoneAuthCredential(credential);
  }
  private void attempt_login() {
    progressDialog.setTitle("Please Wait");
    progressDialog.setMessage("Dear Users Please Wait");
    progressDialog.show();
    codePicker.registerPhoneNumberTextView(phonenumber);

    String phone = codePicker.getFullNumberWithPlus();
    try {
      startPhoneNumberVerification(phone);
      //   Toast.makeText(this, "WE Did It", Toast.LENGTH_SHORT).show();
      Toast.makeText(this, phone.toString(), Toast.LENGTH_SHORT).show();
    }
    catch (Exception e)
    {
      Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }


  }


  private void startPhoneNumberVerification(String phoneNumber) {
    // [START start_phone_auth]
    PhoneAuthProvider.getInstance().verifyPhoneNumber(
      phoneNumber,        // Phone number to verify
      60,                 // Timeout duration
      TimeUnit.SECONDS,   // Unit of timeout
      this,               // Activity (for callback binding)
      mCallbacks);        // OnVerificationStateChangedCallbacks
    // [END start_phone_auth]

    mVerificationInProgress = true;
  }
  private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
    mAuth.signInWithCredential(credential)
      .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information

            FirebaseUser user = task.getResult().getUser();
            //upload_profil_data();

            register_user_on_local_server(task.getResult().getUser().getPhoneNumber());

            // ...
          } else {
            // Sign in failed, display a message and update the UI
            Toast.makeText(Auth.this, "Signin Vertification Failed", Toast.LENGTH_SHORT).show();
            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
              // The verification code entered was invalid
              Toast.makeText(Auth.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
            }
          }
        }
      });
  }
  public  void register_user_on_local_server(final String user_phone)
  {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
      new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          //pDialog.dismiss();

          try {
            JSONObject object     = new JSONObject(response);
            JSONArray jsonArray   = object.getJSONArray("register_data");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

// fetch password from JSON
            //String response_server        = jsonObject.getString("response");
// use password in textviewfemail.setText(password, TextView.BufferType.EDITABLE);
            //  pas.setText(password, TextView.BufferType.EDITABLE);



          }
          catch (JSONException e) {
            //Toast.makeText(ForgotPasswordActivity.this, e.toString(), Toast.LENGTH_LONG).show();
          }


        }

      },

      new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(Auth.this, error.toString(), Toast.LENGTH_LONG).show();
        }
      }) {
      @Override
      protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",user_phone);

        //params.put("user", SharedPrefManager.getInstance(getContext()).getUsername());;

        return params;
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(Auth.this);
    requestQueue.add(stringRequest);

  }
}

