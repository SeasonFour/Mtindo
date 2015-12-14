package com.example.hulk.mtindo;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rey.material.widget.TextView;

/**
 * Created by hulk on 12/14/15.
 */
public class ViewHolder extends RecyclerView.ViewHolder{
    TextView NameU,DescriptionU,PriceU,TagU;
    public ViewHolder(View itemView) {
        super(itemView);
        NameU = (TextView) itemView.findViewById(R.id.Nameu);
        DescriptionU = (TextView) itemView.findViewById(R.id.DescriptionU);
        PriceU = (TextView) itemView.findViewById(R.id.PriceU);
        TagU = (TextView) itemView.findViewById(R.id.TagU);
    }
}
