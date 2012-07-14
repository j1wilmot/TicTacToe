package name.jeremywilmot.tictactoe;

import static name.jeremywilmot.tictactoe.Board.Position.BOTTOM_CENTER;
import static name.jeremywilmot.tictactoe.Board.Position.BOTTOM_LEFT;
import static name.jeremywilmot.tictactoe.Board.Position.BOTTOM_RIGHT;
import static name.jeremywilmot.tictactoe.Board.Position.MIDDLE_CENTER;
import static name.jeremywilmot.tictactoe.Board.Position.MIDDLE_LEFT;
import static name.jeremywilmot.tictactoe.Board.Position.MIDDLE_RIGHT;
import static name.jeremywilmot.tictactoe.Board.Position.TOP_CENTER;
import static name.jeremywilmot.tictactoe.Board.Position.TOP_LEFT;
import static name.jeremywilmot.tictactoe.Board.Position.TOP_RIGHT;

import java.util.HashMap;
import java.util.Map;

import name.jeremywilmot.tictactoe.Board.Position;
import name.jeremywilmot.tictactoe.Board.SquareState;

public class BoardDisplay {

	Board board;
	Map<SquareState, String> stateRepresentations;
	
	public BoardDisplay(Board board) {
		this.board = board;
		this.stateRepresentations = new HashMap<SquareState, String>();
		init();
	}
	
	private void init() {
		stateRepresentations.put(SquareState.EMPTY, ".");
		stateRepresentations.put(SquareState.PLAYER1, "X");
		stateRepresentations.put(SquareState.PLAYER2, "Y");
	}
	
	public String display() {
		StringBuilder output = new StringBuilder();
		appendTopRow(output);
		appendMiddleRow(output);
		appendBottomRow(output);
		return output.toString();
	}

	private void appendRow(StringBuilder output, Position pos1, Position pos2, Position pos3) {
		appendPosition(output, pos1);
		appendPosition(output, pos2);
		appendPositionNewLine(output, pos3);
	}
	
	private void appendTopRow(StringBuilder output) {
		appendRow(output, TOP_LEFT, TOP_CENTER, TOP_RIGHT);
	}
	
	private void appendMiddleRow(StringBuilder output) {
		appendRow(output, MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT);
	}

	private void appendBottomRow(StringBuilder output) {
		appendRow(output, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT);
	}

	private void appendPositionNewLine(StringBuilder output, Position position) {
		appendStateRepresentation(output, position);
		output.append("\n");
	}

	private void appendPosition(StringBuilder output, Position position) {
		appendStateRepresentation(output, position);
		output.append(" ");
	}

	private void appendStateRepresentation(StringBuilder output,
			Position position) {
		SquareState state = board.getSquare(position);
		output.append(stateRepresentations.get(state));
	}
	
}
