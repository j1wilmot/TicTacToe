package org.tdd.samples.tictactoe;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LineTest extends TestSuite {
	
	public static Test suite() {
		TestSuite allTests = new TestSuite();
		allTests.addTestSuite(WhenIsMarkedTheSame.class);
		return allTests;
	}
	
	public static class WhenIsMarkedTheSame extends TestCase {
		public void testGivenAllXMarks_ThenTrue() {
			Line allXMarks = new Line(new Mark[]{Mark.X, Mark.X, Mark.X});
			
			boolean markedTheSame = allXMarks.isMarkedTheSame();
			
			assertTrue("All X marks should be marked the same.", markedTheSame);
		}
		
		public void testGivenAllOMarks_ThenTrue() {
			Line allOMarks = new Line(new Mark[]{Mark.O, Mark.O, Mark.O});
			
			boolean markedTheSame = allOMarks.isMarkedTheSame();
			
			assertTrue("All O marks should be marked the same.", markedTheSame);
		}
		
		public void testGivenNoMarks_ThenTrue() {
			Line noMarks = new Line(new Mark[]{Mark.NONE, Mark.NONE, Mark.NONE});
			
			boolean markedTheSame = noMarks.isMarkedTheSame();
			
			assertTrue("No marks should be marked the same.", markedTheSame);
		}
		
		public void testGivenDifferentMarks_ThenFalse() {
			Line differentMarks = new Line(new Mark[]{Mark.NONE, Mark.O, Mark.X});
			
			boolean markedTheSame = differentMarks.isMarkedTheSame();
			
			assertFalse("Different marks should NOT be marked the same.", markedTheSame);
		}
	}

}
