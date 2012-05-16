package org.tdd.samples.tictactoe;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Position {
	
	private final int row;
	private final int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !obj.getClass().isAssignableFrom(Position.class)) {
			return false;
		}
		
		Position that = (Position) obj;
		
		return new EqualsBuilder()
						.append(this.row, that.row)
						.append(this.col, that.col)
						.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
						.append(row)
						.append(col)
						.toHashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public int getEffectiveRow() {
		return row - 1;
	}

	public int getEffectiveColumn() {
		return col - 1;
	}
	
	

}
