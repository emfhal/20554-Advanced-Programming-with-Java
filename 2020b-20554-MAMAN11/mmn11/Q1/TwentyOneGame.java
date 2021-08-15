/* TwentyOneGame.java - The main goal of the game is to accumulate cards that are as close as possible to 21 without
 *   going through that amount. When the user plays against the "dealer"/computer .
 *
 * Written 04/2020 Emmanuel Fhal for MMN11-Q1.
 */

import java.util.*;
import javax.swing.*;

/** The game starts as follows:
 *  - Mixing the cards and divide the cards into 2 card for each player.
 *  - Each player in turn asks for cards added , the card request done one by one
 *  so the player can decide if he wants to ask for another card or stop.
 *  - After each player completes the card add-in phase, the cards in each hand are displayed to
 *  the other player and announce the winner.
 *  - The player whose card amount is closer to 21 but does not passed this amount is the winner.
 *  The "dealer"/computer will always hit on 16 and "STOP" on 17.
 */

class TwentyOneGame
{
    private static final String title = "Wellcome to Twenty One Card Game";
    private static final int STOP = 17;

    private static Deck deck = new Deck();

    public static void main ( String [] args )
    {
        int game = 0; // The return code from the showConfirmDialog
        do
        {  playGame();
            game = JOptionPane.showConfirmDialog( null, "Play Again?",
                    title, JOptionPane.YES_NO_OPTION );
        } while ( game == JOptionPane.YES_OPTION ); //Defined as a dialog with the user who approved the deal for a new game or stop it now
    }

    private static void playGame ()
    {
        int game = 0;  // The return code from the showConfirmDialog
        Hand dealersHand = new Hand( deck ),
                playersHand = new Hand( deck );

        StringBuilder initMsg = new StringBuilder( "Dealer shows : " ); //show dealer cards
        initMsg.append( dealersHand.firstCard() );
        initMsg.append( "\n\nYour Hand: " );

        while ( playersHand.value() < 21 ) //Continue as long as the total value of the user's cards is less than 21
        {
            StringBuilder msg = new StringBuilder( initMsg );
            msg.append( playersHand.toString() );
            msg.append( "\n\nAnother Card?" );

            game = JOptionPane.showConfirmDialog( null, msg, title,
                    JOptionPane.YES_NO_CANCEL_OPTION );

            if ( game != JOptionPane.YES_OPTION )
                break; //If the user does not decide to continue
            playersHand.hit();
        }

        if ( game == JOptionPane.CANCEL_OPTION )
            return;

        // Determine who won, and display appropriate message:
        String msg = null;

        if ( playersHand.value() > 21 )
        {
            msg = "Sorry, you lost!\n\n"; //Display a message if the cards screen has passed 21
        } else
        {
            while ( dealersHand.value() < STOP )
                dealersHand.hit(); // According to the rule we set if the value of the dealer's cards is less than the value we set then continue playing on the dealer's side

            if ( dealersHand.value() > 21 ||
                    playersHand.value() > dealersHand.value() )
                msg = "You won!\n\n"; //Define instances where the player wins (the dealer have more than 21 or the total value of card of the player higher than the dealer)
            else
                msg = "Sorry, you lost!\n\n";//Define instances where the player not win
        }

        msg += "Dealers Hand: " + dealersHand.toString() + "\n\n" +
                "Players Hand: " + playersHand.toString();

        JOptionPane.showMessageDialog( null, msg, title,
                JOptionPane.PLAIN_MESSAGE );
    }
}



/** Here we will represent and define all of the card game options (in terms of rank and type)
 */
class Card
{
    final int type;
    final int rank;

    Card ( int type, int rank )
    {
        if ( type < 1 || type > 4 )
            throw new IllegalArgumentException(
                    "type must be between 1 (clubs) and 4 (spades)." );

        if ( rank < 1 || rank > 13 )
            throw new IllegalArgumentException(
                    "rank must be between 1 (ace) and 13 (king)" );

        this.type = type;
        this.rank = rank;
    }

    /** Calculates the value of the card.
     *  @return the rank of the card (Ace = 1, J/Q/K = 10).
     */
    public int value ()
    {
        int val = rank;
        if ( rank > 10 )
            val = 10;
        return val;
    }

    /** Uses ASCII to show the rank (A,2,3,4,5,6,7,8,9,10,J,Q,K) and type (Club,Diamond,Heart,Spade).
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder( 2 );
        switch ( rank )
        {
            case  1: sb.append( 'A' );  break;
            case 11: sb.append( 'J' );  break;
            case 12: sb.append( 'Q' );  break;
            case 13: sb.append( 'K' );  break;
            default: sb.append( rank ); break;
        }
        switch ( type )
        {
            case 1: sb.append( '\u2663' );  break;  // Club
            case 2: sb.append( '\u2666' );  break;  // Diamond
            case 3: sb.append( '\u2665' );  break;  // Heart
            case 4: sb.append( '\u2660' );  break;  // Spade
        }
        return sb.toString();
    }
}


/** In this class we will set all options for the deck of cards when we set the number of cards deck to 4.
 * There are 4 types of cards and 13 options for a value and total of 52 standart cards in a deck.
 */

class Deck
{
    private static final int NUM_DECKS = 4;

    private ArrayList<Card> deck;

    public Deck ()
    {   shuffleCards();
    }

    public ArrayList<Card> drawCards ( int numCardsToDeal )
    {
        ArrayList<Card> cards = new ArrayList<Card>( numCardsToDeal );

        for ( int i = 0; i < numCardsToDeal; ++i )
        {
            shuffleCards();
            cards.add( deck.remove(0) );
        }
        return cards;
    }

    private void shuffleCards ()
    {
        deck = new ArrayList<Card>( NUM_DECKS * 52 );

        for ( int numDecks = 0; numDecks < NUM_DECKS; ++numDecks )
            for ( int type = 1; type <= 4; ++type )
                for ( int rank = 1; rank <= 13; ++rank )
                    deck.add( new Card( type, rank ) );

        Collections.shuffle( deck );
    }
}

/** The game management is managed by this "Hand" class of cards held.
 */

class Hand
{
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Deck deck;

    public Hand ( Deck deck )
    {
        this.deck = deck;
        // 2 cards to initialize the hand
        cards.addAll( deck.drawCards( 2 ) );
    }

    /** Calculates the value of the hand.  Q,K,J are worth 10 and
     *  Ace is worth 1 or 11.
     */
    public int value ()
    {
        int total = 0;
        boolean hasAce = false;
        for ( Card card : cards )
        {
            int val = card.value();
            if  ( val == 1 )  hasAce = true;
            total += val;
        }

        while ( total < (21 - 10) && hasAce )
            total += 10;
        return total;
    }

    /** Add one card to the hand from the Deck.
     */
    public void hit () {  cards.addAll( deck.drawCards(1) ); }

    /** During the game only the first card of the dealer is displayed according to the rules
     */
    public Card firstCard() { return cards.get( 0 );  }

    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for ( Card card : cards )
        {
            sb.append( card );
            sb.append( "   " ); //space
        }
        return sb.toString();
    }
}