import java.util.Random;

public class CornerAI extends Player {
    private final int weight[][] = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 30, -12, 0, -1, -1, 0, -12, 30, 0 },
            { 0, -12, -15, -3, -3, -3, -3, -15, -12, 0 },
            { 0, 0, -3, 0, -1, -1, 0, -3, 0, 0 },
            { 0, -1, -3, -1, -1, -1, -1, -3, -1, 0 },
            { 0, -1, -3, -1, -1, -1, -1, -3, -1, 0 },
            { 0, 0, -3, 0, -1, -1, 0, -3, 0, 0 },
            { 0, -12, -15, -3, -3, -3, -3, -15, -12, 0 },
            { 0, 30, -12, 0, -1, -1, 0, -12, 30, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public Position place(Othello game, int depth) {
        Othello subgame = new Othello();
        Position pos[] = game.putPosition();
        int pos_length = 0;
        while (pos[pos_length] != null) {
            pos_length++;
        }
        int turn = game.getTurn();
        int maxEval = -1000;
        Position bestPos[] = new Position[pos_length];
        int bestPos_length = 0;
        for (int i = 0; i < pos_length; i++) {
            subgame.load(game);
            subgame.put(pos[i]);
            kernel(subgame, depth);
            int board[][] = subgame.getBoard();
            int eval = 0;
            for (int j = 1; j <= 8; j++) {
                for (int k = 1; k <= 8; k++) {
                    if (board[j][k] == turn) {
                        eval += weight[j][k];
                    } else if (board[j][k] == -turn) {
                        eval -= weight[j][k];
                    }
                }
            }
            if (eval > maxEval) {
                maxEval = eval;
                bestPos[0] = pos[i];
                bestPos_length = 1;
            } else if (eval == maxEval) {
                bestPos[bestPos_length++] = pos[i];
            }
        }
        Random rand = new Random();
        return bestPos[rand.nextInt(bestPos_length)];
    }

    private void kernel(Othello subgame, int depth) {
        if (depth > 1 && !subgame.getEnd()) {
            subgame.put(place(subgame, depth - 1));
            kernel(subgame, depth - 1);
        }
    }
}
