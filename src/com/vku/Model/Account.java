
package com.vku.model;

/**
 *
 * @author admin!
 */
public class Account {
    
    public static String name = "";
    
    private String username;
    private String password;
    private String AccountNo;
    private String AccountType;
    private String Gender;
    private String Address;
    private String DateofBirth;
    private String Nationalty;
    private String Mobile;
    private double Balance;
    public Account() {
    }
//String Balance
    public Account(String username, String password, String AccountNo, String AccountType, String Gender, String Address, String DateofBirth, String Nationalty, String Mobile, double Balance) {
        this.username = username;
        this.password = password;
        this.AccountNo = AccountNo;
        this.AccountType = AccountType;
        this.Gender = Gender;
        this.Address = Address;
        this.DateofBirth = DateofBirth;
        this.Nationalty = Nationalty;
        this.Mobile = Mobile;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Account.name = name;
    }

    public Account(String username, String password) {
       this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String DateofBirth) {
        this.DateofBirth = DateofBirth;
    }

    public String getNationalty() {
        return Nationalty;
    }

    public void setNationalty(String Nationalty) {
        this.Nationalty = Nationalty;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", AccountNo=" + AccountNo + ", AccountType=" + AccountType + ", Gender=" + Gender + ", Address=" + Address + ", DateofBirth=" + DateofBirth + ", Nationalty=" + Nationalty + ", Mobile=" + Mobile + ", Balance=" + Balance + '}';
    }

  
 

}
