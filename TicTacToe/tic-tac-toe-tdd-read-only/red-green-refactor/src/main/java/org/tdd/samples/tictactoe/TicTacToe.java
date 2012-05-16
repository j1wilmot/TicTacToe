package org.tdd.samples.tictactoe;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TicTacToe {

	private final Board board;
	
	private Mark lastMark;

	private Result result;
	
	public TicTacToe() {
		board = new Board();
		result = Result.GAME_IS_ONGOING;
	}

	public Result move(MarkedPosition markedPosition) {
		if(result != Result.GAME_IS_ONGOING) {
			throw new GameOverException("Game has already finished.");
		} else if(lastMark == markedPosition.getMark()) {
			throw new IllegalMoveException(new StringBuilder()
				.append(markedPosition.getMark())
				.append(" was already the last mark. Cannot repeat the same mark successively.")
				.toString());
		} else if(markedPosition.getMark() != Mark.X && markedPosition.getMark() != Mark.O ) {
			throw new IllegalMoveException(new StringBuilder()
				.append(markedPosition.getMark())
				.append(" is not a valid mark.")
				.toString());
		} else if(!board.contains(markedPosition.getPosition())) {
			throw new IllegalMoveException(new StringBuilder()
				.append(markedPosition.getPosition())
				.append(" is outside the board.")
				.toString());
		} else if(board.isMarked(markedPosition.getPosition())) {
			throw new IllegalMoveException(new StringBuilder()
				.append(markedPosition.getPosition())
				.append(" is already occupied.")
				.toString());
		}
		
		board.set(markedPosition);
		
		lastMark = markedPosition.getMark();
		
		Set<Line> allLines = new HashSet<Line>();
		allLines.add(board.getRow(markedPosition.getPosition()));
		allLines.add(board.getColumn(markedPosition.getPosition()));
		allLines.addAll( board.getDiagonals(markedPosition.getPosition()));
		
		Iterator<Line> i = allLines.iterator();
		while(result == Result.GAME_IS_ONGOING && i.hasNext()) {
			Line line = i.next();
			if(line.isMarkedTheSame()) {
				result = markedPosition.getMark() == Mark.X ? Result.X_WINS : Result.O_WINS;
			}
		} 

		return result;
	}

	public Board getBoard() {
		return  board;
	}

}
