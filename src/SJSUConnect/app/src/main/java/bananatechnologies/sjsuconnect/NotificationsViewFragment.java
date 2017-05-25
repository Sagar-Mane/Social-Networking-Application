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
 */

public class NotificationsViewFragment extends Fragment {
    private static final String TAG = "NotificationsViewFragment";
    private String title;
    private int page;
    private List<Posts> notificationsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotificationAdapter nAdapter;
    private Button newPost;

    public static NotificationsViewFragment newInstance(int page, String title) {

        NotificationsViewFragment fragmentFirst = new NotificationsViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.notifications_view_fragment,container,false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_notifications);

        prepareMovieData();
        nAdapter = new NotificationAdapter(notificationsList);

        Log.i("PostFeed", String.valueOf(notificationsList.size()));
        nAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nAdapter);
        return root;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    private void prepareMovieData() {

        Posts posts = new Posts("Notification1", "Action & Adventure", "2015");
        notificationsList.add(posts);

        posts = new Posts("Inside Out", "Animation, Kids & Family", "2015");
        notificationsList.add(posts);

        posts = new Posts("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        notificationsList.add(posts);

        posts = new Posts("Shaun the Sheep", "Animation", "2015");
        notificationsList.add(posts);

        posts = new Posts("The Martian", "Science Fiction & Fantasy", "2015");
        notificationsList.add(posts);

        posts = new Posts("Mission: Impossible Rogue Nation", "Action", "2015");
        notificationsList.add(posts);

        posts = new Posts("Up", "Animation", "2009");
        notificationsList.add(posts);

        posts = new Posts("Star Trek", "Science Fiction", "2009");
        notificationsList.add(posts);

        posts = new Posts("The LEGO Movie", "Animation", "2014");
        notificationsList.add(posts);

        posts = new Posts("Iron Man", "Action & Adventure", "2008");
        notificationsList.add(posts);

        posts = new Posts("Aliens", "Science Fiction", "1986");
        notificationsList.add(posts);

        posts = new Posts("Chicken Run", "Animation", "2000");
        notificationsList.add(posts);

        posts = new Posts("Back to the Future", "Science Fiction", "1985");
        notificationsList.add(posts);

        posts = new Posts("Raiders of the Lost Ark", "Action & Adventure", "1981");
        notificationsList.add(posts);

        posts = new Posts("Goldfinger", "Action & Adventure", "1965");
        notificationsList.add(posts);

        posts = new Posts("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        notificationsList.add(posts);

        //pAdapter.notifyDataSetChanged();
    }
}
