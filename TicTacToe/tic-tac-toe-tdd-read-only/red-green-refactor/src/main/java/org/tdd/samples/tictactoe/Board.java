package org.tdd.samples.tictactoe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Board {
	
	private static final String ROW_SEPARATOR = "\n";
	private static final String COL_SEPARATOR = " ";
	private final Mark[][] marks;
	
	private static final List<Position> UPPER_LEFT_TO_LOWER_RIGHT = Arrays.asList(new Position(1,1), new Position(2,2), new Position(3,3));
	
	private static final List<Position> UPPER_RIGHT_TO_LOWER_LEFT = Arrays.asList(new Position(3,1), new Position(2,2), new Position(1,3));
	
	public Board() {
		this(new Mark[][]{
				{Mark.NONE, Mark.NONE, Mark.NONE},
				{Mark.NONE, Mark.NONE, Mark.NONE},
				{Mark.NONE, Mark.NONE, Mark.NONE}
		});
	}

	public Board(Mark[][] marks) {
		this.marks = marks;
	}

	public void set(MarkedPosition markedPosition) {
		marks[markedPosition.getPosition().getEffectiveRow()][markedPosition.getPosition().getEffectiveColumn()] = markedPosition.getMark();
	}

	public boolean contains(Position position) {
		return position.getEffectiveRow() < 3 && position.getEffectiveColumn() < 3;
	}

	public boolean isMarked(Position position) {
		return marks[position.getEffectiveRow()][position.getEffectiveColumn()] != Mark.NONE;
	}

	
	public Line getRow(Position position) {
		int effectiveRow = position.getEffectiveRow();
		Mark[] lineMarks = new Mark[3];
		for(int i = 0; i < lineMarks.length; i++) {
			lineMarks[i] = marks[effectiveRow][i];
		}
		return new Line(lineMarks);
	}

	public Line getColumn(Position position) {
		int effectiveColumn = position.getEffectiveColumn();
		Mark[] lineMarks = new Mark[3];
		for(int i = 0; i < lineMarks.length; i++) {
			lineMarks[i] = marks[i][effectiveColumn];
		}
		return new Line(lineMarks);
	}

	public Set<Line> getDiagonals(Position position) {
		Set<Line> diagonals = new HashSet<Line>();
		if(UPPER_LEFT_TO_LOWER_RIGHT.contains(position)) {
			diagonals.add(new Line(new Mark[]{marks[0][0], marks[1][1], marks[2][2]}));
		}
		if(UPPER_RIGHT_TO_LOWER_LEFT.contains(position)) {
			diagonals.add(new Line(new Mark[]{marks[0][2], marks[1][1], marks[2][0]}));
		}
		return diagonals;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !obj.getClass().isAssignableFrom(Board.class)) {
			return false;
		}
		
		Board that = (Board) obj;
		
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(this.marks, that.marks);
		return equalsBuilder.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(marks)
			.toHashCode();
	}

	@Override
	public String toString() {
		List<String> marksPerRow = new LinkedList<String>();
		for(Mark[] row : marks) {
			marksPerRow.add(StringUtils.join(row, COL_SEPARATOR));
		}

		return new StringBuilder()
			.append("{")
			.append("\n")
			.append(StringUtils.join(marksPerRow, ROW_SEPARATOR))
			.append("}").toString();
	}

}
