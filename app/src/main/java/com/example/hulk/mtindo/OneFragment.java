package com.example.hulk.mtindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

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
    View view;

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


        view = inflater.inflate(R.layout.fragment_one, container, false);
        Button butt = (Button) view.findViewById(R.id.button1);
        butt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Hair.class);
                getActivity().startActivity(i);

            }
        });








        //creating the circular button
        final com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton mymenu =
                new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(getActivity())
                        .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(getActivity());

        //Setting icons to the buttons
        ImageView helpIcon = new ImageView(getActivity());
        ImageView checkoutIcon = new ImageView(getActivity());
        ImageView makeUp = new ImageView(getActivity());
        ImageView homeIcon = new ImageView(getActivity());
        ImageView profileIcon = new ImageView(getActivity());

        //setting icons for the buttons
        checkoutIcon.setImageDrawable(getResources().getDrawable(R.drawable.iccamera));
        makeUp.setImageDrawable(getResources().getDrawable(R.drawable.iccamera));
        homeIcon.setImageDrawable(getResources().getDrawable(R.drawable.iccamera));
        profileIcon.setImageDrawable(getResources().getDrawable(R.drawable.iccamera));







        //creating the floating action menu subButtons being created dynamically
        final FloatingActionMenu myFloatingMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(rLSubBuilder.setContentView(homeIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(profileIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(checkoutIcon).build())
                .addSubActionView(rLSubBuilder.setContentView(makeUp).build())


                .attachTo(mymenu)
                .build();

        //Should be linked to the catalogue class
        checkoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(getActivity(), Createstore.class);
                startActivity(i3);
            }
        });
        makeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getActivity(), MainActivity.class);
                startActivity(i4);
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(getActivity(), Beauty_pros.class);
                startActivity(i5);
            }
        });
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(getActivity(), OffersSlider.class);
                startActivity(i6);
            }
        });












    // Inflate the layout for this fragment
    return view;
}



}