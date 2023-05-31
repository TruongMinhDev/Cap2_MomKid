package com.example.momkid.ui.blog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.example.momkid.service.IClickItemBlog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private List<BlogDto> mBlogs;

    private IClickItemBlog clickItemBlog;


    public BlogAdapter(List<BlogDto> mBlogs, IClickItemBlog clickItemBlog) {
        this.mBlogs = mBlogs;
        this.clickItemBlog=clickItemBlog;
    }

    public void setData(List<BlogDto> mBlogs){
        this.mBlogs = mBlogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View blogView = inflater.inflate(R.layout.rcv_blog_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(blogView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BlogDto blog = mBlogs.get(position);
        holder.tvBlogContent.setText(blog.getContent());
        holder.tvBlogName.setText(blog.getName());
        holder.tvName.setText(blog.getNameUser());

        holder.blogItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemBlog.onClickItemBlog(blog);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBlogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout blogItem;
        public TextView tvBlogContent;
        public TextView tvBlogName;

        public TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blogItem = itemView.findViewById(R.id.itemBlog);
            tvBlogContent = itemView.findViewById(R.id.tvBlogContent);
            tvBlogName = itemView.findViewById(R.id.tvBlogName);
            tvName=itemView.findViewById(R.id.tvNameUserBlog);
        }
    }
}
