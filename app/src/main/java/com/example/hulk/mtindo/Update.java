package com.example.hulk.mtindo;

/**
 * Created by hulk on 12/10/15.
 */
public class Update {
    String Name, Description, Price, Tag;

    public Update(String Name,String Description, String Price, String Tag){
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Tag = Tag;
    }
    public String getTheName(){
        return this.Name;
    }
    public String getDescription(){
        return this.Description;
    }
    public String getPrice(){
        return this.Price;
    }
    public String getTag(){
        return this.Tag;
    }
}
