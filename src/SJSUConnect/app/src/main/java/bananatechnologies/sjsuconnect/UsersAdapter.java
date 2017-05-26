package bananatechnologies.sjsuconnect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    //private List<Movie> moviesList;
    private List<Friends> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        Button add;
        Button follow;
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);

            add = (Button) view.findViewById(R.id.add);
            follow = (Button) view.findViewById(R.id.follow);
        }
    }

    public UsersAdapter(List<Friends> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //Movie movie = moviesList.get(position);
        Friends posts = postList.get(position);

        holder.title.setText(posts.getFirst_name());
        holder.genre.setText(posts.getPost());
        holder.year.setText(posts.getPicture());


        holder.add.setOnClickListener(new View.OnClickListener() {
            int temp = position;
            @Override
            public void onClick(View v) {
                JSONObject AFriend = new JSONObject();
                try {

                    AFriend.put("email",UserIdSingleton.getInstance().getUserId().toString());
                    AFriend.put("friend_email",postList.get(position).getEmail().toString());
                    AFriend.put("first_name", UserIdSingleton.getInstance().getFirst_name().toString());

                    UsersViewFragment.addFriends(AFriend);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.follow.setOnClickListener(new View.OnClickListener() {
            int temp = position;
            @Override
            public void onClick(View v) {
                JSONObject AFriend = new JSONObject();
                try {

                    AFriend.put("email",UserIdSingleton.getInstance().getUserId().toString());
                    AFriend.put("friend_email",postList.get(position).getEmail().toString());
                    AFriend.put("first_name", UserIdSingleton.getInstance().getFirst_name().toString());

                    UsersViewFragment.follow(AFriend);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getItemCount() {
        return postList.size();
    }



}
