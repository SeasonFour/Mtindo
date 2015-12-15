package com.example.hulk.mtindo;

/**
 * Created by hulk on 12/10/15.
 */
public class Update {
    String theName, description, price, tag;

    public Update(String Name,String Description, String Price, String Tag){
        this.theName = Name;
        this.description = Description;
        this.price = Price;
        this.tag = Tag;
    }
    public String getTheName(){
        return theName;
    }
    public String getDescription(){
        return description;
    }
    public String getPrice(){
        return price;
    }
    public String getTag(){
        return tag;
    }
    //Very important hwen reading from firebase
    public Update(){

    }
}
