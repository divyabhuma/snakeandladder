package SystemDesign.SnakeAndLadder;

class Player {
    private String playerName;
    private int id;

    Player(String playerName, int id) {
        this.playerName = playerName;
        this.id = id;
    }

    String getPlayerName() {
        return playerName;
    }

    int getId() {
        return id;
    }
}
