package org.tdd.samples.tictactoe;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Line {

	private final Mark[] marks;

	public Line(Mark[] marks) {
		this.marks = marks;
	}

	public boolean isMarkedTheSame() {
		boolean markedTheSame = true;
		Mark mark = null;
		int i = 0;
		while(markedTheSame && i < marks.length) {
			if(mark == null) {
				mark = marks[i];
			} else {
				markedTheSame = mark.equals(marks[i]);
			}
			i++;
		}
		return markedTheSame;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !Line.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Line that = (Line)obj;
		return new EqualsBuilder()
			.append(this.marks, that.marks)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		    .append(marks)
		    .toHashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
