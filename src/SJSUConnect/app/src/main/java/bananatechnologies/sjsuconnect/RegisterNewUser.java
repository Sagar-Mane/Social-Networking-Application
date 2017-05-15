package bananatechnologies.sjsuconnect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterNewUser extends AppCompatActivity {

    private TextView sign_up_with_email;
    private TextView sign_up_with_phone;
    private static final String TAG = "logs_register_activity";
    private EditText email_address_register;
    private EditText phone_number_register;
    private Button next_button_register;

    private EditText first_name;
    private EditText last_name;

    private Activity ref_this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        //getting references to UI elements
        sign_up_with_email=(TextView) findViewById(R.id.email_signup);
        sign_up_with_phone=(TextView) findViewById(R.id.phone_signup);
        email_address_register=(EditText)findViewById(R.id.email_id_register);
        phone_number_register=(EditText) findViewById(R.id.phone_number_register);
        next_button_register=(Button) findViewById(R.id.register);

        //Initially hiding unnessary things
        next_button_register.setVisibility(View.INVISIBLE);
        email_address_register.setVisibility(View.INVISIBLE);
        phone_number_register.setVisibility(View.INVISIBLE);

        //getting activity instance
        ref_this=this;

        //sign up with email button click listener
        sign_up_with_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up_with_email();
            }
        });

        //sign up with phone button click listener
        sign_up_with_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up_with_phone();
            }
        });
    }

    /**
     * sign up with email address
     */
    public void sign_up_with_email() {
        Log.i(TAG, "Reporting from signup with email");
        //Handling UI visibility
        sign_up_with_email.setVisibility(View.GONE);
        sign_up_with_phone.setVisibility(View.GONE);
        phone_number_register.setVisibility(View.GONE);
        email_address_register.setVisibility(View.VISIBLE);
        next_button_register.setVisibility(View.VISIBLE);
        //end handling UI visibility

        //Next step after pressing next button sign up with email
        next_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from next button pressed");

                // getting references to Input elements
                first_name=(EditText) findViewById(R.id.first_name);
                last_name=(EditText) findViewById(R.id.last_name);
                email_address_register=(EditText) findViewById(R.id.email_id_register);
                //Logs to check inputs from register screen

                /*Log.i(TAG,"@@@@@@@@@@@@@@##########$$$$$$$$$$$$$$$$Checking inputs when u click sign up with email"+first_name.getText().toString());
                Log.i(TAG,"Checking inputs when u click sign up with email"+last_name.getText().toString());
                Log.i(TAG,"Checking inputs when u click sign up with email"+email_address_register.getText().toString());
                */



                //First call register user API from here...so that email will be sent to user's id
                //and then start verification activity

                // Get a RequestQueue
                com.android.volley.RequestQueue queue = bananatechnologies.sjsuconnect.RequestQueue.getInstance(ref_this.getApplicationContext()).
                        getRequestQueue();

                // Instantiate the RequestQueue.

                String url ="http://192.168.99.1:3000/register";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Response received Success 200
                                Log.i(TAG,"Response Received Successfully");
                                Log.i(TAG,"Response="+response);
                                //After clicking next button start the verification screen activity

                                startVerificationActivity();
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

                        params.put("first_name",first_name.getText().toString());
                        params.put("last_name",last_name.getText().toString());
                        params.put("country_code","1");
                        params.put("phone_number",phone_number_register.getText().toString());
                        params.put("email",email_address_register.getText().toString());
                        params.put("password", "555");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                bananatechnologies.sjsuconnect.RequestQueue.getInstance(ref_this).addToRequestQueue(stringRequest);

                //After clicking next button start the verification screen activity


            }
        });
    }

    /**
     * sign up with phone
     */
    public void sign_up_with_phone(){
        Log.i(TAG,"Reporting from sign up with phone");

        //Handling UI visibility
        sign_up_with_email.setVisibility(View.GONE);
        sign_up_with_phone.setVisibility(View.GONE);
        email_address_register.setVisibility(View.GONE);
        phone_number_register.setVisibility(View.VISIBLE);
        next_button_register.setVisibility(View.VISIBLE);
        //end handling UI visibility

        //Next step after pressing next button in signup with phone
        next_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name=(EditText) findViewById(R.id.first_name);
                last_name=(EditText) findViewById(R.id.last_name);
                phone_number_register=(EditText) findViewById(R.id.phone_number_register);

                //Logs to check inputs from register screen

                /*Log.i(TAG,"@@@@@@@@@@@@@@##########$$$$$$$$$$$$$$$$Checking inputs when u click sign up with email"+first_name.getText().toString());
                Log.i(TAG,"Checking inputs when u click sign up with email"+last_name.getText().toString());
                Log.i(TAG,"Checking inputs when u click sign up with email"+phone_number_register.getText().toString());*/

                //First call register user API from here...so that email will be sent to user's id
                //and then start verification activity


                // Get a RequestQueue
                com.android.volley.RequestQueue queue = bananatechnologies.sjsuconnect.RequestQueue.getInstance(ref_this.getApplicationContext()).
                        getRequestQueue();

                // Instantiate the RequestQueue.

                String url ="http://192.168.99.1:3000/register";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Response received Success 200
                                Log.i(TAG,"Response Received Successfully");
                                Log.i(TAG,"Response="+response);
                                //After clicking next button start the verification screen activity

                                startVerificationActivity();
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

                        params.put("first_name",first_name.getText().toString());
                        params.put("last_name",last_name.getText().toString());
                        params.put("country_code","1");
                        params.put("phone_number",phone_number_register.getText().toString());
                        params.put("email",email_address_register.getText().toString());
                        params.put("password", "555");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                bananatechnologies.sjsuconnect.RequestQueue.getInstance(ref_this).addToRequestQueue(stringRequest);



            }
        });
    }


    /**
     * start verification activity using intent
     */
    public void startVerificationActivity(){
        Intent verification_activity=new Intent(this,verifyUser.class);
        startActivity(verification_activity);
    }
}
