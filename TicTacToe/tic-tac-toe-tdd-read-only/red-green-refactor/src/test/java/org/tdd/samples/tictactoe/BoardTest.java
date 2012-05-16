package org.tdd.samples.tictactoe;

import java.util.Set;

import static org.fest.assertions.Assertions.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BoardTest extends TestSuite {
	
	public static Test suite() {
		TestSuite allTests = new TestSuite();
		allTests.addTestSuite(WhenSet.class);
		allTests.addTestSuite(WhenContains.class);
		allTests.addTestSuite(WhenIsMarked.class);
		allTests.addTestSuite(WhenGetRow.class);
		allTests.addTestSuite(WhenGetColumn.class);
		allTests.addTestSuite(WhenGetDiagonals.class);
		return allTests;
	}
	
	public abstract static class AbstractBoardTest extends TestCase {
		protected Board board;
		
		@Override
		protected void setUp() throws Exception {
			super.setUp();
			board = new Board();
		}

		@Override
		protected void tearDown() throws Exception {
			board = null;
			super.tearDown();
		}
	}
	
	public static class WhenSet extends AbstractBoardTest {
		public void testShouldUpdateBoard() {
			MarkedPosition givenMarkedPosition = new MarkedPosition(1, 1, Mark.X);
			
			board.set(givenMarkedPosition);
			
			assertEquals("Should have marked the position.", 
					new Board(new Mark[][]{
							{Mark.X, Mark.NONE, Mark.NONE}, 
							{Mark.NONE, Mark.NONE, Mark.NONE}, 
							{Mark.NONE, Mark.NONE, Mark.NONE}
					}), 
					board);
		}
	}
	
	public abstract static class GivenNonEmptyBoard extends AbstractBoardTest {
		@Override
		protected void setUp() throws Exception {
			super.setUp();
			board = new Board(new Mark[][]{
					{Mark.X, Mark.NONE, Mark.NONE}, 
					{Mark.NONE, Mark.NONE, Mark.NONE}, 
					{Mark.NONE, Mark.NONE, Mark.NONE}
			});
		}
	}
	
	public static class WhenContains extends GivenNonEmptyBoard {
		public void testGivenPositionWithinTheBoard_ThenReturnTrue() {
			Position givenPositionThatIsInsideTheBoard = new Position(3, 3);
			
			boolean actualContains = board.contains(givenPositionThatIsInsideTheBoard);
			
			assertTrue(new StringBuilder()
						.append("Should have been TRUE because board ").append(board)
						.append(" contains position ").append(givenPositionThatIsInsideTheBoard)
						.toString() , actualContains);
		}
		
		public void testGivenPositionOutsideTheBoard_ThenReturnTrue() {
			Position givenPositionThatIsOutsideTheBoard = new Position(4, 4);
			
			boolean actualContains = board.contains(givenPositionThatIsOutsideTheBoard);
			
			assertFalse(new StringBuilder()
						.append("Should have been FALSE because board ").append(board)
						.append(" does not contain position ").append(givenPositionThatIsOutsideTheBoard)
						.toString() , actualContains);
		}
	}
	
	public static class WhenIsMarked extends GivenNonEmptyBoard {
		
		public void testGivenPositionIsAlreadyOccupied_ThenReturnTrue() {
			Position givenPositionThatIsAlreadyOccupied = new Position(1, 1);
			
			boolean actualMarked = board.isMarked(givenPositionThatIsAlreadyOccupied);
			
			assertTrue(new StringBuilder()
						.append("Should have been TRUE because board ").append(board)
						.append(" is already marked at position ").append(givenPositionThatIsAlreadyOccupied)
						.toString() , actualMarked);
		}
		
		public void testGivenPositionIsNotYetOccupied_ThenReturnFalse() {
			Position givenPositionThatIsNotYetOccupied = new Position(1, 2);
			
			boolean actualMarked = board.isMarked(givenPositionThatIsNotYetOccupied);
			
			assertFalse(new StringBuilder()
						.append("Should have been FALSE because board ").append(board)
						.append(" is NOT yet marked at position ").append(givenPositionThatIsNotYetOccupied)
						.toString() , actualMarked);
		}
	}
	
	public static class WhenGetRow extends GivenNonEmptyBoard {
		
		public void testShouldReturnMarksInTheBoardWithTheSameRow() {
			Line row = board.getRow(new Position(2, 3));
			
			assertEquals("Should have returned the marks with all the rows equal to 2.", 
					new Line(new Mark[]{Mark.NONE, Mark.NONE, Mark.NONE}), row);
		}
	}
	
	public static class WhenGetColumn extends GivenNonEmptyBoard {
		
		public void testShouldReturnMarksInTheBoardWithTheSameColumn() {
			Line row = board.getColumn(new Position(2, 3));
			
			assertEquals("Should have returned the marks with all the columns equal to 3.", 
					new Line(new Mark[]{Mark.NONE, Mark.NONE, Mark.NONE}), row);
		}
	}

	public static class WhenGetDiagonals extends GivenNonEmptyBoard {
		
		private static final Line UPPER_LEFT_TO_LOWER_RIGHT_DIAGONAL 
		= new Line(new Mark[]{Mark.X, Mark.NONE, Mark.NONE});
		
		private static final Line UPPER_RIGHT_TO_LOWER_LEFT_DIAGONAL 
		= new Line(new Mark[]{Mark.NONE, Mark.NONE, Mark.NONE});
		
		public void testGivenInUpperLeftToLowerRightDiagonal_ThenReturnUpperLeftToLowerRightDiagonal() {
			Set<Line> diagonals = board.getDiagonals(new Position(1, 1));
			
			assertThat(diagonals).containsOnly(UPPER_LEFT_TO_LOWER_RIGHT_DIAGONAL);
		}

		public void testGivenInUpperRightToLowerLeftDiagonal_ThenReturnUpperRightToLowerLeftDiagonal() {
			Set<Line> diagonals = board.getDiagonals(new Position(1, 3));
			
			assertThat(diagonals).containsOnly(UPPER_RIGHT_TO_LOWER_LEFT_DIAGONAL);
		}
		
		public void testGivenInCenter_ThenReturnBothDiagonals() {
			Set<Line> diagonals = board.getDiagonals(new Position(2, 2));
			
			assertThat(diagonals).containsOnly(
					UPPER_LEFT_TO_LOWER_RIGHT_DIAGONAL, 
					UPPER_RIGHT_TO_LOWER_LEFT_DIAGONAL);
		}
		
		public void testGiveNotInDiagonalLine_ThenReturnEmptySet() {
			Set<Line> diagonals = board.getDiagonals(new Position(2, 1));
			
			assertThat(diagonals).isEmpty();
		}
	}
}
