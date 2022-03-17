package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter (Context context, List<Post> posts) {
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

        private TextView tvUserName;
        private TextView tvDescription;
        private TextView tvSmallUserName;
        private TextView likesCount;
        private ImageView ivImage;
        private ImageView ivUserPFP;

        // Like Button
        private ImageButton likeButton;
        private int currentLikeButtonImage;
        int[] likeButtonImage = {R.drawable.ufi_heart, R.drawable.red_heart};
        // Comment Button
        private ImageButton commentButton;
        // Direct Button
        private ImageButton directButton;
        // Save Button
        private ImageButton saveButton;
        private int currentSaveButtonImage;
        int[] saveButtonImage = {R.drawable.save, R.drawable.ufi_save_active};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvSmallUserName = itemView.findViewById(R.id.tvSmallUser);
            ivUserPFP = itemView.findViewById(R.id.ivUserPFP);
            likesCount = itemView.findViewById(R.id.likesCount);
            likeButton = itemView.findViewById(R.id.likeButton);
            commentButton = itemView.findViewById(R.id.commentButton);
            directButton = itemView.findViewById(R.id.directButton);
            saveButton = itemView.findViewById(R.id.saveButton);

        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvSmallUserName.setText(post.getUser().getUsername());
            tvUserName.setText(post.getUser().getUsername());
            likesCount.setText(post.getLikesCount()+ " Likes");
            // Like Button
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentLikeButtonImage++;
                    currentLikeButtonImage = currentLikeButtonImage %likeButtonImage.length;
                    likeButton.setImageResource(likeButtonImage[currentLikeButtonImage]);
                }
            });
            //Comment Button
            commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Comment Button Works", Toast.LENGTH_SHORT).show();

                    // TODO: When clicked opens a recycler view that displays comment logo darkens
                    //       and recycler view shows up displaying all comments of the post and an
                    //       option to add a comment as well
                    //       When clicked again the recycler view closes and comment logo
                    //       whitens again
                }
            });
            // Direct Button
            directButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Direct Button Works", Toast.LENGTH_SHORT).show();
                    // Not Sure what to do for this right now
                }
            });
            // Save Button
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentSaveButtonImage++;
                    currentSaveButtonImage = currentSaveButtonImage %saveButtonImage.length;
                    saveButton.setImageResource(saveButtonImage[currentSaveButtonImage]);
                    // TODO: When clicked post  will be added to save fragment that is yet to be
                    //       created and save button is darken
                    //       When clicked again post is removed of the save recycler view and save
                    //       button will whiten
                }
            });
            //Posted Image
            ParseFile image = post.getImage();
            if(image != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }
            // PFP Image
            ParseFile pfp = post.getUser().getParseFile("userProfileImage");
            if(pfp != null) {
                Glide.with(context).load(pfp.getUrl()).into(ivUserPFP);
            }
        }
    }
}
