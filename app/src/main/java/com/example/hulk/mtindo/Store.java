package com.example.hulk.mtindo;

/**
 * Created by hulk on 12/10/15.
 */
public class Store {
    String UserName,Telephone,StoreName,Description;
    public Store(String UserName, String Telephone, String StoreName, String Description, String theinputimage){
        this.UserName = UserName;
        this.Telephone = Telephone;
        this.StoreName = StoreName;
        this.Description = Description;

    }
   public String getUserName(){
       return UserName;
   }
    public String getTelephone(){
        return Telephone;
    }
    public String getStoreName(){
        return StoreName;
    }
    public String getDescription(){
        return Description;
    }

    public Store(){

    }

}
