import java.util.*;

public class Game {
    private Stack<Card> cards;
    public Stack<Player> players;

    public Game(ArrayList<String> playersName) throws Exception {
        players = new Stack<Player>();
        for (String player : playersName)
            players.push(new Player(player));
    }

    public void dealCards() throws Exception {
        this.cards = Card.geneCards();
        Collections.shuffle(this.cards);

        //temporary store cards for players
        Stack<Card>[] playersCards =  new Stack[players.size()];
        for (int i = 0; i < players.size(); i++)
            playersCards[i] = new Stack<Card>();

        //deal cards
        for(int i = 0; !cards.isEmpty(); i++)
            playersCards[i % players.size()].push(cards.pop());

        //player divide its own cards
        for(int i = 0; i < players.size(); i++)
            players.get(i).setCards(playersCards[i]);
    }

    public void removeWeakest() {
        players.remove(Collections.min(players));
    }

    public void nextPhase() {
        System.out.print("**** ");
        for (Player p : this.players)
            System.out.print(p.getName() + " and");
        System.out.print("proceed to 2-Player phase");
        System.out.println("****");
    }

    public void pollCards() {
        for (Player p : this.players)
            p.pollCards();
    }

}
