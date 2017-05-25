package bananatechnologies.sjsuconnect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    //private List<Movie> moviesList;
    private List<Posts> postList;
    public Button btnButton1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public Button updatePost;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public PostAdapter(List<Posts> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.posts_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //Movie movie = moviesList.get(position);
        Posts posts = postList.get(position);

        holder.title.setText(posts.getFirst_name());
        holder.genre.setText(posts.getPost());
        holder.year.setText(posts.getPicture());


        /*holder.*/
    }

    public int getItemCount() {
        return postList.size();
    }



}
