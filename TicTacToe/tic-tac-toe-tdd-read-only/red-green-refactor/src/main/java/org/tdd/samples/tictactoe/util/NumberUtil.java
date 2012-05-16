package org.tdd.samples.tictactoe.util;

import java.util.List;

public class NumberUtil {
	
	public enum Order { Ascending, Descending };

	public static Order getOrder(List<? extends Number> numbers) {
		return isAscending(numbers) ? Order.Ascending :
				isDescending(numbers) ? Order.Descending :
				null;
	}

	private static boolean isAscending(List<? extends Number> numbers) {
		boolean ascending = true;
		int i = 0;
		while(ascending && i < numbers.size() - 1) {
			ascending = numbers.get(i).longValue()+1 == numbers.get(i+1).longValue();
			i++;
		}
		return ascending;
	}
	
	private static boolean isDescending(List<? extends Number> numbers) {
		boolean descending = true;
		int i = 0;
		while(descending && i < numbers.size() - 1) {
			descending = numbers.get(i).longValue()-1 == numbers.get(i+1).longValue();
			i++;
		}
		return descending;
	}
}
