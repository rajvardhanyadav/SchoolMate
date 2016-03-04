package com.geek.schoolmate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.model.Blog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by ry41071 on 15-02-2016.
 */
public class BlogRecyclerViewAdapter extends RecyclerView.Adapter<BlogRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Blog> blogs;
    private ImageLoader imageLoader;
    private View.OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textViewUserDesig;
        TextView textViewBlogTitle;
        TextView textViewBlogDesc;
        TextView textViewBlogPostDate;
        TextView textViewBlogViews;
        ImageView imageViewBlogImg;
        ImageView imageViewUserDp;
        ImageView imageViewLike;
        ImageView imageViewFav;

        public ViewHolder(View view) {
            super(view);
            textViewUserName = (TextView) view.findViewById(R.id.textViewUserName);
            textViewUserDesig = (TextView) view.findViewById(R.id.textViewUserDesignation);
            textViewBlogTitle = (TextView) view.findViewById(R.id.textViewBlogTitle);
            textViewBlogDesc = (TextView) view.findViewById(R.id.textViewBlogDesc);
            textViewBlogPostDate = (TextView) view.findViewById(R.id.textViewBlogPostDate);
            textViewBlogViews = (TextView) view.findViewById(R.id.textViewViews);
            imageViewUserDp = (ImageView) view.findViewById(R.id.imageViewProfilePhoto);
            imageViewBlogImg = (ImageView) view.findViewById(R.id.imageViewBlogImg);
            imageViewLike = (ImageView) view.findViewById(R.id.imageViewLike);
            imageViewFav = (ImageView) view.findViewById(R.id.imageViewFav);
        }
    }

    public BlogRecyclerViewAdapter(ArrayList<Blog> blogs, ImageLoader imageLoader, View.OnClickListener onClickListener) {
        this.blogs = blogs;
        this.imageLoader = imageLoader;
        this.onClickListener = onClickListener;
    }

    @Override
    public BlogRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_blog, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        Blog blog = blogs.get(position);
        holder.textViewUserName.setText(blog.getUserName());
        holder.textViewUserDesig.setText(blog.getUserDesignation());
        holder.textViewBlogTitle.setText(blog.getBlogTitle());
        holder.textViewBlogDesc.setText(blog.getBlogDesc());
        holder.textViewBlogPostDate.setText(blog.getBlogPostDate());
        holder.textViewBlogViews.setText(blog.getViews() + " views");
        imageLoader.displayImage(blog.getUserProfilePhotoUrl(), holder.imageViewUserDp);
        imageLoader.displayImage(blog.getBlogImageUrl().get(0), holder.imageViewBlogImg);
        holder.imageViewLike.setOnClickListener(onClickListener);
        holder.imageViewFav.setOnClickListener(onClickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return blogs.size();
    }
}
