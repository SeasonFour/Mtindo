package com.example.hulk.mtindo;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

/**
 * Created by hulk on 12/16/15.
 */
public class Store_Adapter extends FirebaseRecyclerViewAdapter<Store , ViewHolder> {
    Class<Store> mModelClass2;
    protected int mModelLayout2;
    Class<ViewHolder> mViewHolderClass;
    FirebaseArray mSnapshots;
    public Store_Adapter(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mModelClass2 = modelClass;
        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;
        mSnapshots = new FirebaseArray(ref);
    }


    @Override
    public void populateViewHolder(ViewHolder viewHolder,Store store) {
        viewHolder.storeName.setText(store.getStoreName());
        viewHolder.userName.setText(store.getuserName());
        viewHolder.telephone.setText(store.getTelephone());
        viewHolder.description1.setText(store.getDescription());

    }
}
