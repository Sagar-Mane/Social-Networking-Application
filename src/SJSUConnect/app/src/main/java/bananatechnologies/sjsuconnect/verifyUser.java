package bananatechnologies.sjsuconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class verifyUser extends AppCompatActivity {

    private EditText verification_code;
    private Button verify;
    private static final String TAG = "verifyUser_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);
        Log.i(TAG,"Reporting from verify user activity on create");

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
    }
}
