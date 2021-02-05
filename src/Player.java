import java.util.*;

public class Player implements Comparable<Player>{
    private int score;
    private String name;
    private Queue<ArrayList<Card>> cards;

    public Player(String name) {
        setScore(0);
        setName(name);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Queue<ArrayList<Card>> getCards() {
        return cards;
    }

    public ArrayList<Card> pollCards() {
        return cards.poll();
    }

    public String strCardCurPart() {
        ArrayList<Card> temp = cards.peek();
        Collections.sort(temp, new suitComparator());
        return strCardPart(temp);
    }

    public String strCardPart(ArrayList<Card> cardPart) {
        StringBuilder res = new StringBuilder();
        for (Card card : cardPart)
            res.append(" " + card);
        return res.toString();
    }

    public String strCards() {
        StringBuilder res = new StringBuilder();
        for (ArrayList<Card> cardPart : this.cards) {
            res.append(strCardPart(cardPart) + ",");
        }
        res.setLength(res.length() - 1);
        return res.toString();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = new LinkedList<>();
        while(!cards.isEmpty()) {
            ArrayList<Card> tmp = new ArrayList<>();
            for (int i = 0; !cards.isEmpty() && i < 5; i++)
                tmp.add(cards.pop());
            this.cards.add(tmp);
        }
    }

    public int countPoint() {
        ArrayList<Card> temp = cards.peek();
        Collections.sort(temp, new suitComparator());

        int bestSum = temp.get(0).getWeight();
        int currentSum = bestSum;

        for(int i = 1; i < temp.size(); i++) {
            if (temp.get(i-1).getSuit() != temp.get(i).getSuit())
                currentSum = 0;
            else
                currentSum += temp.get(i).getWeight();
            bestSum = Math.max(bestSum, currentSum);
        }

        return bestSum;
    }

    @Override
    public int compareTo(Player p) {
        return this.getScore() - p.getScore();
    }
}

class suitComparator implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        if (c1.getSuitWeight() != c2.getSuitWeight())
            return c1.getSuitWeight() - c2.getSuitWeight();
        else
            return c1.compareTo(c2);
    }
}
