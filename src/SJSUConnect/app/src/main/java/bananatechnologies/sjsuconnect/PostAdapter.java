package bananatechnologies.sjsuconnect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    //private List<Movie> moviesList;
    private List<Posts> postList;
    public Button btnButton1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public Button accept,decline;

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
    }

    public int getItemCount() {
        return postList.size();
    }
}
