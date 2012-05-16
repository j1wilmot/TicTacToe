package see.fa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	private Board board;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		board = new Board(
				new Mark[][]{
						{Mark.X,       Mark.O,      Mark.NONE}, 
						{Mark.NONE, Mark.X,      Mark.O},
						{Mark.O,      Mark.NONE, Mark.X}
				}
			);
	}

	@Override
	protected void tearDown() throws Exception {
		board = null;
		super.tearDown();
	}

	public void testGivenAPosition_WhenGetRow_ThenReturnRowContainingTheMarksOfThatRow() {
		Line rowAtPosition = board.getRow(new Position(1,1));
		
		assertEquals("Should have retrieved 1st row.", new Line(Mark.X, Mark.O, Mark.NONE), rowAtPosition);
	}
	
	public void testGivenAPosition_WhenGetCol_ThenReturnColContainingTheMarksOfThatCol() {
		Line colAtPosition = board.getCol(new Position(1,1));
		
		assertEquals("Should have retrieved 1st col.", new Line(Mark.X, Mark.NONE, Mark.O), colAtPosition);
	}
	
	public void testGivenAPositionForUpperLeftToLowerRight_WhenGetDiag_ThenReturnLineContainingTheMarksOfThatDiag() {
		Set<Line> diagonals = board.getDiags(new Position(1,1));
		
		assertEquals("Should have retrieved upper left to lower right diagonal line.", Collections.singleton(new Line(Mark.X, Mark.X, Mark.X)), diagonals);
	}
	
	public void testGivenAPositionForUpperRightToLowerLeft_WhenGetDiag_ThenReturnLineContainingTheMarksOfThatDiag() {
		Set<Line> diagonals = board.getDiags(new Position(3,1));
		
		assertEquals("Should have retrieved upper right to lower left diagonal line.", Collections.singleton(new Line(Mark.NONE, Mark.X, Mark.O)), diagonals);
	}
	
	public void testGivenCenterPosition_WhenGetDiag_ThenReturnBothDiagLines() {
		Set<Line> diagonals = board.getDiags(new Position(2,2));
		
		assertEquals("Should have retrieved both diagonal lines.", 
				new HashSet<Line>(Arrays.asList(
						new Line(Mark.X, Mark.X, Mark.X), 
						new Line(Mark.NONE, Mark.X, Mark.O)
					)), diagonals);
	}
}
