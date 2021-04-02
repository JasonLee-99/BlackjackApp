package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Random;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b;
        ImageView img;
        b = (Button) view.findViewById(R.id.hit);
        img = (ImageView) view.findViewById(R.id.dealer_pos1);

        //change user 2nd card
        ImageView user2;
        String user2_temp;
        user2 = (ImageView) view.findViewById(R.id.user2);
        user2_temp = generateCard();
        int drawableId = getResources().getIdentifier(user2_temp, "drawable", getActivity().getPackageName());
        user2.setImageResource(drawableId);

        //change user 2nd card

        ImageView user1;
        String user1_temp;
        user1 = (ImageView) view.findViewById(R.id.user1);
        user1_temp = generateCard();
        int user2_id = getResources().getIdentifier(user1_temp, "drawable", getActivity().getPackageName());
        user1.setImageResource(user2_id);

        //change dealer 1st card

        ImageView deal1;
        String deal1_temp;
        deal1 = (ImageView) view.findViewById(R.id.dealer1);
        deal1_temp = generateCard();
        int deal1_id = getResources().getIdentifier(deal1_temp, "drawable", getActivity().getPackageName());
        deal1.setImageResource(deal1_id);


        //change user 2nd card

        ImageView deal2;
        String deal2_temp;
        deal2 = (ImageView) view.findViewById(R.id.dealer2);
        deal2_temp = generateCard();
        int deal2_id = getResources().getIdentifier(deal2_temp, "drawable", getActivity().getPackageName());
        deal2.setImageResource(deal2_id);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_temp = generateCard();
                System.out.println(str_temp);
                int drawableId = getResources().getIdentifier(str_temp, "drawable", getActivity().getPackageName());

                img.setImageResource(drawableId);
            }
        });

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });




    }

    public static String generateCard(){

        String[] suits = {"d", "c", "h", "s"};
        int randomNum = 1+ (int)(Math.random() * 13);
        int randomSuit = (int)(Math.random() * 4);


        String str_temp = (suits[randomSuit]) + randomNum;
        return  str_temp;
    }

}