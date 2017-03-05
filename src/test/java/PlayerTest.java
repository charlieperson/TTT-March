import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PlayerTest {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Board board;
    private Player player;

    @Before
    public void setUp() throws Exception {
        bufferedReader = mock(BufferedReader.class);
        board = mock(Board.class);
        printStream = mock(PrintStream.class);
        player = new Player(printStream, bufferedReader, board, "1", "");
        when(bufferedReader.readLine()).thenReturn("-1");
        when(board.isLocationAvailable(anyInt())).thenReturn(true);
    }

    @Test
    public void shouldPrintPromptWhenMoving() {
        player.move();

        verify(printStream).println("Player 1, enter a number between 1 and 9");
    }

    @Test
    public void shouldMarkBoardInLocationOneWhenPlayerEntersOne() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");

        player.move();

        verify(board).mark(eq(1), anyString());
    }

    @Test
    public void shouldMarkBoardInLocationTwoWhenPlayerEntersTwo() throws IOException {
        when(bufferedReader.readLine()).thenReturn("2");

        player.move();

        verify(board).mark(eq(2), anyString());
    }

    @Test
    public void shouldPrintPromptWithPlayerNameWhenMoving() {
        new Player(printStream, bufferedReader, board, "some name", "").move();

        verify(printStream).println(contains("Player some name"));
    }

    @Test
    public void shouldPlaceCorrectMarkForPlayerWhenMoveOccurs() throws IOException {
        new Player(printStream, bufferedReader, board, "some name", "%").move();

        player.move();

        verify(board).mark(anyInt(), eq("%"));
    }


    @Test
    public void shouldMarkBoardWithDifferentSymbol() throws IOException {
        String symbol = "a mark";
        Player player = new Player(printStream, bufferedReader, board, "", symbol);

        player.move();

        verify(board).mark(anyInt(), eq(symbol));
    }

    @Test
    public void shouldTellPlayerWhenTheyMoveInOccupiedLocation() throws IOException {
        when(bufferedReader.readLine()).thenReturn("5");
        when(board.isLocationAvailable(5)).thenReturn(false, true);

        player.move();

        verify(printStream).println(contains("taken"));
    }

    @Test
    public void shouldNotTellPlayerWhenTheyMoveInFreeLocation() {
        when(board.isLocationAvailable(anyInt())).thenReturn(true);

        player.move();

        verify(printStream, never()).println(contains("taken"));
    }

    @Test
    public void shouldContinueToPromptPlayerUntilAnAvailableLocationIsEntered() throws IOException {
        when(bufferedReader.readLine()).thenReturn("5", "7");
        when(board.isLocationAvailable(5)).thenReturn(false);
        player.move();
        verify(board).mark(eq(7), anyString());
    }

    @Test
    public void shouldCheckIfEachLocationEnteredByPlayerIsAvailable() throws IOException {
        when(bufferedReader.readLine()).thenReturn("5", "7");
        when(board.isLocationAvailable(5)).thenReturn(false);
        player.move();
        verify(board).isLocationAvailable(5);
        verify(board).isLocationAvailable(7);
    }


}