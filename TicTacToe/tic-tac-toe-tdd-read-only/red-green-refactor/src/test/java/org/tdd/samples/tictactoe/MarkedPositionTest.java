package org.tdd.samples.tictactoe;

import org.tdd.samples.tictactoe.test.MarkedPositionTestFixtureBuilder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MarkedPositionTest extends TestSuite {

	public static Test suite() {
		TestSuite allTests = new TestSuite();
		allTests.addTestSuite(WhenCompareTo.class);
		return allTests;
	}
	
	public static class WhenCompareTo extends TestCase {
		private MarkedPosition lesserRow;
		private MarkedPosition equalRowAndLesserColumn;
		private MarkedPosition equalRowAndColumn;
		private MarkedPosition equalRowAndGreaterColumn;
		private MarkedPosition greaterRow;

		@Override
		protected void setUp() throws Exception {
			super.setUp();
			lesserRow = new MarkedPositionTestFixtureBuilder().withRow(1).build();
			equalRowAndLesserColumn = new MarkedPositionTestFixtureBuilder().withRow(2).withCol(1).build();
			equalRowAndColumn = new MarkedPositionTestFixtureBuilder().withRow(2).withCol(2).build();
			equalRowAndGreaterColumn = new MarkedPositionTestFixtureBuilder().withRow(2).withCol(3).build();
			greaterRow = new MarkedPositionTestFixtureBuilder().withRow(3).build();
		}

		@Override
		protected void tearDown() throws Exception {
			greaterRow = null;
			equalRowAndGreaterColumn = null;
			equalRowAndColumn = null;
			equalRowAndLesserColumn = null;
			lesserRow = null;
			super.tearDown();
		}

		public void testGivenLesserRowAgainstGreaterRow_ThenNegativeOne() {
			int comparison = lesserRow.compareTo(greaterRow);
			
			assertEquals("Should have been less than.", -1, comparison);
		}
		
		public void testGivenGreaterRowAgainstLesserRow_ThenPositiveOne() {
			int comparison = greaterRow.compareTo(lesserRow);
			
			assertEquals("Should have been more than.",  1, comparison);
		}
		
		public void testGivenEqualRowAndLesserColumnAgainstGreaterColumn_ThenNegativeOne() {
			int comparison = equalRowAndLesserColumn.compareTo(equalRowAndGreaterColumn);
			
			assertEquals("Should have been less than.", -1, comparison);
		}
		
		public void testGivenEqualRowAndGreaterColumnAgainstLesserColumn_ThenPositiveOne() {
			int comparison = equalRowAndGreaterColumn.compareTo(equalRowAndLesserColumn);
			
			assertEquals("Should have been more than.", 1, comparison);
		}
		
		public void testGivenEqualRowAndEqualColumn_ThenZero() {
			int comparison = equalRowAndColumn.compareTo(equalRowAndColumn);
			
			assertEquals("Should have been equal.", 0, comparison);
		}
	}
	
	
}
