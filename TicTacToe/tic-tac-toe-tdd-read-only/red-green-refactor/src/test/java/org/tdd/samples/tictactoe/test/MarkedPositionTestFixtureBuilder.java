package org.tdd.samples.tictactoe.test;

import java.util.ArrayList;
import java.util.List;

import org.tdd.samples.tictactoe.Mark;
import org.tdd.samples.tictactoe.MarkedPosition;
import org.tdd.samples.tictactoe.Position;

public class MarkedPositionTestFixtureBuilder {

	private final List<FakeMarkedPosition> markedPositions;
	
	public MarkedPositionTestFixtureBuilder() {
		this(1);
	}
	
	public MarkedPositionTestFixtureBuilder(int count) {
		markedPositions = new ArrayList<FakeMarkedPosition>();
		for(int i = 0; i < count; i++) {
			markedPositions.add(new FakeMarkedPosition());
		}
	}
	
	public MarkedPositionTestFixtureBuilder withRow(int row) {
		for(FakeMarkedPosition markedPosition : markedPositions) {
			markedPosition.row = row;
		}
		return this;
	}
	
	public MarkedPositionTestFixtureBuilder withCol(int col) {
		for(FakeMarkedPosition markedPosition : markedPositions) {
			markedPosition.col = col;
		}
		return this;
	}
	
	public MarkedPositionTestFixtureBuilder withRowsAndColumns(int[][] rowsAndColumns) {
		for(int i = 0; i < rowsAndColumns.length; i++) {
			FakeMarkedPosition markedPosition = markedPositions.get(i);
			markedPosition.row = rowsAndColumns[i][0];
			markedPosition.col = rowsAndColumns[i][1];
		}
		return this;
	}
	
	public MarkedPositionTestFixtureBuilder withSameRowDifferentColumns(int row, int[] columns) {
		for(int i = 0; i < columns.length; i++) {
			FakeMarkedPosition markedPosition = markedPositions.get(i);
			markedPosition.row = row;
			markedPosition.col = columns[i];
		}
		return this;
	}
	
	public MarkedPositionTestFixtureBuilder withDifferentRowsAndSameColumn(int[] rows, int column) {
		for(int i = 0; i < rows.length; i++) {
			FakeMarkedPosition markedPosition = markedPositions.get(i);
			markedPosition.row = rows[i];
			markedPosition.col = column;
		}
		return this;
	}

	public MarkedPositionTestFixtureBuilder withMark(Mark mark) {
		for(FakeMarkedPosition markedPosition : markedPositions) {
			markedPosition.mark = mark;
		}
		return this;
	}
	
	public MarkedPositionTestFixtureBuilder withMarks(Mark... marks) {
		for(int i = 0; i < marks.length; i++) {
			FakeMarkedPosition markedPosition = markedPositions.get(i);
			markedPosition.mark = marks[i];
		}
		return this;
	}
	
	public MarkedPosition build() {
		return markedPositions.get(0);
	}

	public MarkedPosition[] buildArray() {
		return markedPositions.toArray(new MarkedPosition[markedPositions.size()]);
	}
	
	private static class FakeMarkedPosition extends MarkedPosition {
		
		private int row;
		
		private int col;

		private Mark mark;

		public FakeMarkedPosition() {
			super(1, 1, Mark.NONE);
		}

		@Override
		public Mark getMark() {
			return mark;
		}

		@Override
		public Position getPosition() {
			return new Position(row, col);
		}

		
	}

}
