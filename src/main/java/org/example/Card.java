package org.example;

public class Card
{
    private String face; // face of card ("Ace", "Deuce", ...)
    private String suit; // suit of card ("Hearts", "Diamonds", ...)
    private int number;

    public Card( String cardFace, String cardSuit, int cardNumber )
    {
        face = cardFace; // initialize face of card
        suit = cardSuit; // initialize suit of card
        number = cardNumber;
    }
    public String toString()
    {
        return face + " of " + suit;
    } // end method toString

    public int getNumber() {
        return number;
    }

}

