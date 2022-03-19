package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_CREATED_TIME = "createdAt";
    public static final String KEY_LIKES_COUNT = "LikesCount";
    public static final String KEY_POST_TIME = "createdAt";

//Description
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

//User
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

//Posted Image
    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }
//Likes Count
    public int getLikesCount() {
        return getInt(KEY_LIKES_COUNT);
    }
// Time of Post
    public Date getPostDate() {
        Date date = new Date(2022, 3, 19, 9, 47, 49);
        return date;
    }
}
