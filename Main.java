import othello_package.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("win");
    public static Player first;
    public static Player second;
    public static int first_depth = 0;
    public static int second_depth = 0;
    public static int interval = 0;

    public static void main(String[] args) {
        setPlayer();
        Othello game = new Othello();
        while (!game.getEnd()) {
            clear();
            game.display();
            System.out.println();
            if (game.getTurn() == Othello.BLACK) {
                game.put(first.place(game, first_depth));
            } else {
                game.put(second.place(game, second_depth));
            }
            sleep(interval);
        }
        clear();
        game.display();
    }

    public static void setPlayer() {
        Scanner scanner = new Scanner(System.in);
        int num;
        System.out.println("先攻プレイヤーを選択");
        System.out.println("1: Human");
        System.out.println("2: RandomAI");
        System.out.println("3: MaxAI");
        System.out.println("4: MinAI");
        System.out.println("5: CornerAI");
        while (true) {
            try {
                System.out.print("番号: ");
                num = scanner.nextInt();
                if (num >= 1 && num <= 5) {
                    break;
                } else {
                    System.out.println("1~5の整数を入力してください");
                }
            } catch (InputMismatchException e) {
                System.out.println("1~5の整数を入力してください");
                scanner.next();
            }
        }
        switch (num) {
            case 1:
                first = new Human();
                break;
            case 2:
                first = new RandomAI();
                break;
            case 3:
                first = new MaxAI();
                break;
            case 4:
                first = new MinAI();
                break;
            case 5:
                first = new CornerAI();
                break;
            default:
                break;
        }
        if (num >= 3) {
            while (true) {
                try {
                    System.out.print("AIの先読みの深さ: ");
                    num = scanner.nextInt();
                    if (num >= 1 && num <= 10) {
                        first_depth = num;
                        break;
                    } else {
                        System.out.println("1~10の整数を入力してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("1~10の整数を入力してください");
                    scanner.next();
                }
            }
        }
        System.out.println("\n後攻プレイヤーを選択");
        System.out.println("1: Human");
        System.out.println("2: RandomAI");
        System.out.println("3: MaxAI");
        System.out.println("4: MinAI");
        System.out.println("5: CornerAI");
        while (true) {
            try {
                System.out.print("番号: ");
                num = scanner.nextInt();
                if (num >= 1 && num <= 5) {
                    break;
                } else {
                    System.out.println("1~5の整数を入力してください");
                }
            } catch (InputMismatchException e) {
                System.out.println("1~5の整数を入力してください");
                scanner.next();
            }
        }
        switch (num) {
            case 1:
                second = new Human();
                break;
            case 2:
                second = new RandomAI();
                break;
            case 3:
                second = new MaxAI();
                break;
            case 4:
                second = new MinAI();
                break;
            case 5:
                second = new CornerAI();
                break;
            default:
                break;
        }
        if (num >= 3) {
            while (true) {
                try {
                    System.out.print("AIの先読みの深さ: ");
                    num = scanner.nextInt();
                    if (num >= 1 && num <= 10) {
                        second_depth = num;
                        break;
                    } else {
                        System.out.println("1~10の整数を入力してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("1~10の整数を入力してください");
                    scanner.next();
                }
            }
        }
        System.out.println();
        while (true) {
            try {
                System.out.print("インターバル(ミリ秒): ");
                num = scanner.nextInt();
                if (num >= 0) {
                    interval = num;
                    break;
                } else {
                    System.out.println("0以上の整数を入力してください");
                }
            } catch (InputMismatchException e) {
                System.out.println("0以上の整数を入力してください");
                scanner.next();
            }
        }
    }

    public static void clear() {
        try {
            if (IS_WINDOWS) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("問題が発生しました");
            System.exit(0);
        }
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("問題が発生しました");
            System.exit(0);
        }
    }
}