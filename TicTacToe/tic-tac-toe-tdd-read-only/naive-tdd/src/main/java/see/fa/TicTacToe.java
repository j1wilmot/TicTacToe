package see.fa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class TicTacToe {
	
	private final Board board;
	
	public TicTacToe() {
		board = new Board();
	}

	public Result move(Position position, Mark mark) {
		board.set(position, mark);

		Set<Line> lineResults = new HashSet<Line>();
		lineResults.add(board.getRow(position));
		lineResults.add(board.getCol(position));
		lineResults.addAll(board.getDiags(position));
		
		Result result = Result.GAME_NOT_FINISH;
		Iterator<Line> i = lineResults.iterator();
		while(result == Result.GAME_NOT_FINISH && i.hasNext()) {
			result = Result.valueOf(i.next()); 
		}
		
		return result; 
	}

	public Board getBoard() {
		return board;
	}

}
