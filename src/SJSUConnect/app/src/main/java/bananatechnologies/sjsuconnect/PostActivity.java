package bananatechnologies.sjsuconnect;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jnirg on 5/24/2017.
 */

public class PostActivity extends Activity {

   // private List<Movie> movieList = new ArrayList<>();
    private List<Posts> postsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter pAdapter;
    private Button newPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);*/
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pAdapter = new PostAdapter(postsList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        /*this.newPost = (Button) findViewById(R.id.n ewPostButton);


        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"New POST!!!!!", Toast.LENGTH_SHORT).show();
            }
        });
*/

       /* recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Movie movie = movieList.get(position);
                Posts posts = postsList.get(position);
                //Toast.makeText(getApplicationContext(), posts.getFirst_name() + " is selected!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        prepareMovieData();
    }

    private void prepareMovieData() {

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
        postsList.add(posts);

        pAdapter.notifyDataSetChanged();
    }

}
