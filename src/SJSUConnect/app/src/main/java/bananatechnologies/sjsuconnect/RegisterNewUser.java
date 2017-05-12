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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        sign_up_with_email=(TextView) findViewById(R.id.email_signup);
        sign_up_with_phone=(TextView) findViewById(R.id.phone_signup);
        email_address_register=(EditText)findViewById(R.id.email_id_register);
        phone_number_register=(EditText) findViewById(R.id.phone_number_register);
        next_button_register=(Button) findViewById(R.id.register);

        //Initially hiding unnessary things
        next_button_register.setVisibility(View.INVISIBLE);
        email_address_register.setVisibility(View.INVISIBLE);
        phone_number_register.setVisibility(View.INVISIBLE);

        sign_up_with_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from sign up with email");
                sign_up_with_email.setVisibility(View.GONE);
                sign_up_with_phone.setVisibility(View.GONE);
                phone_number_register.setVisibility(View.GONE);

                email_address_register.setVisibility(View.VISIBLE);
                next_button_register.setVisibility(View.VISIBLE);

                //Next step after pressing next button sign up with email
                next_button_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"Reporting from next button pressed");
                        startVerificationActivity();
                    }
                });

            }
        });

        sign_up_with_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Reporting from sign up with phone");
                sign_up_with_email.setVisibility(View.GONE);
                sign_up_with_phone.setVisibility(View.GONE);
                email_address_register.setVisibility(View.GONE);
                phone_number_register.setVisibility(View.VISIBLE);
                next_button_register.setVisibility(View.VISIBLE);

                //Next step after pressing next button sign up with phone
                next_button_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"Reporting from next button pressed");
                        startVerificationActivity();
                    }
                });
            }
        });
    }

    public void startVerificationActivity(){
        Intent verification_activity=new Intent(this,verifyUser.class);
        startActivity(verification_activity);
    }
}
