package com.example.instagram.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.instagram.LoginActivity;
import com.example.instagram.MainActivity;
import com.example.instagram.Post;
import com.example.instagram.PostsAdapter;
import com.example.instagram.ProfileAdapter;
import com.example.instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    private RecyclerView rvPosts;
    private ProfileAdapter profileAdapter;
    private List<Post> allPosts;

    private TextView tvProfileUserName;
    private TextView tvProfileTotalPosts;
    private TextView tvProfileBio;
    private ImageView userPFP;

    private Button buttonLogOut;
    private ImageButton changeProfileImageButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        rvPosts = getView().findViewById(R.id.profileFragmentPosts);
        allPosts = new ArrayList<>();
        profileAdapter = new ProfileAdapter(getContext(), allPosts);

        tvProfileUserName = view.findViewById(R.id.tvProfileUserName);
        tvProfileBio = view.findViewById(R.id.tvProfileBio);
        tvProfileTotalPosts = view.findViewById(R.id.tvProfileTotalPosts);
        userPFP = view.findViewById(R.id.ivUserProfileImage);

        buttonLogOut = view.findViewById(R.id.profileButtonLogOut);
        changeProfileImageButton = view.findViewById(R.id.addChangeProfileImage);

        tvProfileUserName.setText(currentUser.getUsername());
        tvProfileTotalPosts.setText(Post.KEY_USER.length() + " Posts");
        tvProfileBio.setText(currentUser.getString("userBio"));
        // PFP Image
        ParseFile pfp = currentUser.getParseFile("userProfileImage");
        if(pfp != null) {
            Glide.with(getContext()).load(pfp.getUrl()).into(userPFP);
        }

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLoginActivity();
            }
        });

        changeProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Change Profile Image", Toast.LENGTH_SHORT).show();
            }
        });


        // Set the adapter on the recycler view
        rvPosts.setAdapter(profileAdapter);
        // Set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    private void goLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }

    private void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_TIME);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                profileAdapter.notifyDataSetChanged();
            }
        });
    }
}