package othello_package;

import java.util.Random;

public class MaxAI extends Player {
    public Position place(Othello game, int depth) {
        Othello subgame = new Othello();
        Position pos[] = game.putPosition();
        int pos_length = 0;
        while (pos[pos_length] != null) {
            pos_length++;
        }
        int maxStoneCount = 0;
        Position maxPos[] = new Position[pos_length];
        int maxPos_length = 0;
        for (int i = 0; i < pos_length; i++) {
            subgame.load(game);
            subgame.put(pos[i]);
            kernel(subgame, depth);
            int stoneCount = subgame.countStone(game.getTurn());
            if (stoneCount > maxStoneCount) {
                maxStoneCount = stoneCount;
                maxPos[0] = pos[i];
                maxPos_length = 1;
            } else if (stoneCount == maxStoneCount) {
                maxPos[maxPos_length++] = pos[i];
            }
        }
        Random rand = new Random();
        return maxPos[rand.nextInt(maxPos_length)];
    }

    private void kernel(Othello subgame, int depth) {
        if (depth > 1 && !subgame.getEnd()) {
            subgame.put(place(subgame, depth - 1));
            kernel(subgame, depth - 1);
        }
    }
}
