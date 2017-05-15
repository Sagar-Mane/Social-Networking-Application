package bananatechnologies.sjsuconnect;

/**
 * Created by Sagar Mane on 15-05-2017.
 */

public class UserIdSingleton {

    String user_id;
    private UserIdSingleton(){}

    private static final UserIdSingleton instance=new UserIdSingleton();

    public static UserIdSingleton getInstance(){
        return instance;
    }

    public void setUserId(String email){
        user_id=email;
    }
    public String getUserId(){
        return user_id;
    }
}
