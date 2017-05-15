package bananatechnologies.sjsuconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class verifyUser extends AppCompatActivity {

    
    private static final String TAG = "verifyUser_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);
        Log.i(TAG,"Reporting from verify user activity on create");

    }
}
