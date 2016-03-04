package com.geek.schoolmate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.geek.schoolmate.R;
import com.geek.schoolmate.adapter.HomeFragmentPagerAdapter;
import com.geek.schoolmate.network.NetworkManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    private final String TAG = "MainActivity";
    private final int FILTER_TASK_REQUEST_CODE = 100;
    ViewPager viewPager;
    TabLayout tabLayout;
    private ImageLoader mImageLoader = null;
    private DisplayImageOptions mDisplayImageOptions;
    private ImageLoaderConfiguration mImageLoaderConfiguration;
    private NetworkManager mNetworkManager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView(R.layout.tab);
        } else {
            Log.d(TAG, "Actionbar is null");
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);

        tabLayout = (TabLayout) actionBar.getCustomView().findViewById(R.id.tabs);*/

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(homeFragmentPagerAdapter.getTabView(i));
        }
    }

    public ImageLoader getImageLoader() {
        Log.d(TAG, "getImageLoader()");
        if (mDisplayImageOptions == null) {
            mDisplayImageOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(false)
                    .cacheOnDisk(true)
                    .build();
        }
        if (mImageLoaderConfiguration == null) {
            mImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(getApplication())
                    .defaultDisplayImageOptions(mDisplayImageOptions).threadPriority(Thread.NORM_PRIORITY - 2)
                    .tasksProcessingOrder(QueueProcessingType.FIFO)
                    .build();
        }
        if (mImageLoader == null) {
            mImageLoader = ImageLoader.getInstance();
            mImageLoader.init(mImageLoaderConfiguration);
        }
        return mImageLoader;
    }

    public NetworkManager getNetworkManager() {
        Log.d(TAG, "getNetworkManager()");
        if (mNetworkManager == null) {
            mNetworkManager = new NetworkManager(this);
        }
        return mNetworkManager;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected()");
        switch (item.getItemId()) {
            case R.id.item_search:
                return true;
            case R.id.item_favourites:
                return true;
            case R.id.item_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                return true;
            case R.id.item_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.item_setting:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                int position = viewPager.getCurrentItem();
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, BlogCreateActivity.class));
                        break;
                    case 1:
                        Intent intent = new Intent(MainActivity.this, FilterTaskActivity.class);
                        startActivityForResult(intent, FILTER_TASK_REQUEST_CODE);
//                        startActivity(new Intent(MainActivity.this, TaskCreateActivity.class));
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_create_white_24dp));
                break;
            case 1:
                floatingActionButton.setImageDrawable(getDrawable(R.drawable.ic_sort_white_24dp));
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILTER_TASK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                }
                break;
        }
    }
}