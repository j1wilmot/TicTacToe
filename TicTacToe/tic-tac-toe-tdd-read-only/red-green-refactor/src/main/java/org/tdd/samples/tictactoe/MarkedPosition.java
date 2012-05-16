package org.tdd.samples.tictactoe;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class MarkedPosition implements Comparable<MarkedPosition> {

	private final Position position;
	private final Mark mark;

	public MarkedPosition(int row, int col, Mark mark) {
		this.position = new Position(row, col);
		this.mark = mark;
	}

	public Mark getMark() {
		return mark;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !MarkedPosition.class.isAssignableFrom(MarkedPosition.class)) {
			return false;
		}
		
		MarkedPosition that = (MarkedPosition) obj;
		return new EqualsBuilder()
		    .append(this.position, that.position)
		    .append(this.mark, that.mark)
		    .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		    .append(position)
		    .append(mark)
			.toHashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public int compareTo(MarkedPosition that) {
		return new CompareToBuilder()
			.append(this.getPosition().getEffectiveRow(), that.getPosition().getEffectiveRow())
			.append(this.getPosition().getEffectiveColumn(), that.getPosition().getEffectiveColumn())
		    .toComparison();
	}

}
