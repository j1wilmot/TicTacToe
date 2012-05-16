package org.tdd.samples.tictactoe.util;

import java.util.Arrays;
import java.util.List;

import org.tdd.samples.tictactoe.util.NumberUtil.Order;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NumberUtilTest extends TestSuite {

	public static Test suite() {
		TestSuite allTests = new TestSuite();
		allTests.addTestSuite(WhenGetOrder.class);
		return allTests;
	}
	
	public static class WhenGetOrder extends TestCase {
		public void testGivenNumbersAreIncreasing_ThenAscending() {
			List<? extends Number> increasingNumbers = Arrays.asList(1,2,3);
			
			Order order = NumberUtil.getOrder(increasingNumbers);
			
			assertEquals("Should have been ascending order.", Order.Ascending, order);
		}

		public void testGivenNumbersAreDecreasing_ThenDescending() {
			List<? extends Number> decreasingNumbers = Arrays.asList(3,2,1);
			
			Order order = NumberUtil.getOrder(decreasingNumbers);
			
			assertEquals("Should have been descending order.", Order.Descending, order);
		}
		
		public void testGivenNumbersAreNeitherIncreasingNorDecreasing_ThenNoOrder() {
			List<? extends Number> neitherIncreasingNorDecreasingNumbers = Arrays.asList(3,1,2);
			
			Order order = NumberUtil.getOrder(neitherIncreasingNorDecreasingNumbers);
			
			assertNull("Should have been no order.",  order);
		}
	} 
}
