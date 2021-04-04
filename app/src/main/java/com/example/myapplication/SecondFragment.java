package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.w3c.dom.Text;

import java.util.Random;


public class SecondFragment extends Fragment {

    public static final int nCards = 52;
    private Card[] deck;
    private Card[] user ;
    private Card[] dealer;
    private Card[]temp;
    private int currentCard;
    private int currentHandCard;
    private int dealerHandSize;
    private int playerFinalTotal;
    private int DealerFinalTotal;
    ImageView img;
    Button b;
    Button b2;
    TextView userTotal;
    TextView dealerTotal;
    TextView winner;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //variables

        super.onViewCreated(view, savedInstanceState);
        b = (Button) view.findViewById(R.id.hit);
        b2 = (Button) view.findViewById(R.id.stand);
        deck = new Card[nCards];

        deck = createDeck();

        user = new Card[nCards];
        dealer = new Card[nCards];

        shuffleDeck();
        for(int i = 0; i < 2; i++){
            user[i] = deal();
            dealer[i] = deal();
        }

        currentHandCard = 1; //represents array index [1] so, 2 cards.
        dealerHandSize =1;
        //generate pictures

        //change user 2nd card
        ImageView user2;
        String user2_temp;
        user2 = (ImageView) view.findViewById(R.id.user2);
        user2_temp = user[1].suit;
        int drawableId = getResources().getIdentifier(user2_temp, "drawable", getActivity().getPackageName());
        user2.setImageResource(drawableId);

        //change user 2nd card

        ImageView user1;
        String user1_temp;
        user1 = (ImageView) view.findViewById(R.id.user1);
        user1_temp = user[0].suit;
        int user2_id = getResources().getIdentifier(user1_temp, "drawable", getActivity().getPackageName());
        user1.setImageResource(user2_id);

        //change dealer 1st card

        ImageView deal1;
        String deal1_temp;
        deal1 = (ImageView) view.findViewById(R.id.dealer1);
        deal1_temp = dealer[0].suit;
        int deal1_id = getResources().getIdentifier("red_back", "drawable", getActivity().getPackageName());
        deal1.setImageResource(deal1_id);


        //change user 2nd card

        ImageView deal2;
        String deal2_temp;
        deal2 = (ImageView) view.findViewById(R.id.dealer2);
        deal2_temp = dealer[1].suit;
        int deal2_id = getResources().getIdentifier(deal2_temp, "drawable", getActivity().getPackageName());
        deal2.setImageResource(deal2_id);




        //need to check hand total
        int int_handTotal;
        int_handTotal = checkHandTotal(user);

        userTotal = (TextView)view.findViewById(R.id.user_total_text);
        userTotal.setText("" + int_handTotal);

        winbustCheck(int_handTotal);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentHandCard++;
                temp = new Card[1];
                temp[0] = deal();
                String str_temp = temp[0].suit;
                user[currentHandCard] = temp[0];


                int count = 0;

                for(int i = 0; i < user.length; i ++){
                    if(user[i] != null){
                        count++;
                    }
                }

                if(count == 3) {
                    img = (ImageView) view.findViewById(R.id.user_pos3);
                }else if(count == 4){
                    img = (ImageView) view.findViewById(R.id.user_pos4);
                }else if(count == 5){
                    img = (ImageView) view.findViewById(R.id.user_pos5);
                }
                int drawableId = getResources().getIdentifier(str_temp, "drawable", getActivity().getPackageName());

                img.setImageResource(drawableId);

               int int_handTotal = checkHandTotal(user);

                userTotal = (TextView)view.findViewById(R.id.user_total_text);
                userTotal.setText("" + int_handTotal);
               winbustCheck(int_handTotal);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerFinalTotal = checkHandTotal(user);

                //checking for ace conversion
                if(playerFinalTotal < 21){
                    for(int i = 0; i < currentHandCard ; i++){
                        int card = Integer.parseInt( user[i].suit.substring(1, user[i].suit.length())  ) ;
                        if(card == 1){
                            if((playerFinalTotal + 10) <= 21){
                                playerFinalTotal += 10;
                            }
                        }
                    }
                }

                userTotal = (TextView)view.findViewById(R.id.user_total_text);
                userTotal.setText("" + playerFinalTotal);

                System.out.println("The player's final hand total is: " + playerFinalTotal);
                b.setEnabled(false);
                b2.setEnabled(false);

                //need to do Dealer stuff here

                //reveal dealer's card 1
                ImageView deal1;
                String deal1_temp;
                deal1 = (ImageView) view.findViewById(R.id.dealer1);
                deal1_temp = dealer[0].suit;
                int deal1_id = getResources().getIdentifier(deal1_temp,"drawable", getActivity().getPackageName());
                deal1.setImageResource(deal1_id);

                int int_dealerTotal;

                int_dealerTotal = checkHandTotal(dealer);
                System.out.println(int_dealerTotal);
                //check for aces
                if(int_dealerTotal < 16) {
                    for (int i = 0; i < dealerHandSize; i++) {
                        int card = Integer.parseInt(dealer[i].suit.substring(1, dealer[i].suit.length()));
                        if (card == 1) {
                            if ((int_dealerTotal + 10) < 21) {
                                int_dealerTotal += 10;
                            }
                        }
                    }
                }

