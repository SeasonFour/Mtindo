package com.example.hulk.mtindo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by hulk on 12/14/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    TextView Name,Description,Price,Tag;
    public ViewHolder(View itemView) {
        super(itemView);
        Name = (TextView) itemView.findViewById(R.id.Nameu);
        Description = (TextView) itemView.findViewById(R.id.DescriptionU);
        Price = (TextView) itemView.findViewById(R.id.PriceU);
        Tag = (TextView) itemView.findViewById(R.id.TagU);
    }
}
