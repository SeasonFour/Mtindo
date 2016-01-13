package com.example.hulk.mtindo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

import java.io.IOException;

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
        try {
            viewHolder.imageViewStore.setImageBitmap(decodeFromBase64(store.getImageEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static Bitmap decodeFromBase64(String input) throws IOException {
        byte[] decodedByte = com.firebase.client.utilities.Base64.decode(input);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
