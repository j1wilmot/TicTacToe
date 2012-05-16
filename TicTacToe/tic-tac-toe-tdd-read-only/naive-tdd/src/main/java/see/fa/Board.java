package see.fa;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Board {

	private static final Set<List<Position>> POSITIONS_FOR_DIAGONAL_LINES;
	
	static {
		List<Position> upperLeftToLowerRightLinePositions = Arrays.asList(new Position(1, 1), new Position(2, 2), new Position(3, 3));
		List<Position> upperRightToLowerLeftLinePositions = Arrays.asList(new Position(1, 3), new Position(2, 2), new Position(3, 1));
	
		Set<List<Position>> diagonalLinePositions = new HashSet<List<Position>>();
		diagonalLinePositions.add(upperLeftToLowerRightLinePositions);
		diagonalLinePositions.add(upperRightToLowerLeftLinePositions);
		POSITIONS_FOR_DIAGONAL_LINES = Collections.unmodifiableSet(diagonalLinePositions);
	}
	
	private final Mark[][] marks;

	public Board(Mark[][] marks) {
		this.marks = marks;
	}

	public Board() {
		this(new Mark[][]{
				{Mark.NONE, Mark.NONE, Mark.NONE},
				{Mark.NONE, Mark.NONE, Mark.NONE},
				{Mark.NONE, Mark.NONE, Mark.NONE}
		});
	}

	public void set(Position position, Mark mark) {
		int effectiveRow = position.getX() - 1;
		int effectiveCol = position.getY() - 1;
		
		marks[effectiveRow][effectiveCol] = mark;
	}

	public Line getRow(Position position) {
		int effectiveRow = position.getX() - 1;
		return new Line(marks[effectiveRow]);
	}

	public Line getCol(Position position) {
		int effecitveCol = position.getY() - 1;
		List<Mark> lineMarks = new LinkedList<Mark>();
		for(Mark[] row : marks) {
			lineMarks.add(row[effecitveCol]);
		}
		return new Line(lineMarks);
	}

	public Set<Line> getDiags(Position position) {
		Set<Line> lines = new HashSet<Line>();
		for(List<Position> positionsForDiagonalLine : POSITIONS_FOR_DIAGONAL_LINES) {
			if(positionsForDiagonalLine.contains(position)) {
				List<Mark> marksForLine = new LinkedList<Mark>();
				for(Position positionInDiagonalLine : positionsForDiagonalLine) {
					marksForLine.add(marks[positionInDiagonalLine.getX() - 1][positionInDiagonalLine.getY() - 1]);
				}
				lines.add(new Line(marksForLine));
			}
		}
		return lines;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !Board.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Board that = (Board)obj;
		
		return Mark.equals(this.marks, that.marks);
	}

	@Override
	public int hashCode() {
		return Mark.hashCode(marks);
	}

	@Override
	public String toString() {
		return Mark.toString(marks);
	}

}
