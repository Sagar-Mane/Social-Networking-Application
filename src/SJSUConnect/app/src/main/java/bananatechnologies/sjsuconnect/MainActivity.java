package bananatechnologies.sjsuconnect;

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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //declaring variables to get references to UI componenets
    private EditText email;
    private EditText password;
    private Button login;
    private static final String TAG = "logs";
    private Button register_new_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                UserIdSingleton.getInstance().setUserId(email.getText().toString());
                //Start main screen after receiving response from login API
                //startMainScreen();

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
     * Method to start main screen using intent.
     */
    public void startMainScreen(){
        Intent main=new Intent(this,MainScreen.class);
        startActivity(main);
        /**
         * Toast to welcome back user.
         */
        Context context = getApplicationContext();
        CharSequence text = "Welcome back to SJSU Connect!";
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
