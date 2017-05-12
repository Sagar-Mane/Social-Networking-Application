package bananatechnologies.sjsuconnect;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;

public class MainScreen extends AppCompatActivity {

    PagerAdapter adpter;
    PagerTabStrip header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        ViewPager vp=(ViewPager) findViewById(R.id.pager);
        adpter=new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adpter);
         // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.header_tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(vp);

    }
}
