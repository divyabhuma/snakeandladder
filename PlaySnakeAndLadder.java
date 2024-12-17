package SystemDesign.SnakeAndLadder;

import java.util.*;

public class PlaySnakeAndLadder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        Queue<Player> allPlayers = new LinkedList<>();
        Map<String, Integer> playersCurrentPosition = new HashMap<>();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name of player " + i + ": ");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName, i);
            allPlayers.offer(player);
            playersCurrentPosition.put(playerName, 0);
        }

        System.out.print("Enter the number of snakes: ");
        int numSnakes = scanner.nextInt();

        List<Jumper> snakes = new ArrayList<>();
        for (int i = 0; i < numSnakes; i++) {
            System.out.print("Enter start and end points of snake " + (i + 1) + " (e.g., 14 7): ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            snakes.add(new Jumper(start, end));
        }

        System.out.print("Enter the number of ladders: ");
        int numLadders = scanner.nextInt();

        List<Jumper> ladders = new ArrayList<>();
        for (int i = 0; i < numLadders; i++) {
            System.out.print("Enter start and end points of ladder " + (i + 1) + " (e.g., 3 22): ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            ladders.add(new Jumper(start, end));
        }

        Dice dice = new Dice(1);

        GameBoard gb = new GameBoard(dice, allPlayers, snakes, ladders, playersCurrentPosition, 100);

        gb.startGame();

        scanner.close();
    }
}
