package bananatechnologies.sjsuconnect;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.ContentValues.TAG;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.MyViewHolder> {

    //private List<Movie> moviesList;
    private List<Friends> friendsList;
    public Button btnButton1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public Button accept,decline;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);

            accept = (Button) view.findViewById(R.id.accept);
            decline = (Button) view.findViewById(R.id.decline);
        }
    }


    public FriendRequestAdapter(List<Friends> friendList) {
        this.friendsList = friendList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frequest_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Friends friendreq = friendsList.get(position);
        Friends friends= friendsList.get(position);

        holder.title.setText(friends.getFirst_name());
        holder.genre.setText(friends.getPost());
        holder.year.setText(friends.getPicture());


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = position;

                JSONObject AFriend = new JSONObject();
                try {

                    AFriend.put("email",UserIdSingleton.getInstance().getUserId().toString());
                    AFriend.put("friend_email",friendsList.get(position).getEmail().toString());
                    AFriend.put("first_name", friendsList.get(position).getFirst_name().toString());

                    FriendRequestsViewFragment.addFriends(AFriend);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = position;
            }
        });

    }

    public int getItemCount() {
        return friendsList.size();
    }
}
