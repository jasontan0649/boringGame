import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("******************");
        System.out.println(" 3-Player Phase  *");
        System.out.println("******************");
        Game game = new Game(Menu.inputNames());
        System.out.println();


        //Game Begin
        for(int i = 1; i <= 2; i++) {
            game.dealCards();
            Menu.shuffleCard(game);
            for(int j = 1; j <= (2+i); j++) {
                System.out.println("*** ROUND " + j + " ***");
                Menu.cardsAtHand(game);
                System.out.println("\n");
                Menu.showScore(game);

                //poll current stack of card
                game.pollCards();

                System.out.println("\n");
                Menu.availableCard(game);

                if (j == 2+i)
                    break;

                System.out.println("Press ENTER to the next round.");
                System.in.read();
                System.out.println("\n\n");
            }
            game.removeWeakest();
            if (i == 1) {
                game.nextPhase();
                System.out.println("******************");
                System.out.println(" " + (i+1) + "-Player Phase  *");
                System.out.println("******************\n\n");
            }
        }
        //Game End
        Player winner = game.players.pop();
        System.out.println("******" + winner.getName() + " is the WINNER! ****");
    }
}
