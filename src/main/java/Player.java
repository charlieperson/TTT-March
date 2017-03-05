import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Player {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Board board;
    private String name;
    private String symbol;

    public Player(PrintStream printStream, BufferedReader bufferedReader, Board board, String name, String symbol) {
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.board = board;
        this.name = name;
        this.symbol = symbol;
    }

    public void move() {
        printStream.println("Player " + name + ", enter a number between 1 and 9");

        int location = Integer.parseInt(readLine());
        while (!board.isLocationAvailable(location)){
            printStream.println("Location already taken");
            location = Integer.parseInt(readLine());
        }
        board.mark(location, symbol);
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}