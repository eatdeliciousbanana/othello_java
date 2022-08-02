import java.util.Random;

public class RandomAI extends Player {
    public Position place(Othello game, int depth) {
        Position pos[] = game.putPosition();
        int pos_length = 0;
        while (pos[pos_length] != null) {
            pos_length++;
        }
        Random rand = new Random();
        return pos[rand.nextInt(pos_length)];
    }
}
