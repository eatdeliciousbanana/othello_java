import java.util.Random;

public class RandomAI extends Player {
    public Position place(Othello game, int n) {
        Position pos[] = game.putPosition();
        int count = 0;
        while (pos[count] != null) {
            count++;
        }
        Random rand = new Random();
        return pos[rand.nextInt(count)];
    }
}
