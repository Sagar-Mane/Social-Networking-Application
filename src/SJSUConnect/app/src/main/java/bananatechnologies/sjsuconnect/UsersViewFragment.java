package bananatechnologies.sjsuconnect;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar Mane on 11-05-2017.
 * Don't change or modify before asking it contains dependencies.
 */

public class UsersViewFragment extends Fragment{
    /*private String title;
    private int page;*/
    private static final String TAG = "PostFeedViewFragment";
    private String title;
    private int page;
    private List<Friends> friendsRequestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter uAdapter;
    public static Context c;
    public TextView editText;

    public static UsersViewFragment newInstance(int page, String title) {

        UsersViewFragment fragmentFirst = new UsersViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.usersviewfragment,container,false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_users);
        c = getContext();
        View temp = inflater.inflate(R.layout.users_list_row,container,false);

        prepareFriendRequestData();
        uAdapter = new UsersAdapter(friendsRequestList);

        Log.i("PostFeed", String.valueOf(friendsRequestList.size()));
        uAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);
        return root;
    }

    private void addFriend(JSONObject posts_response_body) {

        String url ="http://10.0.0.89:3000/addByEmail";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, posts_response_body, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Context context = getContext();
                        CharSequence text = "Success! Friend Request Sent";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        //mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        RequestQueue.getInstance(c).addToRequestQueue(jsObjRequest);
    }


    private void prepareFriendRequestData() {


        JSONObject posts_response_body = new JSONObject();
        Log.i("user id", String.valueOf(UserIdSingleton.getInstance().getUserId()));
        String url ="http://10.0.0.89:3000/getAllUsers?email="+UserIdSingleton.getInstance().getUserId();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        friendsRequestList = new ArrayList<>();
                        Log.i("Posts Feeds", String.valueOf(response));
                        try {
                            JSONArray data = response.getJSONArray("data");

                            for(int i=0;i<data.length(); i++){
                                JSONObject jsonas = data.getJSONObject(i);
                                Friends posts = new Friends(jsonas.getString("first_name") + " " + jsonas.getString("last_name"), "","",jsonas.getString("email"));
                                friendsRequestList.add(posts);
                            }
                        }
                        catch (Exception e){

                        }

                        //mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        RequestQueue.getInstance(getContext()).addToRequestQueue(jsObjRequest);
        /*fAdapter.notifyDataSetChanged();*/

        /*Friends friends = new Friends("Mad Max: Fury Road", "Action & Adventure", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("Inside Out", "Animation, Kids & Family", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("Shaun the Sheep", "Animation", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("The Martian", "Science Fiction & Fantasy", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("Mission: Impossible Rogue Nation", "Action", "2015");
        friendsRequestList.add(friends);

        friends = new Friends("Up", "Animation", "2009");
        friendsRequestList.add(friends);

        friends = new Friends("Star Trek", "Science Fiction", "2009");
        friendsRequestList.add(friends);

        friends = new Friends("The LEGO Movie", "Animation", "2014");
        friendsRequestList.add(friends);

        friends = new Friends("Iron Man", "Action & Adventure", "2008");
        friendsRequestList.add(friends);

        friends = new Friends("Aliens", "Science Fiction", "1986");
        friendsRequestList.add(friends);

        friends = new Friends("Chicken Run", "Animation", "2000");
        friendsRequestList.add(friends);

        friends = new Friends("Back to the Future", "Science Fiction", "1985");
        friendsRequestList.add(friends);

        friends = new Friends("Raiders of the Lost Ark", "Action & Adventure", "1981");
        friendsRequestList.add(friends);

        friends = new Friends("Goldfinger", "Action & Adventure", "1965");
        friendsRequestList.add(friends);

        friends = new Friends("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        friendsRequestList.add(friends);*/

        //fAdapter.notifyDataSetChanged();
    }


    public static void addFriends(JSONObject temp)
    {
        JSONObject posts_response_body = new JSONObject();
        Log.i("user id", String.valueOf(UserIdSingleton.getInstance().getUserId()));
        String url ="http://10.0.0.89:3000/approveFriendRequests";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Posts Feeds", String.valueOf(response));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        RequestQueue.getInstance(c).addToRequestQueue(jsObjRequest);
    }

    public static void follow(JSONObject temp)
    {
        JSONObject posts_response_body = new JSONObject();
        Log.i("user id", String.valueOf(UserIdSingleton.getInstance().getUserId()));
        String url ="http://10.0.0.89:3000/follow";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, temp, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        //mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        RequestQueue.getInstance(c).addToRequestQueue(jsObjRequest);
    }


}
