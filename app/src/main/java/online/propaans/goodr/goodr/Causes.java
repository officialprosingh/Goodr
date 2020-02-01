package online.propaans.goodr.goodr;

import com.google.firebase.database.IgnoreExtraProperties;


public class Causes {

    private String userId;
    private String c_name;
    private String c_people;
    private String c_state;


    public Causes(){

    }

    public Causes(String userId, String c_name, String c_state, String c_people) {
        this.userId = userId;
        this.c_name = c_name;
        this.c_people = c_people;
        this.c_state = c_state;
    }

    public String getUserId() {
        return userId;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_people() {
        return c_people;
    }

    public  String getC_state(){
        return c_state;
    }



}

