package online.propaans.goodr.goodr;

public class Sell {

    private String selluserId;
    private String selluserName;
    private String sellfood;
    private String selluserNumber;
    private String selluserQuantity;
    private String selluserAddress;
    private String sellPrice;



    public Sell(){

    }

    public Sell(String userId, String selluserName, String sellfood , String selluserNumber , String selluserQuantity , String selluserAddress , String sellPrice) {
        this.selluserId = userId;
        this.selluserName = selluserName;
        this.sellfood = sellfood;
        this.selluserNumber = selluserNumber;
        this.selluserQuantity = selluserQuantity;
        this.selluserAddress = selluserAddress;
        this.sellPrice = sellPrice;
    }

    public String getSelluserId() {
        return selluserId;
    }

    public String getSelluserName() {
        return selluserName;
    }

    public String getSellfood() {
        return sellfood;
    }

    public  String getSelluserNumber(){
        return selluserNumber;
    }

    public String getSelluserQuantity(){
        return selluserQuantity;
    }

    public String getSelluserAddress(){
        return selluserAddress;
    }
    public String getSellPrice(){
        return sellPrice;
    }


}
