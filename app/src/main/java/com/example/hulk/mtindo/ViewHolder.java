package com.example.hulk.mtindo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by hulk on 12/14/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    TextView Name,Description,Price,Tag,storeName,userName, telephone, description1;
    ImageView imageViewStore;
    public ViewHolder(View itemView) {
        super(itemView);
        Name = (TextView) itemView.findViewById(R.id.Nameu);
        Description = (TextView) itemView.findViewById(R.id.DescriptionU);
        Price = (TextView) itemView.findViewById(R.id.PriceU);
        Tag = (TextView) itemView.findViewById(R.id.TagU);
        storeName = (TextView) itemView.findViewById(R.id.StoreName);
        userName = (TextView) itemView.findViewById(R.id.UserName);
        telephone = (TextView) itemView.findViewById(R.id.Telephone);
        description1 = (TextView) itemView.findViewById(R.id.Description1);
        imageViewStore = (ImageView) itemView.findViewById(R.id.imageViewStore);


    }
}
