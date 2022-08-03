import java.util.Scanner;
import java.util.InputMismatchException;

public class Human extends Player {
    public Position place(Othello game, int depth) {
        Scanner scanner = new Scanner(System.in);
        boolean putcheck[][] = game.getPutcheck();
        int x = 0, y = 0;
        while (true) {
            while (true) {
                try {
                    System.out.print("行: ");
                    y = scanner.nextInt();
                    if (y >= 1 && y <= 8) {
                        break;
                    } else {
                        System.out.println("1~8の整数を入力してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("1~8の整数を入力してください");
                    scanner.next();
                }
            }
            while (true) {
                try {
                    System.out.print("列: ");
                    x = scanner.nextInt();
                    if (x >= 1 && x <= 8) {
                        break;
                    } else {
                        System.out.println("1~8の整数を入力してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("1~8の整数を入力してください");
                    scanner.next();
                }
            }
            if (putcheck[y][x]) {
                break;
            } else {
                System.out.println("その場所には置けません");
            }
        }
        return new Position(y, x);
    }
}
