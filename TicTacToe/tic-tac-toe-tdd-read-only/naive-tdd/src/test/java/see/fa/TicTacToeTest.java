package see.fa;

import junit.framework.TestCase;

public class TicTacToeTest extends TestCase {
	
	private TicTacToe ticTacToe;
	
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

	public void testGivenPosition_AndMark_WhenMove_ThenMarkPosition() {
		Position givenPosition = new Position(1, 1);
		Mark givenMark = Mark.X;
		
		ticTacToe.move(givenPosition, givenMark);
		
		Board resultingBoard = ticTacToe.getBoard();
		
		assertEquals("Should have marked the board at the specified position.",
				new Board(new Mark[][]{
					{Mark.X, Mark.NONE, Mark.NONE},
					{Mark.NONE, Mark.NONE, Mark.NONE},
					{Mark.NONE, Mark.NONE, Mark.NONE}
				}), 
				resultingBoard);
	}

	public void testGivenPosition_AndMark_AndTwoSameMarksInHorizontalLine_WhenMove_ThenWinGameForMark() {
		Mark givenSameMark = Mark.X;
		
		ticTacToe.move(new Position(1, 1), givenSameMark);
		ticTacToe.move(new Position(1, 2), givenSameMark);
		Result result = ticTacToe.move(new Position(1, 3), givenSameMark);
		
		assertEquals("Should have won the game for X.", Result.X_WINS, result);
	}
	
	public void testGivenPosition_AndMark_AndTwoSameMarksInVerticalLine_WhenMove_ThenWinGameForMark() {
		Mark givenSameMark = Mark.X;
		
		ticTacToe.move(new Position(1, 1), givenSameMark);
		ticTacToe.move(new Position(2, 1), givenSameMark);
		Result result = ticTacToe.move(new Position(3, 1), givenSameMark);
		
		assertEquals("Should have won the game for X.", Result.X_WINS, result);
	}
	
	public void testGivenPosition_AndMark_AndTwoSameMarksInDiagonalLine_WhenMove_ThenWinGameForMark() {
		Mark givenSameMark = Mark.X;
		
		ticTacToe.move(new Position(1, 1), givenSameMark);
		ticTacToe.move(new Position(2, 2), givenSameMark);
		Result result = ticTacToe.move(new Position(3, 3), givenSameMark);
		
		assertEquals("Should have won the game for X.", Result.X_WINS, result);
	}
	
	public void testGivenPosition_AndMark_AndNoSameMarksOnAnyStraightLine_WhenMove_ThenContinueGame() {
		Mark givenSameMark = Mark.X;
		
		ticTacToe.move(new Position(1, 1), givenSameMark);
		ticTacToe.move(new Position(2, 3), givenSameMark);
		Result result = ticTacToe.move(new Position(3, 2), givenSameMark);
		
		assertEquals("Should have won the game for X.", Result.GAME_NOT_FINISH, result);
	}
}
