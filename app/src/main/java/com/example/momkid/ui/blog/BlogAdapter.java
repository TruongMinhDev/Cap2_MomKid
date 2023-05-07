package com.example.momkid.ui.blog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private List<BlogDto> mBlogs;

    private Context context;

    public BlogAdapter(List<BlogDto> mBlogs, Context context) {
        this.mBlogs = mBlogs;
        this.context = context;
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
        holder.tvBlogContent.setText( blog.getContent());
        holder.tvBlogName.setText(blog.getName());
    }

    @Override
    public int getItemCount() {
        return mBlogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBlogContent;
        public TextView tvBlogName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBlogContent = itemView.findViewById(R.id.tvBlogContent);
            tvBlogName = itemView.findViewById(R.id.tvBlogName);
        }
    }
}
