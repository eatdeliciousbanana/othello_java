public class Othello {
    // マスの状態を表す定数
    private final int WHITE = 1;
    private final int BLACK = -1;
    private final int NONE = 0;
    private final int BORDER = 2;

    // メンバ変数
    private int board[][]; // オセロ盤
    private boolean putcheck[][]; // 石を置ける場所
    private int turn; // 白または黒のターン
    private int turnNum; // ターン数のカウント(1~61ターン)
    private boolean end; // 終了しているかどうか

    // コンストラクタ
    Othello() {
        this.board = new int[10][];
        for (int i = 0; i < 10; i++) {
            this.board[i] = new int[10];
        }
        for (int i = 0; i < 10; i++) {
            this.board[0][i] = BORDER;
            this.board[i][0] = BORDER;
            this.board[i][9] = BORDER;
            this.board[9][i] = BORDER;
        }
        this.board[4][4] = WHITE;
        this.board[4][5] = BLACK;
        this.board[5][4] = BLACK;
        this.board[5][5] = WHITE;
        this.putcheck = new boolean[10][];
        for (int i = 0; i < 10; i++) {
            this.putcheck[i] = new boolean[10];
        }
        this.turn = BLACK;
        this.turnNum = 1;
        this.end = false;
        this.checkBoard();
    }

    // boardを返す
    public int[][] getBoard() {
        return this.board;
    }

    // putcheckを返す
    public boolean[][] getPutcheck() {
        return this.putcheck;
    }

    // turnを返す
    public int getTurn() {
        return this.turn;
    }

    // turnNumを返す
    public int getTurnNum() {
        return this.turnNum;
    }

    // endを返す
    public boolean getEnd() {
        return this.end;
    }

    // 置ける場所を探す
    private boolean checkBoard() {
        boolean check = false;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.putcheck[i][j] = false;
                if (this.board[i][j] != NONE) {
                    continue;
                }
                int direction[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                        { 1, 1 } };
                loop: for (int k = 0; k < 8; k++) {
                    int m = direction[k][0];
                    int n = direction[k][1];
                    while (this.board[i + m][j + n] == -this.turn) {
                        m += direction[k][0];
                        n += direction[k][1];
                        if (this.board[i + m][j + n] == this.turn) {
                            this.putcheck[i][j] = true;
                            check = true;
                            break loop;
                        }
                    }
                }
            }
        }
        return check;
    }

    // 置ける場所を列挙する
    public Position[] putPosition() {
        Position ret[] = new Position[60];
        int count = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (this.putcheck[i][j]) {
                    ret[count++] = new Position(i, j);
                }
            }
        }
        return ret;
    }

    // 石を置いて挟んだ石を裏返す
    public boolean put(Position pos) {
        int y = pos.getY();
        int x = pos.getX();
        if (!this.putcheck[y][x]) {
            return false;
        }
        this.board[y][x] = this.turn;
        int direction[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                { 1, 1 } };
        for (int i = 0; i < 8; i++) {
            Position reverse_pos[] = new Position[18];
            int count = 0;
            boolean flag = false;
            int m = direction[i][0];
            int n = direction[i][1];
            while (this.board[y + m][x + n] == -this.turn) {
                reverse_pos[count++] = new Position(y + m, x + n);
                m += direction[i][0];
                n += direction[i][1];
                if (this.board[y + m][x + n] == this.turn) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                for (int j = 0; j < count; j++) {
                    int reverse_y = reverse_pos[j].getY();
                    int reverse_x = reverse_pos[j].getX();
                    this.board[reverse_y][reverse_x] = this.turn;
                }
            }
        }
        this.update();
        return true;
    }

    // 次のターンに進める
    private void update() {
        this.turn = -this.turn;
        this.turnNum++;
        if (!this.checkBoard()) {
            this.turn = -this.turn;
            if (!this.checkBoard()) {
                this.terminate();
            }
        }
    }

    // 終了する
    private void terminate() {
        this.end = true;
    }

    // リセットする
    public void reset() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.board[i][j] = NONE;
            }
        }
        this.board[4][4] = WHITE;
        this.board[4][5] = BLACK;
        this.board[5][4] = BLACK;
        this.board[5][5] = WHITE;
        this.turn = BLACK;
        this.turnNum = 1;
        this.end = false;
        this.checkBoard();
    }

    // 石の数を数える
    public int countStone(int color) {
        int count = 0;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (this.board[i][j] == color) {
                    count++;
                }
            }
        }
        return count;
    }

    // 他のOthelloクラスインスタンスをロードする
    public void load(Othello game) {
        int board[][] = game.getBoard();
        boolean putcheck[][] = game.getPutcheck();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                this.board[i][j] = board[i][j];
                this.putcheck[i][j] = putcheck[i][j];
            }
        }
        this.turn = game.getTurn();
        this.turnNum = game.getTurnNum();
        this.end = game.getEnd();
    }

    // オセロを画面に表示する
    public void display() {
        System.out.println(this.turnNum + "手目");
        System.out.println(this.turn == BLACK ? "黒のターン" : "白のターン");
        System.out.println("　１２３４５６７８");
        for (int i = 1; i <= 8; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= 8; j++) {
                switch (this.board[i][j]) {
                    case BLACK:
                        System.out.print("○");
                        break;
                    case WHITE:
                        System.out.print("●");
                        break;
                    case NONE:
                        System.out.print("・");
                        break;
                    default:
                        break;
                }
            }
            System.out.print("\n");
        }
        if (this.end) {
            int black = this.countStone(BLACK);
            int white = this.countStone(WHITE);
            if (black > white) {
                System.out.println("黒" + black + "個, 白" + white + "個で黒の勝ちです!");
            } else if (black < white) {
                System.out.println("黒" + black + "個, 白" + white + "個で白の勝ちです!");
            } else {
                System.out.println("黒" + black + "個, 白" + white + "個で引き分けです!");
            }
        }
    }
}