                //card 1
                if(int_dealerTotal < 16) {
                    temp = new Card[1];
                    temp[0] = deal();
                    dealerHandSize++;
                    dealer[2] = temp[0];
                    String str_temp = temp[0].suit;

                    img = (ImageView) view.findViewById(R.id.dealer_pos3);

                    int drawableId = getResources().getIdentifier(str_temp, "drawable", getActivity().getPackageName());
                    img.setImageResource(drawableId);

                    int_dealerTotal = checkHandTotal(dealer);

                }
                    if(int_dealerTotal < 16){
                        Card[] temp2 = new Card[1];
                        temp2[0] = deal();
                        dealerHandSize++;
                        dealer[3] = temp2[0];
                        String str_temp2 = temp2[0].suit;

                        img = (ImageView) view.findViewById(R.id.dealer_pos4);

                        int drawableId2 = getResources().getIdentifier(str_temp2, "drawable", getActivity().getPackageName());
                        img.setImageResource(drawableId2);

                        int_dealerTotal = checkHandTotal(dealer);
                    }

                DealerFinalTotal = checkHandTotal(dealer);
                if(DealerFinalTotal < 21){
                    for(int i = 0; i < dealerHandSize ; i++){
                        int card = Integer.parseInt( dealer[i].suit.substring(1, dealer[i].suit.length())  ) ;
                        if(card == 1){
                            if((DealerFinalTotal + 10) < 21){
                                DealerFinalTotal += 10;
                            }
                        }
                    }
                }

                System.out.println("The dealers's final hand total is: " + DealerFinalTotal);

                dealerTotal = (TextView)view.findViewById(R.id.dealer_total_text);
                dealerTotal.setText("" + DealerFinalTotal);
                String check = " ";
                check = compareHands(playerFinalTotal,DealerFinalTotal);

                String finalMsg = (check + "  wins!!!");
                if(check.equals("tie")){
                    finalMsg = "Its a tie!";
                }
                winner = (TextView)view.findViewById(R.id.winner);
                winner.setText(finalMsg);

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


    public static int rollCard() {
        int card = (int) (Math.random() * nCards);
        return card;
    }

    public Card[] createDeck(){
        int i = 0;

        deck = new Card[nCards];
        for ( int suit = Card.DIAMOND; suit <= Card.SPADE; suit++ )
            for ( int rank = 1; rank <= 13; rank++ )
                deck[i++] = new Card(suit, rank);

        currentCard = 0;

        return deck;
    }

    public void displayDeck() {
        for (int i = 0; i < nCards; i++)
            System.out.println(deck[i].suit);
    }

    public Card deal()
    {
        if ( currentCard < nCards )
        {
            return (deck[currentCard++]);
        }
        else
        {
            System.out.println("No more cards to deal from the deck");
            return (null);
        }
    }

    public void shuffleDeck()
    {

        for(int i = 0; i < (nCards / 2); i++)
        {
            int cardOne = rollCard();
            int cardTwo = rollCard();

            Card temp = deck[cardOne];
            deck[cardOne] = deck[cardTwo];
            deck[cardTwo] = temp;
        }
        currentCard = 0;
    }

    public int checkHandTotal(Card[] user){

        int int_handTotal;
        boolean flag = false;
        int card1 = Integer.parseInt( user[0].suit.substring(1, user[0].suit.length())  ) ;
        int card2 = Integer.parseInt( user[1].suit.substring(1, user[1].suit.length())  ) ;

        if(card1 == 10 || card1 == 11 || card1 == 12 || card1 == 13){
            card1 = 10;
            if(card2 == 1){
                card2 = 11;
                flag = true;
            }
        }

        if((card2 == 10 || card2 == 11 || card2 == 12 || card2 == 13) && flag == false){
            card2 = 10;
            if(card1 == 1){
                card1 = 11;
            }
        }
        int_handTotal = card1 + card2;

        if(user[2] != null){
            int card3 = Integer.parseInt( user[2].suit.substring(1, user[2].suit.length())  ) ;

            if(card3 == 10 || card3 == 11 || card3 == 12 || card3 == 13){
                card3 = 10;
            }

            int_handTotal = int_handTotal + card3;

        }

        if(user[3] != null){
            int card4 = Integer.parseInt( user[3].suit.substring(1, user[3].suit.length())  ) ;

            if(card4 == 10 || card4 == 11 || card4 == 12 || card4 == 13){
                card4 = 10;
            }

            int_handTotal = int_handTotal + card4;

        }

        if(user[4] != null){
            int card5 = Integer.parseInt( user[4].suit.substring(1, user[4].suit.length())  ) ;

            if(card5 == 10 || card5 == 11 || card5 == 12 || card5 == 13){
                card5 = 10;
            }

            int_handTotal = int_handTotal + card5;

        }


        return int_handTotal;

    }


    public void winbustCheck(int int_handTotal){

        if(int_handTotal == 21){
            System.out.println("Winnnneer");
        }else if(int_handTotal > 21){
            System.out.println("Bustt");
            b.setEnabled(false);
            b2.setEnabled(false);
        }
    }

    public String compareHands(int playerHand, int dealerHand){
        String winner = "";

        if(playerHand > dealerHand){
            winner = "player";
        }
        else if(dealerHand > playerHand && dealerHand <= 21){
            winner = "dealer";
        }
        else if(playerHand == dealerHand){
           winner = "tie";
        }else{
            winner = "player";
        }

        return winner;
    }

}

