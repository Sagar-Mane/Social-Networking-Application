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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //declaring variables to get references to UI componenets
    private EditText email;
    private EditText password;
    private Button login;
    private static final String TAG = "logs";
    private Button register_new_user;

    //ref to login activity
    private Activity login_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_activity=this;

        //getting references to UI elements
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        register_new_user=(Button) findViewById(R.id.create_account);

        Log.i(TAG,"Reporting from onCreate view");
        login=(Button) findViewById(R.id.log_in);
        //Login button listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from login button pressed");
                Log.i(TAG,"Email="+email.getText()+"Password="+password.getText());
                //get username and password
                email=(EditText) findViewById(R.id.email);
                password=(EditText) findViewById(R.id.password);

                login();

            }
        });

        //register new user button listener
        register_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from create account");
                startRegisterActivity();
            }
        });
    }

    /**
     * Method to call Login API
     * Upon response 200 call startMainScreen Activity
     */
    public void login(){
        //start user session
        UserIdSingleton.getInstance().setUserId(email.getText().toString());
        // Get a RequestQueue
        com.android.volley.RequestQueue queue = bananatechnologies.sjsuconnect.RequestQueue.getInstance(login_activity.getApplicationContext()).
                getRequestQueue();
        // Instantiate the RequestQueue.

        /*String url ="http://192.168.99.1:3000/login";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response received Success 200
                        Log.i(TAG,"Response Received Successfully");
                        Log.i(TAG,"Response="+response);

                        //After receiving reponse from Login API start main screen
                        startMainScreen();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error Occured StatusCode 401
                Log.i(TAG,"Error occured in the request");
                Log.i(TAG,"Error=   "+error);
                *//**
                 * Error toast.
                 *//*
                Context context = getApplicationContext();
                CharSequence text =  "Oops ! Something went wrong. Try Again";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        bananatechnologies.sjsuconnect.RequestQueue.getInstance(login_activity).addToRequestQueue(stringRequest);*/
        //testing json object request

        JSONObject register_request_body = new JSONObject();
        String url ="http://52.88.12.164:3000/login";
        try
        {
            register_request_body.put("email",email.getText().toString());
            register_request_body.put("password",password.getText().toString());
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
                        Log.i(TAG,"LogIn succesful");

                        UserIdSingleton.getInstance().setFirst_name(response.get("first_name").toString());
                        UserIdSingleton.getInstance().setLast_name(response.get("last_name").toString());
                        //After receiving reponse from Login API start main screen
                        startMainScreen();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    if(response.get("statusCode").toString().equals("401")){
                        Log.i(TAG,"Login Error Occurred");
                        // We may start login activity if this happens...
                        Context context = getApplicationContext();
                        CharSequence text = "Oops ! Something went wrong. Try Again";
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

        bananatechnologies.sjsuconnect.RequestQueue.getInstance(this).addToRequestQueue(jsonRequest);
    }

    /**
     * Method to start main screen using intent.
     */
    public void startMainScreen(){
        Intent main=new Intent(this,MainScreen.class);
        startActivity(main);
        /**
         * Toast to welcome back user.
         */
        Context context = getApplicationContext();
        CharSequence text = "Welcome to SJSU Connect!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    /**
     * Start register activity using intent.
     */
    public void startRegisterActivity(){
        Log.i(TAG,"Reporting from start register activity");
        Intent register_activity=new Intent(this,RegisterNewUser.class);
        startActivity(register_activity);
    }


}
