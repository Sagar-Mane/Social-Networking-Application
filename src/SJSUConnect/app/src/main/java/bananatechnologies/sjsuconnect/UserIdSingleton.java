package bananatechnologies.sjsuconnect;

/**
 * Created by Sagar Mane on 15-05-2017.
 */

public class UserIdSingleton {

    String user_id;
    String last_name;
    String first_name;
    private UserIdSingleton(){}

    private static UserIdSingleton instance=new UserIdSingleton();

    public static UserIdSingleton getInstance(){
        return instance;
    }

    public void setUserId(String email){
        user_id=email;
    }
    public String getUserId(){
        return user_id;
    }

    public void setLast_name(String last_name){
        this.last_name=last_name;
    }
    public String getLast_name(){
        return this.last_name;
    }

    public void setFirst_name(String first_name){
        this.first_name=first_name;
    }
    public String getFirst_name(){
        return this.first_name;
    }
}
