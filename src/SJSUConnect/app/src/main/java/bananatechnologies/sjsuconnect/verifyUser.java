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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
        //final String email=UserIdSingleton.getInstance().getUserId();
        //Log.i(TAG,"User email test="+email);
        // Get a RequestQueue
        com.android.volley.RequestQueue queue = bananatechnologies.sjsuconnect.RequestQueue.getInstance(verify_user_activity.getApplicationContext()).
                getRequestQueue();
        // Instantiate the RequestQueue.
        final String email=UserIdSingleton.getInstance().getUserId();
        String url ="http://192.168.99.1:3000/validate";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response received Success 200
                        Log.i(TAG,"Response Received Successfully");
                        Log.i(TAG,"Response="+response);


                        Context context = getApplicationContext();
                        CharSequence text = "Registration Succesful Login to Continue !";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        //After receiving reponse from Validate API start login activity i.e. main activity
                        startLoginScreen();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error Occured StatusCode 401
                Log.i(TAG,"Error occured in the request");
                Log.i(TAG,"Error=   "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();

                //POST Request parameters for validate API
                params.put("email",email);
                params.put("verification_id",verification_code.getText().toString());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        bananatechnologies.sjsuconnect.RequestQueue.getInstance(verify_user_activity).addToRequestQueue(stringRequest);
    }

    /**
     * Method to start login screen after verification using intent
     */
    public void startLoginScreen(){
        Intent login_activity=new Intent(this,MainActivity.class);
        startActivity(login_activity);

    }
}
