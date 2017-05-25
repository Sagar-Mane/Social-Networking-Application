package bananatechnologies.sjsuconnect;

/**
 * Created by jnirg on 5/17/2017.
 */

public class Friends {
    private String first_name, post, picture,email;

    public Friends() {
    }

    public Friends(String title, String genre, String year,String email) {
        this.first_name = title;
        this.post = genre;
        this.picture = year;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
