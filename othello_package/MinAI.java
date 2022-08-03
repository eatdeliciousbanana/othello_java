package othello_package;

import java.util.Random;

public class MinAI extends Player {
    public Position place(Othello game, int depth) {
        Othello subgame = new Othello();
        Position pos[] = game.putPosition();
        int pos_length = 0;
        while (pos[pos_length] != null) {
            pos_length++;
        }
        int minStoneCount = 65;
        Position minPos[] = new Position[pos_length];
        int minPos_length = 0;
        for (int i = 0; i < pos_length; i++) {
            subgame.load(game);
            subgame.put(pos[i]);
            kernel(subgame, depth);
            int stoneCount = subgame.countStone(game.getTurn());
            if (stoneCount < minStoneCount) {
                minStoneCount = stoneCount;
                minPos[0] = pos[i];
                minPos_length = 1;
            } else if (stoneCount == minStoneCount) {
                minPos[minPos_length++] = pos[i];
            }
        }
        Random rand = new Random();
        return minPos[rand.nextInt(minPos_length)];
    }

    private void kernel(Othello subgame, int depth) {
        if (depth > 1 && !subgame.getEnd()) {
            subgame.put(place(subgame, depth - 1));
            kernel(subgame, depth - 1);
        }
    }
}
