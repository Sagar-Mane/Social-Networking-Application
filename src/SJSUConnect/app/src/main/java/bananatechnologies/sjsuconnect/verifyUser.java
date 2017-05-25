package bananatechnologies.sjsuconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class verifyUser extends AppCompatActivity {

    private Activity verify_user_activity;  //For getting reference to this activity outside scope

    private EditText verification_code;
    private Button verify;
    private static final String TAG = "verifyUser_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);
        Log.i(TAG,"Reporting from verify user activity on create");



        //getting activity reference.
        verify_user_activity=this;

        //getting references to UI elements

        verification_code=(EditText) findViewById(R.id.verification_code);
        verify=(Button) findViewById(R.id.verify_button);

        //verify button on click listener

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.i(TAG,"Reporting from verify button click listener");
                    verify();
            }
        });
    }

    public void verify(){
        Log.i(TAG,"Reporting from verify user method");
        Log.i(TAG,"Verfication code="+verification_code.getText().toString());

        //Call verify user API
        final String email=UserIdSingleton.getInstance().getUserId();
        Log.i(TAG,"Email============="+email);
        //Log.i(TAG,"User email test="+email);
        // Get a RequestQueue
        //testing json object request

        JSONObject register_request_body = new JSONObject();
        String url ="http://192.168.99.1:3000/validate";
        try
        {
            //POST Request parameters for validate API
            register_request_body.put("email",email);
            Log.i(TAG,"Email============="+email);
            register_request_body.put("verification_id",verification_code.getText().toString());
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, register_request_body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.get("statusCode").toString().equals("200")){
                        Log.i(TAG,"Verification succesful");

                        //After receiving reponse from Validate API start login activity i.e. main activity
                        Context context = getApplicationContext();
                        CharSequence text = "Verification Successful!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        startLoginScreen();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if(response.get("statusCode").toString().equals("401")){
                        Log.i(TAG,"Wrong verfication id");
                        // We may start login activity if this happens...
                        Context context = getApplicationContext();
                        CharSequence text = "Error ! Wrong Verification ID";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TODO: handle success
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //TODO: handle failure
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        bananatechnologies.sjsuconnect.RequestQueue.getInstance(verify_user_activity).addToRequestQueue(jsonRequest);
    }

    /**
     * Method to start login screen after verification using intent
     */
    public void startLoginScreen(){
        Intent login_activity=new Intent(this,MainActivity.class);
        startActivity(login_activity);

    }
}
