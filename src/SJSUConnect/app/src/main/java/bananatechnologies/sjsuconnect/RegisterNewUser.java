package bananatechnologies.sjsuconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterNewUser extends AppCompatActivity {

    private TextView sign_up_with_email;
    private TextView sign_up_with_phone;
    private static final String TAG = "logs_register_activity";
    private EditText email_address_register;
    private EditText phone_number_register;
    private Button next_button_register;

    private EditText first_name;
    private EditText last_name;

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

                //After clicking next button start the verification screen activity

                startVerificationActivity();
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

                //After clicking next button start the verification screen activity

                startVerificationActivity();

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
