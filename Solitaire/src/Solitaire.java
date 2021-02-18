import java.util.*;
/**
 * A class that allows a user to play a game of Klondike
 * Solitaire.
 * 
 * @author Max Lee 
 * @version 11-5-18
 */
public class Solitaire
{
    /**
     * Creates a new Solitaire game.
     * @param args arguments for the main
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;
    
    /**
     * Creates an instance of the Solitaire class.
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        for (int i = 0; i < 4; i++)
        {
            foundations[i] = new Stack<Card>();
        }
        piles = new Stack[7];
        for (int i = 0; i < 7; i++)
        {
            piles[i] = new Stack<Card>();
        }
        stock = new Stack<Card>();
        waste = new Stack<Card>();
        createStock();
        display = new SolitaireDisplay(this);
        deal();
    }
    
    /**
     * Gets the card on top of the waste pile. 
     * @return the card on top of the waste, null if the waste is empty
     */
    public Card getWasteCard()
    {
        if (waste.isEmpty())
        {
            return null;
        }
        else
        {
            return waste.peek();
        }
    }

    /**
     * Gets the card on top of the stock.
     * @return the card on top of the stock, null if the stock is empty
     */
    public Card getStockCard()
    {
        if (stock.isEmpty())
        {
            return null;
        }
        else
        {
            return stock.peek();
        }
    }

    /**
     * Gets the card on top of a foundation
     * @param index the index of the foundation
     * @precondition 0 <= index < 4
     * @return the card on top of the given
     *         foundation, or null if the foundation
     *         is empty
     */
    public Card getFoundationCard(int index)
    {
        if (foundations[index].isEmpty())
        {
            return null;
        }
        else
        {
            return foundations[index].peek();
        }
    }

    /**
     * Creates a shuffled 52-card deck.
     * @postcondition a 52-card deck is created and randomized.
     */
    private void createStock()
    {
        ArrayList<Card>deck = new ArrayList<Card>();
        for (int i = 0; i < 13; i++)
        {
            deck.add(new Card(i+1, "c"));
        }
        for (int i = 0; i < 13; i++)
        {
            deck.add(new Card (i+1, "d"));
        }
        for (int i = 0; i < 13; i++)
        {
            deck.add(new Card (i+1, "h"));
        }
        for (int i = 0; i < 13; i++)
        {
            deck.add(new Card (i+1, "s"));
        }
        for (int i = 0; i < 52; i++)
        {
            stock.push(deck.remove((int)(deck.size() * Math.random())));
        }
    }
    
