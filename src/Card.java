import java.util.Stack;

public class Card implements Comparable<Card> {
    private char suit;
    private char value;
    private final static String validSuit = "dchs";
    private final static String validValue = "A23456789XJQK";

    public Card(String cardValue) throws Exception {
        setCard(cardValue);
    }

    //static method
    public static Stack<Card> geneCards() throws Exception {
        Stack<Card> res = new Stack<>();
        char[] suits = validSuit.toCharArray();
        char[] values = validValue.toCharArray();

        for(char value : values)
            for(char suit : suits)
                res.push(new Card(""+suit+value));

        return res;
    }

    //setter
    public void setCard(String cardValue) throws Exception {
        if (cardValue.length() != 2)
            throw new Exception("Invalid card format");
        setSuit(cardValue.charAt(0));
        setValue(cardValue.charAt(1));
    }

    private void setSuit(char suit) throws Exception {
        suit = Character.toLowerCase(suit);
        if (!validSuit.contains("" + suit))
            throw new Exception("Invalid suit format");
        this.suit = suit;
    }

    private void setValue(char value) throws Exception {
        value = Character.toUpperCase(value);
        if (!validValue.contains("" + value))
            throw new Exception("Invalid value format");
        this.value = value;
    }

    //getter
    public char getSuit() {
        return this.suit;
    }

    public int getWeight() {
        return Math.min(validValue.indexOf(this.value) + 1, 10);
    }

    public int getSuitWeight() {
        return validSuit.indexOf(this.suit);
    }

    public String getCard() {
        return "" + this.suit + this.value;
    }

    //comparable
    public int compareTo(Card c) {
        return this.getWeight() - c.getWeight();
    }

    //toString
    @Override
    public String toString() {
        return this.getCard();
    }

}
