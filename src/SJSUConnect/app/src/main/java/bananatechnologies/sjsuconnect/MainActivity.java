package bananatechnologies.sjsuconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private static final String TAG = "logs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        Log.i(TAG,"Reporting from onCreate view");
        login=(Button) findViewById(R.id.log_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from login button pressed");
                Log.i(TAG,"Email="+email.getText()+"Password="+password.getText());

                //startMainScreen();
                login();
                /*if(email.getText().equals("admin")&&password.getText().equals("admin")){
                    Log.i(TAG,"Successful login");
                }
                else{
                    Log.i(TAG,"Login failed");
                }*/
            }
        });
    }

    public void startMainScreen(){
        Intent main=new Intent(this,MainScreen.class);
        startActivity(main);
    }
    public void registerUser(){

        // Get a RequestQueue
        RequestQueue queue = bananatechnologies.sjsuconnect.RequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();

        // Instantiate the RequestQueue.

        String url ="http://192.168.99.1:3000/ping";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Response received Success 200
                        Log.i(TAG,"Response Received Successfully");
                        Log.i(TAG,"Response="+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error Occured StatusCode 401
                Log.i(TAG,"Error occured in the request");
                Log.i(TAG,"Error=   "+error);
            }
        });
        // Add the request to the RequestQueue.
        bananatechnologies.sjsuconnect.RequestQueue.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void login(){
        Log.i(TAG,"Reporting from login function");
        final String username=email.getText().toString();
        final String pass=password.getText().toString();
        String url ="http://192.168.99.1:3000/login";
        StringRequest login=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Successful reponse returned");
                Log.i(TAG,"Reponse=  "+response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error   -"+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password", pass);

                return params;
            }
        };
        bananatechnologies.sjsuconnect.RequestQueue.getInstance(this).addToRequestQueue(login);

    }


}
