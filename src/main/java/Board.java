import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private PrintStream printStream;
    private List<String> locations;
    private List<String> originalLocations;

    public Board(PrintStream printStream, List<String> locations) {
        this.printStream = printStream;
        this.locations = locations;
        this.originalLocations = new ArrayList<String>(locations);
    }

    public void draw() {
        String boardString = String.format(
                "%s|%s|%s\n" +
                "-----\n" +
                "%s|%s|%s\n" +
                "-----\n" +
                "%s|%s|%s", locations.toArray()
        );

        printStream.println(boardString);
    }

    public void mark(int location, String symbol) {
        locations.set(location - 1, symbol);
    }

    public boolean isLocationAvailable(int location) {
        return locations.get(location - 1).equals(originalLocations.get(location - 1));
    }
}
