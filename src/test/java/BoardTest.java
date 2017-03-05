import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardTest {

    private PrintStream printStream;
    private Board board;
    private List<String> locations;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        locations = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        board = new Board(printStream, locations);
    }

    @Test
    public void shouldDrawBoardWhenStarting() {
        locations = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        board = new Board(printStream, locations);
        board.draw();

        verify(printStream).println(
                "1|2|3\n" +
                "-----\n" +
                "4|5|6\n" +
                "-----\n" +
                "7|8|9");
    }

    @Test
    public void shouldDrawMarkedBoardWhenLocationsHaveAllBeenMarked() {
        locations = asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
        board = new Board(printStream, locations);

        board.draw();

        verify(printStream).println(
                "a|b|c\n" +
                "-----\n" +
                "d|e|f\n" +
                "-----\n" +
                "g|h|i");
    }

    @Test
    public void shouldMarkBoardInTopLeftCornerWhenPlayerChoosesToMoveInCellOne() throws IOException {
        board.mark(1, "");

        assertThat(locations.get(0), is(not("1")));
    }

    @Test
    public void shouldMarkBoardInTopRightCornerWhenPlayerChoosesToMoveInCellThree() throws IOException {
        board.mark(3, "");

        assertThat(locations.get(2), is(not("3")));
    }

    @Test
    public void shouldChangeLocationToLetterPWhenMarkedWithALetterP() {
        board.mark(1, "P");

        assertThat(locations.contains("P"), is(true));
    }

    @Test
    public void shouldChangeLocationToDifferntLetterWhenMarkedWithADifferertLetter() {
        board.mark(1, "Q");

        assertThat(locations.contains("Q"), is(true));
    }

    @Test
    public void locationShouldBeAvailableWhenUnMarked() {
        assertThat(board.isLocationAvailable(5), is(true));
    }

    @Test
    public void locationShouldBeUnAvailableWhenMarked() {
        locations.set(4,"anything but original symbol");
        assertThat(board.isLocationAvailable(5), is(false));
    }

}