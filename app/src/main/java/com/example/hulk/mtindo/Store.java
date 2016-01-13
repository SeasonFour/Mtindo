package com.example.hulk.mtindo;

/**
 * Created by hulk on 12/10/15.
 */
public class Store {
    String userName,telephone,storeName,description,imageEncoded;
    public Store(String UserName, String Telephone, String StoreName, String Description, String ImageEncoded ){
        this.userName = UserName;
        this.telephone = Telephone;
        this.storeName = StoreName;
        this.description = Description;
        this.imageEncoded = ImageEncoded;


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
    public String getImageEncoded(){return imageEncoded; }

   

    public Store(){

    }

}
