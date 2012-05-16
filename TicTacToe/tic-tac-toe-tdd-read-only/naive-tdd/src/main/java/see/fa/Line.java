package see.fa;

import java.util.List;

public class Line {

	private final Mark[] marks;

	public Line(Mark... marks) {
		this.marks = marks;
	}

	public Line(List<Mark> marks) {
		this(marks.toArray(new Mark[marks.size()]));
	}

	public Mark getCommonMark() {
		Mark commonMark = marks[0];
		int i = 1;
		while( i < marks.length && commonMark != null) {
			commonMark = commonMark == marks[i] ? commonMark : null;
			i++;
		}
		
		return commonMark;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !Line.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Line that = (Line)obj;
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
