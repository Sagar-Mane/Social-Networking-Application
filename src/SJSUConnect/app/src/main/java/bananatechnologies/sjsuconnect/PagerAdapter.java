package bananatechnologies.sjsuconnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;


/**
 * Created by Sagar Mane on 11-05-2017.
 * Don't change or modify before asking it contains dependencies.
 */

public class PagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private static int NUM_ITEMS = 6;
    private int tabIcons[] = {R.mipmap.post,R.mipmap.fr, R.mipmap.profile, R.mipmap.notifications,R.mipmap.fr,R.mipmap.profile};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return PostFeedViewFragment.newInstance(0, "PostFeed");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return FriendRequestsViewFragment.newInstance(1, "Friend Requests");
            case 2: // Fragment # 0 - This will show FirstFragment different title
                return ProfileViewFragment.newInstance(2, "Profile");
            case 3: // Fragment # 0 - This will show FirstFragment different title
                return NotificationsViewFragment.newInstance(3, "Notifications");
            case 4: // Fragment # 0 - This will show FirstFragment different title
                return FriendsViewFragment.newInstance(4, "Friends");
            case 5: // Fragment # 0 - This will show FirstFragment different title
                return UsersViewFragment.newInstance(5, "Users");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }
}
