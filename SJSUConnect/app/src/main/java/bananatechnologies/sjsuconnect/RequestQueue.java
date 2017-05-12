package bananatechnologies.sjsuconnect;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by Sagar Mane. on 11-05-2017.
 * Singleton for getting request instance.
 */

public class RequestQueue {
    private static RequestQueue mInstance;
    private com.android.volley.RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private RequestQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueue(context);
        }
        return mInstance;
    }

    public com.android.volley.RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }



}
