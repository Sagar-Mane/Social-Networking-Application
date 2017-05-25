package bananatechnologies.sjsuconnect;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;

public class MainScreen extends AppCompatActivity {

    PagerAdapter adpter;
    PagerTabStrip header;
    private RecyclerView recyclerView;
    private PostAdapter pAdapter;
    private Button newPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ViewPager vp=(ViewPager) findViewById(R.id.pager);
        adpter=new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adpter);
         // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.header_tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(vp);

    }



}
