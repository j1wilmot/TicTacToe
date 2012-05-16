package see.fa;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Position {

	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !Position.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		Position that = (Position) obj;
		return new EqualsBuilder()
			.append(this.x, that.x)
			.append(this.y, that.y)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(x)
			.append(y)
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append(x)
			.append(y)
			.toString();
	}

}
