package com.example.hulk.mtindo;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

/**
 * Created by hulk on 12/16/15.
 */
public class Store_Adapter extends FirebaseRecyclerViewAdapter<Store , ViewHolder> {
    Class<Update> mModelClass;
    protected int mModelLayout;
    Class<ViewHolder> mViewHolderClass;
    FirebaseArray mSnapshots;
    public Store_Adapter(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mModelClass = modelClass;
        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;
        mSnapshots = new FirebaseArray(ref);
    }


    @Override
    public void populateViewHolder(ViewHolder viewHolder,Store store) {
        viewHolder.StoreName.setText(store.getStoreName());
        viewHolder.UserName.setText(store.getUserName());
        viewHolder.Telephone.setText(store.getTelephone());
        viewHolder.Description1.setText(store.getDescription());

    }
}
