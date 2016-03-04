package com.geek.schoolmate.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geek.schoolmate.R;
import com.geek.schoolmate.adapter.BlogRecyclerViewAdapter;
import com.geek.schoolmate.callback.IGetBlogsList;
import com.geek.schoolmate.model.Blog;
import com.geek.schoolmate.ui.activity.BlogDetailsActivity;
import com.geek.schoolmate.ui.activity.MainActivity;
import com.geek.schoolmate.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ry41071 on 15-02-2016.
 */
public class BlogFragment extends Fragment implements IGetBlogsList, View.OnClickListener {
    public static final String TAG = "BlogFragment";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Blog> mBlog;

    public static BlogFragment newInstance() {
        Log.d(TAG, "newInstance()");
        BlogFragment fragment = new BlogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.blog_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
        ((MainActivity) getActivity()).getNetworkManager().getBlogList(this);
    }

    @Override
    public void onGetBlogsListSuccessCallback(Object response) {
        Log.d(TAG, "onGetBlogsListSuccessCallback()");
        mBlog = (ArrayList<Blog>) response;
        Log.d(TAG, "Total mBlog " + mBlog.size());
        mAdapter = new BlogRecyclerViewAdapter(mBlog, ((MainActivity) getActivity()).getImageLoader(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGetBlogsListFailureCallback(Object response) {
        Log.d(TAG, "onGetBlogsListFailureCallback()");
        Toast.makeText(getActivity(), "Unable to fetch mBlog.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.linearLayoutRootBlog:
                int position = (int) v.getTag();
                Intent intent = new Intent(getActivity(), BlogDetailsActivity.class);
                intent.putExtra(Constants.PARC_BLOG, mBlog.get(position));
                startActivity(intent);
                break;
            case R.id.imageViewLike:
                Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageViewFav:
                Toast.makeText(getActivity(), "Fav", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}