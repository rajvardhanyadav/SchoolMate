package com.geek.schoolmate.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.model.Blog;
import com.geek.schoolmate.utils.Constants;

public class BlogDetailsActivity extends BaseActivity {
    private final String TAG = "BlogDetailsActivity";
    private TextView textViewUserName;
    private TextView textViewUserDesig;
    private TextView textViewBlogTitle;
    private TextView textViewBlogDesc;
    private TextView textViewBlogPostDate;
    private TextView textViewBlogViews;
    private ImageView imageViewUserDp;
    private Blog blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);
        blog = getIntent().getParcelableExtra(Constants.PARC_BLOG);
        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserDesig = (TextView) findViewById(R.id.textViewUserDesignation);
        textViewBlogTitle = (TextView) findViewById(R.id.textViewBlogTitle);
        textViewBlogDesc = (TextView) findViewById(R.id.textViewBlogDesc);
        textViewBlogPostDate = (TextView) findViewById(R.id.textViewBlogPostDate);
        textViewBlogViews = (TextView) findViewById(R.id.textViewViews);
        imageViewUserDp = (ImageView) findViewById(R.id.imageViewUserDp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (blog != null) {
            textViewUserName.setText(blog.getUserName());
            textViewUserDesig.setText(blog.getUserDesignation());
            textViewBlogTitle.setText(blog.getBlogTitle());
            textViewBlogDesc.setText(blog.getBlogDesc());
            textViewBlogPostDate.setText(blog.getBlogPostDate());
            textViewBlogViews.setText(blog.getViews() + " views");
        }
    }
}
