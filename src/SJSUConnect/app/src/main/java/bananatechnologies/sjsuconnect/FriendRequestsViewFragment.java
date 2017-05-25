package bananatechnologies.sjsuconnect;


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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sagar Mane on 11-05-2017.
 * Don't change or modify before asking it contains dependencies.
 */

public class FriendRequestsViewFragment extends Fragment{

    private static final String TAG = "PostFeedViewFragment";
    private String title;
    private int page;
    private List<Friends> friendsRequestList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendRequestAdapter fAdapter;
    private Button newPost;

    public static FriendRequestsViewFragment newInstance(int page, String title) {

        FriendRequestsViewFragment fragmentFirst = new FriendRequestsViewFragment();
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
        View rootview=inflater.inflate(R.layout.friend_requests_view_fragment,container,false);

        View Root = inflater.inflate(R.layout.postfeedviewfragment,container,false);

        recyclerView = (RecyclerView) Root.findViewById(R.id.recycler_view);

        prepareFriendRequestData();
        fAdapter = new FriendRequestAdapter(friendsRequestList);

        Log.i("PostFeed", String.valueOf(friendsRequestList.size()));
        fAdapter.notifyDataSetChanged();


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(Root.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView.setAdapter(fAdapter);



        return Root;
    }

    private void prepareFriendRequestData() {

        Friends friends = new Friends("Mad Max: Fury Road", "Action & Adventure", "2015");
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
        friendsRequestList.add(friends);

        //fAdapter.notifyDataSetChanged();
    }
}
