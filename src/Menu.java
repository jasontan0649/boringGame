import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {
    public static ArrayList<String> inputNames() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<String>();
        for(int i = 0; i < 3; i++) {
            System.out.print("Enter player " + (i+1) + " name: ");
            names.add(input.nextLine());
        }
        return names;
    }

    public static void shuffleCard(Game game) throws Exception {
        Scanner input = new Scanner(System.in);
        while (true) {
            availableCard(game);
            System.out.println("Press S to Shuffle or ENTER to start");
            String res = input.nextLine();
            if (res.equals("")) {
                System.out.println("<Enter is press>");
                return;
            }
            System.out.println("\n\nShuffle card");
            game.dealCards();
        }
    }

    public static void availableCard(Game game){
        System.out.println("Available Cards:");
        int maxLen  = 0;
        for (Player p : game.players)
            maxLen = Math.max(maxLen, p.getName().length());


        for (Player p : game.players) {
            int spaceLen = maxLen - p.getName().length();
            System.out.print(p.getName() + " ".repeat(spaceLen) + ":");
            System.out.print(p.strCards());
            System.out.println();
        }
    }

    public static void showScore(Game game) {
        System.out.println("Score:");
        int maxLen  = 0;
        for (Player p : game.players)
            maxLen = Math.max(maxLen, p.getName().length());

        for (Player p : game.players) {
            int spaceLen = maxLen - p.getName().length();
            System.out.print(p.getName() + " ".repeat(spaceLen) + "= ");
            System.out.print(p.getScore());
            System.out.println();
        }
    }


    public static void cardsAtHand(Game game) {
        int maxLen  = 0;
        for (Player p : game.players)
            maxLen = Math.max(maxLen, p.getName().length());

        ArrayList<Integer> points = new ArrayList<>();
        for (Player p : game.players) {
            int spaceLen = maxLen - p.getName().length();
            System.out.print(p.getName() + " ".repeat(spaceLen) + ":");
            System.out.print(p.strCardCurPart());
            System.out.print(" | Point = ");
            int point = p.countPoint();
            points.add(point);
            System.out.println(point);
        }

        //Find max
        int maxPointIndex = points.indexOf(Collections.max(points));
        //Add Score
        game.players.get(maxPointIndex).addScore(points.get(maxPointIndex));
    }
}
