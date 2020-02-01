package online.propaans.goodr.goodr;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Artist {
    private String userId;
    private String userName;
    private String food;
    private String userNumber;
    private String userQuantity;
    private String userAddress;
    private String money;



    public Artist(String id, String name, String genre, String number, String quantity, String address){

    }

    public Artist(String userId, String userName, String food , String userNumber , String userQuantity , String userAddress, String money) {
        this.userId = userId;
        this.userName = userName;
        this.food = food;
        this.money = money;
        this.userNumber = userNumber;
        this.userQuantity = userQuantity;
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFood() {
        return food;
    }

    public  String getUserNumber(){
        return userNumber;
    }

    public String getUserQuantity(){
        return userQuantity;
    }

    public String getUserAddress(){
        return userAddress;
    }

    public String getMoney(){
        return money;
   }


}
