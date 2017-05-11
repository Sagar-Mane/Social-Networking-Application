package bananatechnologies.sjsuconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                if(email.getText().equals("admin")&&password.getText().equals("admin")){
                    Log.i(TAG,"Successful login");
                }
                else{
                    Log.i(TAG,"Login failed");
                }
            }
        });
    }

}