    /**
     * Deals the deck so as to set up for solitaire.
     * @postcondition the cards are in solitaire setup
     */
    private void deal()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                piles[i].push(stock.pop());
                
            }
            piles[i].peek().turnUp();
        }
    }
     
    /**
     * Deals top three cards on stock to waste, turning them up
     * @postcondition top three (or however many are left if < 3)
     *                  are dealt from stock to the top of waste, 
     *                  cards turned up
     */
    private void dealThreeCards()
    {
        for (int i = 0; i < 3; i++)
        {
            if (!stock.isEmpty())
            {
                waste.push(stock.pop());
                waste.peek().turnUp();
            }
        }
    }
    
    /**
     * Resets the stock.
     * @postcondition All cards on waste are turned face down
     *                  and moved to stock.
     */
    private void resetStock()
    {
        while (!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }
    
    /**
     * True if card can be added to pile
     * @param index index of the pile being considered
     * @param card card being considered
     * @return true if card can be moved to pile at index,
     *          false otherwise.
     */
    private boolean canAddToPile(Card card, int index)
    {
        if (!piles[index].isEmpty())
        {
            if (!piles[index].peek().isFaceUp())
            {
                return false;
            }
        }
        if (piles[index].isEmpty())
        {
            return card.getRank() == 13;
        }
        if (piles[index].peek().isFaceUp())
        {
            return card.getRank() == piles[index].peek().getRank() - 1 &&
                    card.isRed() != piles[index].peek().isRed();
        }
        return false;
    }
    
    /**
     * Helper method, removes face up cards from pile.
     * @param index index of pile
     * @return stack of face up cards from pile at index.
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card>cards = new Stack<Card>();
        while(!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            cards.push(piles[index].pop());
        }
        return cards;
    }
    
    /**
     * Removes from cards, adds to pile
     * @param cards stack of cards to be removed
     * @param index index of pile
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.isEmpty())
        {
            piles[index].push(cards.pop());
        }
    }
    
    /**
     * True if card can be moved onto foundation at index.
     * @param card the card being checked
     * @param index index of foundation being checked
     * @return true if card can be moved to index, false otherwise.
     */
    private boolean canAddToFoundation (Card card, int index)
    {
        if (foundations[index].isEmpty())
        {
            return card.getRank() == 1;
        }
        else
        {
            return foundations[index].peek().getSuit().equals(card.getSuit())
                    && foundations[index].peek().getRank() + 1 == card.getRank();
        }
    }
    
    /**
     * Gets a pile.
     * @param index index of pile
     * @return reference to pile
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * Called by the display when the stock is clicked
     * @postcondition    check if the waste or a pile is not selected. 
     *                   Then, if cards are left in the stock, put 3 cards
     *                   onto waste. Otherwise, reset the stock.
     */
    public void stockClicked()
    {
        if (!display.isWasteSelected() && !display.isPileSelected())
        {
            if (!stock.isEmpty())
            {
                this.dealThreeCards();
            }
            else
            {
                this.resetStock();
            }
        }
    }
    /**
     * Called by the display when the waste is clicked
     * @postcondition    check if the waste is selected. if so, unselect it. 
     *                   Otherwise, check if nothing else is selected and if
     *                   waste is empty. If so, select the waste.
     */
    public void wasteClicked()
    {
        if (!waste.isEmpty() && !display.isWasteSelected() && !display.isPileSelected())
        {
            display.selectWaste();
        }
        else if (display.isWasteSelected())
        {
            display.unselect();
        }
    }

    /**
     * Called by the display when the waste is clicked
     * @param index index of foundation
     * @postcondition    If a selected card can be added to
     *                   the foundation at index, add it.
     */
    public void foundationClicked(int index)
    {
        if (display.isPileSelected())
        {
            if (canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
                display.unselect();
                if (foundations[index].peek().getRank() == 13)
                    for (int i = 0; i < 4; i++)
                    {
                        if (foundations[index].peek().getRank() != 13)
                        {
                            return;
                        }
                    }
                    System.out.println("YOU WIN!");
            }
        }
        else if (display.isWasteSelected())
        {
            if (canAddToFoundation(waste.peek(), index))
            {
                foundations[index].push(waste.pop());
            }
        }
        
    }

    /**
     * Called by the display when a pile is clicked
     * @param    index the index of pile
     * @postcondition    check if the waste is selected. 
     *                   If so, move the top card of waste on top
     *                   of the pile if the move is legal. If nothing
     *                   is selected, select the pile or flip a card. If 
     *                   just the pile is selected, unselect the pile. 
     *                   If another pile is selected as well, then move 
     *                   the cards from the previous pile to the current 
     *                   pile if legal.
     */
    public void pileClicked(int index)
    {
        if (display.isWasteSelected())
        {
            if (canAddToPile(waste.peek(), index))
            {
                piles[index].push(waste.pop());
            }
            display.unselect();
        }
        else if (!display.isWasteSelected() && !display.isPileSelected())
        {
            if (!piles[index].isEmpty())
            {
                if (piles[index].peek().isFaceUp())
                {
                    display.selectPile(index);
                }
                else
                {
                    piles[index].peek().turnUp();
                }
            }
        }
        else if (display.isPileSelected())
        {
            if (display.selectedPile() == index)
            {
                display.unselect();
            }
            else
            {
                Stack<Card>temps = new Stack<Card>();
                temps = removeFaceUpCards(display.selectedPile());
                if (!temps.isEmpty() && canAddToPile(temps.peek(), index))
                {
                    addToPile(temps, index);
                    display.unselect();
                }
                else
                {
                    addToPile(temps, display.selectedPile());
                }
            }
        }
    }
}