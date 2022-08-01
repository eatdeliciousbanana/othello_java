import java.io.IOException;

public class Main {
    public static void main(String... arg) throws IOException, InterruptedException {
        Othello game = new Othello();
        while (!game.getEnd()) {
            game.display();
            game.put(game.putPosition()[0]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        game.display();

    }
}