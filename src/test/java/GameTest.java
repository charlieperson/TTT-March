import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameTest {

    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Game game;

    @Before
    public void setUp() throws Exception {
        board = mock(Board.class);
        playerOne = mock(Player.class);
        playerTwo = mock(Player.class);
        game = new Game(board, playerOne, playerTwo);
    }

    @Test
    public void shouldDrawBoardWhenStarting() {
        game.start();

        verify(board, times(3)).draw();
    }

    @Test
    public void shouldTellPlayerToMove() {

        game.start();

        verify(playerOne).move();
    }

    @Test
    public void shouldTellPlayerTwoToMove() {
        game.start();

        verify(playerTwo).move();
    }
}