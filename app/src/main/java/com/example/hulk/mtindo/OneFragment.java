package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by maureen on 12/10/15.
 */

/*public class OneFragment extends Fragment{
View picview;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        picview =inflater.inflate(R.layout.fragment_one, container, false);
        //Initialize ImageView
        ImageView imageView = (ImageView) picview.findViewById(R.id.imageView );
        Picasso.with(getActivity())
                .load("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQJ6i4TOiVidyBVIpUjRAgNaB3LfLm_BOBi1HWU-DxRHeEaaV1x")
                .into(imageView);
        // Inflate the layout for this fragment



        CardView card = (CardView)picview .findViewById(R.id.card_view1);
        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Mtindo.class);
                getActivity().startActivity(i);

            }
        });
        return picview;






    }

}*/

public class OneFragment extends Fragment {

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}