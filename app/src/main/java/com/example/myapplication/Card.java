package com.example.myapplication;

public class Card {
    public static final int DIAMOND = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int SPADE = 3;
    String suit = "";
    public Card(int s, int r)
    {

        switch(s)
        {
            case DIAMOND:
                suit = "d" + r;
                break;
            case HEARTS:
                suit = "h" + r;
                break;
            case CLUBS:
                suit = "c" + r;
                break;
            case SPADE:
                suit = "s" + r;
                break;
        }

    }
}
