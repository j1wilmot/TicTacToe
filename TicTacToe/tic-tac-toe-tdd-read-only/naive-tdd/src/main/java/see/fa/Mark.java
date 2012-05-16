package see.fa;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public enum Mark {

	X('X'), O('O'), NONE('_');

	private static final String ROW_SEPARATOR = "\n";
	private static final String COL_SEPARATOR = " ";
	private final char symbol;

	private Mark(char symbol){
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}

	public static boolean equals(Mark[] lhs, Mark[] rhs) {
		return equals(new Mark[][]{lhs}, new Mark[][]{rhs});
	}

	public static boolean equals(Mark[][] lhs, Mark[][] rhs) {
		return new EqualsBuilder()
			.append(lhs, rhs)
			.isEquals();
	}

	public static int hashCode(Mark[]... marks) {
		return new HashCodeBuilder().append(marks).toHashCode();
	}

	public static String toString(Mark[] marks) {
		return toString(new Mark[][]{marks});
	}

	public static String toString(Mark[][]  marks) {
		List<String> rowToStrings = new LinkedList<String>();
		for(Mark[] row : marks) {
			rowToStrings.add(StringUtils.join(row, COL_SEPARATOR));
		}
		
		return new StringBuilder()
			.append(ROW_SEPARATOR)
			.append(StringUtils.join(rowToStrings, ROW_SEPARATOR))
			.append(ROW_SEPARATOR)
			.toString();
	}

}
