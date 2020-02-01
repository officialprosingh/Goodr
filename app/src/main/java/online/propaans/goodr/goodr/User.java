package online.propaans.goodr.goodr;


public class User {
    public String name, email , snumber;
    private String smoney;


    public User(String name, String email, String snumber , String smoney) {
        this.name = name;
        this.email = email;
        this.snumber = snumber;
        this.smoney = smoney;
    }
    public String getName(){
        return name;
    }

    public String getMoney(){
        return smoney;
    }



    public String getEmail(){
        return email;
    }


}
