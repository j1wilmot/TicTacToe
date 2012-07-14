package name.jeremywilmot.tictactoe;

import static org.junit.Assert.*;

import name.jeremywilmot.tictactoe.Board.Position;

import org.junit.Before;
import org.junit.Test;

import static name.jeremywilmot.tictactoe.Board.Position.*;
import static name.jeremywilmot.tictactoe.Board.SquareState.*;

public class BoardTest {


	private Board board;

	@Before
	public void init() {
		board = new Board();
	}

	@Test
	public void takeSquare() {
		for (Board.Position position : Board.Position.values()) {
			Board newBoard = board.setSquare(position, PLAYER1);
			assertEquals(PLAYER1, newBoard.getSquare(position));
		}
	}

	@Test(expected=IllegalStateException.class)
	public void playerCannotTakeSameSquare() {
		board.setSquare(TOP_LEFT, PLAYER1)
			.setSquare(TOP_LEFT, PLAYER1);
	}

	@Test(expected=IllegalStateException.class)
	public void cannotTakeOtherPlayersSquare() {
		board.setSquare(TOP_LEFT, PLAYER1)
			.setSquare(TOP_LEFT, PLAYER2);
	}

	public void testWinner(Position... positions) {
		Board newBoard = board;
		for (Position position : positions) {
			newBoard = newBoard.setSquare(position, PLAYER1);
		}
		assertTrue(newBoard.hasWinner());
	}
	
	@Test
	public void gameOverTop() {
		testWinner(TOP_LEFT, TOP_CENTER, TOP_RIGHT);
	}

	@Test
	public void gameOverMiddleAcross() {
		testWinner(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT);
	}

	@Test
	public void gameOverBottom() {
		testWinner(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT);
	}

	@Test
	public void gameOverLeft() {
		testWinner(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT);
	}

	@Test
	public void gameOverMiddleDown() {
		testWinner(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER);
	}

	@Test
	public void gameOverRight() {
		testWinner(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT);
	}

	@Test
	public void gameOverRightToLeftDiagonal() {
		testWinner(TOP_RIGHT, MIDDLE_CENTER, BOTTOM_LEFT);
	}

	@Test
	public void gameOverLeftToRightDiagonal() {
		testWinner(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT);
	}

	@Test
	public void allSquaresTakenWithNoVictor() {
		Board newBoard = board.setSquare(TOP_LEFT, PLAYER1)
			.setSquare(TOP_CENTER, PLAYER2)
			.setSquare(TOP_RIGHT, PLAYER1)
			.setSquare(MIDDLE_LEFT, PLAYER1)
			.setSquare(MIDDLE_CENTER, PLAYER2)
			.setSquare(MIDDLE_RIGHT, PLAYER1)
			.setSquare(BOTTOM_LEFT, PLAYER2)
			.setSquare(BOTTOM_CENTER, PLAYER1)
			.setSquare(BOTTOM_RIGHT, PLAYER2);
		assertFalse(newBoard.hasWinner());
		assertTrue(newBoard.gameOver());
	}

	@Test
	public void gameNotOver() {
		Board newBoard = board.setSquare(MIDDLE_LEFT, PLAYER1)
			.setSquare(MIDDLE_CENTER, PLAYER2)
			.setSquare(MIDDLE_RIGHT, PLAYER1);
		assertFalse(newBoard.hasWinner());
	}


}
