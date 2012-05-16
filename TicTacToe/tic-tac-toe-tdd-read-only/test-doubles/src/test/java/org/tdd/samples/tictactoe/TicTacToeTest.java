package org.tdd.samples.tictactoe;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TicTacToeTest extends TestSuite {
	
	public static Test suite() {
		TestSuite allTests = new TestSuite();
		allTests.addTestSuite(GivenAnUnfinishedGame_WhenMove.class);
		allTests.addTestSuite(GivenRunningGame_WhenMove.class);
		allTests.addTestSuite(GivenRunningGame_AndLastMoveIsXMark_WhenMove.class);
		allTests.addTestSuite(GivenRunningGame_AndLastMoveIsOMark_WhenMove.class);
		allTests.addTestSuite(GivenEndGame_WhenMove.class);
		return allTests;
	}

	public static class AbstractTicTacToeTest extends TestCase {
		protected TicTacToe ticTacToe;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			ticTacToe = new TicTacToe();
		}

		@Override
		protected void tearDown() throws Exception {
			ticTacToe = null;
			super.tearDown();
		}		
	}
	
	public static class GivenAnUnfinishedGame_WhenMove extends AbstractTicTacToeTest {
		public void testGivenAMarkedPosition_ThenMarkPositionOnBoard() {
			MarkedPosition givenMarkedPosition = new MarkedPosition(1, 1, Mark.X);
			
			ticTacToe.move(givenMarkedPosition);
			
			Board board = ticTacToe.getBoard();
			assertEquals("Should have marked board.",
					new Board(new Mark[][]{
							{Mark.X, Mark.NONE, Mark.NONE},
							{Mark.NONE, Mark.NONE, Mark.NONE},
							{Mark.NONE, Mark.NONE, Mark.NONE}
					}), 
					board
			);
		}
		
		public void testGivenAMarkedPositionIsWithinTheBoard_WhenMove_ThenDoNotThrowIllegalMoveException() {
			MarkedPosition givenMarkedPositionWithinTheBoard = new MarkedPosition(3, 3, Mark.X);
			
			try {
				ticTacToe.move(givenMarkedPositionWithinTheBoard);
			} catch(IllegalMoveException illegalMoveException) {
				fail("Should not have thrown " + illegalMoveException);
			}
		}

		public void testGivenAMarkedPositionIsOutsideTheBoard_WhenMove_ThenThrowIllegalMoveException() {
			MarkedPosition givenMarkedPositionOutisdeTheBoard = new MarkedPosition(4, 4, Mark.X);
			
			try {
				ticTacToe.move(givenMarkedPositionOutisdeTheBoard);
				fail("Should not have thrown " + IllegalMoveException.class.getName());
			} catch(IllegalMoveException illegalMoveException) {
				// expected behaviour.
			}
		}
		
		public void testGivenANoneMarkMarkedPosition_WhenMove_ThenThrowIllegalMoveException() {
			MarkedPosition givenNoneMarkMarkedPosition = new MarkedPosition(1, 1, Mark.NONE);
			
			try {
				ticTacToe.move(givenNoneMarkMarkedPosition);
				fail("Should not have thrown " + IllegalMoveException.class.getName());
			} catch(IllegalMoveException illegalMoveException) {
				// expected behaviour.
			}
		}
		
		public void testGivenANullMarkMarkedPosition_WhenMove_ThenThrowIllegalMoveException() {
			MarkedPosition givenNullMarkMarkedPosition = new MarkedPosition(1, 1, null);
			
			try {
				ticTacToe.move(givenNullMarkMarkedPosition);
				fail("Should not have thrown " + IllegalMoveException.class.getName());
			} catch(IllegalMoveException illegalMoveException) {
				// expected behaviour.
			}
		}
		
		public void test_ThenDoNotThrowGameOverException() {
			MarkedPosition givenAnyMarkedPosition = new MarkedPosition(1, 1, Mark.X);
			
			try {
				ticTacToe.move(givenAnyMarkedPosition);
			} catch(GameOverException e) {
				fail("Should not have thrown a " + e.getClass().getName() + "  when ");
			}
		}
	}
	
	public abstract static class AbstractGivenRunningGame_WhenMove extends AbstractTicTacToeTest {

		protected Mark nextMark;
		
		@Override
		protected void setUp() throws Exception {
			super.setUp();
			
			ticTacToe.move(new MarkedPosition(2, 2, Mark.X));
			nextMark = Mark.O;
		}

		@Override
		protected void tearDown() throws Exception {
			nextMark = null;
			super.tearDown();
		}
		
	}

	public static class GivenRunningGame_WhenMove extends AbstractGivenRunningGame_WhenMove {
		public void testGivenAMarkedPositionThatIsNotYetOccupied_ThenDotNotThrowIllegalMoveException() {
			MarkedPosition givenMarkedPositionThatIsNotYetOccupied = new MarkedPosition(3, 3, nextMark);
			
			try {
				ticTacToe.move(givenMarkedPositionThatIsNotYetOccupied);
			} catch (IllegalMoveException illegalMoveException) {
				fail("Should NOT have thrown an " + illegalMoveException);
			}
		}

		public void testGivenAMarkedPositionThatIsAlreadyOccupied_ThenThrowIllegalMoveException() {
			MarkedPosition givenMarkedPositionThatIsAlreadyOccupied = new MarkedPosition(2, 2, nextMark);
			
			try {
				ticTacToe.move(givenMarkedPositionThatIsAlreadyOccupied);
				fail("Should have thrown an " + IllegalMoveException.class.getName());
			} catch (IllegalMoveException illegalMoveException) {
				
			}
		}
		
		@SuppressWarnings("unused")
		public void testGivenMarkedPositionWhichMakesAHorizontalLine_ThenWin() {
			SETUP_TWO_HORIZONTAL_MARKS : { 
				ticTacToe.move(new MarkedPosition(1, 1, Mark.O));
				ticTacToe.move(new MarkedPosition(2, 1, Mark.X));
				ticTacToe.move(new MarkedPosition(1, 3, Mark.O));
			}
			
			Result result = ticTacToe.move(new MarkedPosition(2, 3, Mark.X));
			
			assertEquals("X Should have won.", Result.X_WINS, result);
		}
		
		@SuppressWarnings("unused")
		public void testGivenMarkedPositionWhichMakesAVerticalLine_ThenWin() {
			SETUP_TWO_VERTICAL_MARKS : { 
				ticTacToe.move(new MarkedPosition(1, 1, Mark.O));
				ticTacToe.move(new MarkedPosition(1, 2, Mark.X));
				ticTacToe.move(new MarkedPosition(1, 3, Mark.O));
			}
			
			Result result = ticTacToe.move(new MarkedPosition(3, 2, Mark.X));
			
			assertEquals("X Should have won.", Result.X_WINS, result);
		}
		
		@SuppressWarnings("unused")
		public void testGivenMarkedPositionWhichMakesAnUpperLeftToLowerRightDiagonalLine_ThenWin() {
			SETUP_DIAGONAL_LINE_FROM_UPPER_LEFT_TO_LOWER_RIGHT : { 
				ticTacToe.move(new MarkedPosition(1, 2, Mark.O));
				ticTacToe.move(new MarkedPosition(1, 1, Mark.X));
				ticTacToe.move(new MarkedPosition(1, 3, Mark.O));
			}
			
			Result result = ticTacToe.move(new MarkedPosition(3, 3, Mark.X));
			
			assertEquals("X Should have won.", Result.X_WINS, result);
		}
		
		@SuppressWarnings("unused")
		public void testGivenMarkedPositionWhichMakesAnUpperRightToLowerLeftDiagonalLine_ThenWin() {
			SETUP_DIAGONAL_LINE_FROM_UPPER_RIGHT_TO_LOWER_LEFT : { 
				ticTacToe.move(new MarkedPosition(1, 2, Mark.O));
				ticTacToe.move(new MarkedPosition(1, 3, Mark.X));
				ticTacToe.move(new MarkedPosition(3, 3, Mark.O));
			}
			
			Result result = ticTacToe.move(new MarkedPosition(3, 1, Mark.X));
			
			assertEquals("X Should have won.", Result.X_WINS, result);
		}
		
		public void testGivenMarkedPositionWhichMakesNoStraightLine_ThenContinueGame() {
			Result result = ticTacToe.move(new MarkedPosition(2, 3, nextMark));
			
			assertEquals("Should have been on-going.", Result.GAME_IS_ONGOING, result);
		}
	}

	public static class GivenRunningGame_AndLastMoveIsXMark_WhenMove extends AbstractGivenRunningGame_WhenMove {
		public void testGivenAnXMark_ThenThrowIllegalMoveException() {
			try {
				ticTacToe.move(new MarkedPosition(2,3,Mark.X));
				fail("Should have thrown IllegalMoveException.");
			} catch(IllegalMoveException e) {
				// expected behavior
			}
		}
		
		public void testGivenAnOMark_ThenDoNotThrowIllegalMoveException() {
			try {
				ticTacToe.move(new MarkedPosition(2,3,Mark.O));
			} catch(IllegalMoveException e) {
				fail("Should NOT have thrown IllegalMoveException.");
			}
		}
	}
	
	public static class GivenRunningGame_AndLastMoveIsOMark_WhenMove extends AbstractGivenRunningGame_WhenMove {
		
		@Override
		protected void setUp() throws Exception {
			super.setUp();
			ticTacToe.move(new MarkedPosition(2,3,Mark.O));
		}

		public void testGivenAnXMark_ThenDoNotThrowIllegalMoveException() {
			try {
				ticTacToe.move(new MarkedPosition(3,3,Mark.X));
			} catch(IllegalMoveException e) {
				fail("Should NOT have thrown IllegalMoveException.");
			}
		}
		
		public void testGivenAnOMark_ThenThrowIllegalMoveException() {
			try {
				ticTacToe.move(new MarkedPosition(3,3,Mark.O));
				fail("Should have thrown IllegalMoveException.");
			} catch(IllegalMoveException e) {
				// expected behavior
			}
		}
	}
	
	public static class GivenEndGame_WhenMove extends AbstractTicTacToeTest {
		
		public void test_ThenThrowGameOverException() {
			ticTacToe.move(new MarkedPosition(1, 1, Mark.X));
			ticTacToe.move(new MarkedPosition(2, 1, Mark.O));
			ticTacToe.move(new MarkedPosition(1, 2, Mark.X));
			ticTacToe.move(new MarkedPosition(2, 2, Mark.O));
			ticTacToe.move(new MarkedPosition(1, 3, Mark.X));
			
			try {
				ticTacToe.move(new MarkedPosition(2, 3, Mark.O));
				fail("Should not have been able to move after the game as finished.");
			} catch(GameOverException e) {
				// expected behavior
			}
		}
	}
	
}
