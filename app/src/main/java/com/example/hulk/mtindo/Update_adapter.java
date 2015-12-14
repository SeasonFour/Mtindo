package com.example.hulk.mtindo;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

/**
 * Created by hulk on 12/14/15.
 */
public class Update_adapter extends FirebaseRecyclerViewAdapter<Update,ViewHolder> {

    Class<Update> mModelClass;
    protected int mModelLayout;
    Class<ViewHolder> mViewHolderClass;
    FirebaseArray mSnapshots;

    public Update_adapter(Class<Update> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);

        mModelClass = modelClass;
        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;
        mSnapshots = new FirebaseArray(ref);
    }

   @Override
    public void populateViewHolder(ViewHolder viewHolder, Update update) {

    }
}
