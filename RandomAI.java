import java.util.Random;

public class RandomAI extends OthelloAI {
    public Position compute(Othello game, int n) {
        Position pos[] = game.putPosition();
        int count = 0;
        while (pos[count] != null) {
            count++;
        }
        Random rand = new Random();
        return pos[rand.nextInt(count)];
    }
}
