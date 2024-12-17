package SystemDesign.SnakeAndLadder;

import java.util.*;

class GameBoard {
    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<String, Integer> playersCurrentPosition;
    private int boardSize;

    GameBoard(Dice dice, Queue<Player> nextTurn, List<Jumper> snakes, List<Jumper> ladders, Map<String, Integer> playersCurrentPosition, int boardSize) {
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = playersCurrentPosition;
        this.boardSize = boardSize;
    }

    void startGame() {
        while (nextTurn.size() > 1) {
            Player player = nextTurn.poll();
            int currentPosition = playersCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice();
            int nextCell = currentPosition + diceValue;
            if (nextCell > boardSize) {
                nextTurn.offer(player);
            } else if (nextCell == boardSize) {
                System.out.println(player.getPlayerName() + " won the game");
            } else {
                int[] nextPosition = new int[1];
                nextPosition[0] = nextCell;
                boolean gotLadder = false;

                for (Jumper snake : snakes) {
                    if (snake.startPoint == nextCell) {
                        nextPosition[0] = snake.endPoint;
                        System.out.println(player.getPlayerName() + " bitten by a snake at position " + nextCell);
                        break;
                    }
                }

                for (Jumper ladder : ladders) {
                    if (ladder.startPoint == nextCell) {
                        nextPosition[0] = ladder.endPoint;
                        gotLadder = true;
                        System.out.println(player.getPlayerName() + " climbed a ladder at position " + nextCell);
                        break;
                    }
                }
                if (nextPosition[0] == boardSize) {
                    System.out.println(player.getPlayerName() + " won the game");
                } else {
                    playersCurrentPosition.put(player.getPlayerName(), nextPosition[0]);
                    System.out.println(player.getPlayerName() + " is at position " + nextPosition[0]);
                    nextTurn.offer(player);
                }
            }
        }
    }
}
