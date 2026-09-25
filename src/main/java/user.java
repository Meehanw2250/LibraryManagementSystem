public class user {
    private String name;
    private String userID;
    private String address;
    private Double balance;

    public user(String userID, String name, String address, Double balance) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public String getUserID() {
        return userID;
    }
    public String getAddress() {
        return address;
    }
    public Double getBalance() {
        return balance;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "UserID: " + userID + ", Name: " + name + ", Address: " + address + ", Balance: " + String.format("%.2f", balance);
    }
}

