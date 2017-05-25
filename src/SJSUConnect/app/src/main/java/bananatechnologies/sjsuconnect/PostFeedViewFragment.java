package bananatechnologies.sjsuconnect;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sagar Mane on 11-05-2017.
 * Don't change or modify before asking it contains dependencies.
 */

public class PostFeedViewFragment extends Fragment{

    private static final String TAG = "PostFeedViewFragment";
    private String title;
    private int page;
    private List<Posts> postsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter pAdapter;
    private Button newPost;

    public static PostFeedViewFragment newInstance(int page, String title) {

        PostFeedViewFragment fragmentFirst = new PostFeedViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        //fragmentFirst.setArguments(args);

        return fragmentFirst;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View Root = inflater.inflate(R.layout.postfeedviewfragment,container,false);

        recyclerView = (RecyclerView) Root.findViewById(R.id.recycler_view);

        prepareMovieData();
        pAdapter = new PostAdapter(postsList);

        Log.i("PostFeed", String.valueOf(postsList.size()));
        pAdapter.notifyDataSetChanged();


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Root.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView.setAdapter(pAdapter);



        return Root;

    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i(TAG, "OnAttach");

        Activity activity = (Activity) context;
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);

        pAdapter = new PostAdapter(postsList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        prepareMovieData();

    }
*/


    private void prepareMovieData() {

        JSONObject posts_response_body = new JSONObject();
        Log.i("user id", String.valueOf(UserIdSingleton.getInstance().getUserId()));
        String url ="http://10.0.0.89:3000/getTimeline?email="+UserIdSingleton.getInstance().getUserId();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Posts Feeds", String.valueOf(response));
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for(int i=0;i<data.length(); i++){
                                JSONObject jsonas = data.getJSONObject(i);
                                Posts posts = new Posts(jsonas.getString("status"), "Action & Adventure", "05/25/2017");
                                postsList.add(posts);
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

        bananatechnologies.sjsuconnect.RequestQueue.getInstance(getContext()).addToRequestQueue(jsObjRequest);


        /*Posts posts = new Posts("Mad Max: Fury Road", "Action & Adventure", "2015");


        Posts posts = new Posts("Mad Max: Fury Road", "Action & Adventure", "2015");
        postsList.add(posts);

        posts = new Posts("Inside Out", "Animation, Kids & Family", "2015");
        postsList.add(posts);

        posts = new Posts("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        postsList.add(posts);

        posts = new Posts("Shaun the Sheep", "Animation", "2015");
        postsList.add(posts);

        posts = new Posts("The Martian", "Science Fiction & Fantasy", "2015");
        postsList.add(posts);

        posts = new Posts("Mission: Impossible Rogue Nation", "Action", "2015");
        postsList.add(posts);

        posts = new Posts("Up", "Animation", "2009");
        postsList.add(posts);

        posts = new Posts("Star Trek", "Science Fiction", "2009");
        postsList.add(posts);

        posts = new Posts("The LEGO Movie", "Animation", "2014");
        postsList.add(posts);

        posts = new Posts("Iron Man", "Action & Adventure", "2008");
        postsList.add(posts);

        posts = new Posts("Aliens", "Science Fiction", "1986");
        postsList.add(posts);

        posts = new Posts("Chicken Run", "Animation", "2000");
        postsList.add(posts);

        posts = new Posts("Back to the Future", "Science Fiction", "1985");
        postsList.add(posts);

        posts = new Posts("Raiders of the Lost Ark", "Action & Adventure", "1981");
        postsList.add(posts);

        posts = new Posts("Goldfinger", "Action & Adventure", "1965");
        postsList.add(posts);

        posts = new Posts("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        postsList.add(posts);*/

        //pAdapter.notifyDataSetChanged();
    }

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return rootview;
    }*/
}
