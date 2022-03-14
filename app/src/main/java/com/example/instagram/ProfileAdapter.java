package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public ProfileAdapter (Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_posts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView tvProfileUserName;
//        private TextView tvProfileTotalPosts;
//        private TextView tvProfileBio;
        //privateImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvProfileUserName = itemView.findViewById(R.id.tvProfileUserName);
//            tvProfileBio = itemView.findViewById(R.id.tvProfileBio);
//            tvProfileTotalPosts = itemView.findViewById(R.id.tvProfileTotalPosts);
        }

        public void bind(Post post) {
//            tvProfileUserName.setText("Bryan");
//            tvProfileTotalPosts.setText("9 " + "Posts");
//            tvProfileBio.setText(post.getUser().getString("userBio"));
        }
    }
}
