package com.example.hulk.mtindo;

/**
 * Created by hulk on 12/10/15.
 */
public class Store {
    String userName,telephone,storeName,description;
    public Store(String UserName, String Telephone, String StoreName, String Description){
        this.userName = UserName;
        this.telephone = Telephone;
        this.storeName = StoreName;
        this.description = Description;


    }
   public String getuserName(){
       return userName;
   }
    public String getTelephone(){
        return telephone;
    }
    public String getStoreName(){
        return storeName;
    }
    public String getDescription(){
        return description;
    }
   

    public Store(){

    }

}
