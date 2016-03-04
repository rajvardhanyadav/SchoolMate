package com.geek.schoolmate.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.ui.fragment.BlogFragment;
import com.geek.schoolmate.ui.fragment.NotificationFragment;
import com.geek.schoolmate.ui.fragment.TaskFragment;

/**
 * Created by ry41071 on 15-02-2016.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private int[] imageResId = {
            R.drawable.ic_home_white_24dp,
            R.drawable.ic_playlist_add_check_white_24dp,
            R.drawable.ic_notifications_white_24dp
    };
    private String tabTitles[] = new String[]{"Blog", "Task", "Notifications"};
    private Context context;

    public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BlogFragment.newInstance();
            case 1:
                return TaskFragment.newInstance();
            case 2:
                return NotificationFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
        /*Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;*/
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.textViewTab);
        tv.setText(tabTitles[position]);
        return v;
    }
}