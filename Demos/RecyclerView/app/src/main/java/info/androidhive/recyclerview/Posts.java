package info.androidhive.recyclerview;

/**
 * Created by jnirg on 5/17/2017.
 */

public class Posts {
    private String first_name, post, picture;

    public Posts() {
    }

    public Posts(String title, String genre, String year) {
        this.first_name = title;
        this.post = genre;
        this.picture = year;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String name) {
        this.first_name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post= post;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
