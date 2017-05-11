package bananatechnologies.sjsuconnect;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sagar Mane on 11-05-2017.
 * Don't change or modify before asking it contains dependencies.
 */

public class FriendRequestsViewFragment extends Fragment{
    private String title;
    private int page;

    public static FriendRequestsViewFragment newInstance(int page, String title) {

        FriendRequestsViewFragment fragmentFirst = new FriendRequestsViewFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.friend_requests_view_fragment,container,false);
        return rootview;
    }
}
