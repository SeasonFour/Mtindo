package com.example.hulk.mtindo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by hulk on 12/14/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    TextView Name,Description,Price,Tag,StoreName,UserName, Telephone, Description1;
    public ViewHolder(View itemView) {
        super(itemView);
        Name = (TextView) itemView.findViewById(R.id.Nameu);
        Description = (TextView) itemView.findViewById(R.id.DescriptionU);
        Price = (TextView) itemView.findViewById(R.id.PriceU);
        Tag = (TextView) itemView.findViewById(R.id.TagU);
        StoreName = (TextView) itemView.findViewById(R.id.StoreName);
        UserName = (TextView) itemView.findViewById(R.id.UserName);
        Telephone = (TextView) itemView.findViewById(R.id.Telephone);
        Description1 = (TextView) itemView.findViewById(R.id.Description1);

    }
}
