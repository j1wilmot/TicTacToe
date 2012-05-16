package see.fa;

import junit.framework.TestCase;

public class LineTest extends TestCase {
	
	public void testGivenRowContainsOnlyMarkX_WhenGetCommonMark_ThenMarkX() {
		Mark commonMark = new Line(Mark.X, Mark.X, Mark.X).getCommonMark();
		
		assertEquals("Common mark should have been the only mark that Row only has..", Mark.X, commonMark);
	}
	
	public void testGivenContainsAMarkNotEqualToInputMark_WhenAreAll_ThenFalse() {
		Mark commonMark = new Line(Mark.NONE, Mark.X, Mark.X).getCommonMark();
		
		assertNull("Common mark should have been the only mark that Row only has..", commonMark);
	}

}
