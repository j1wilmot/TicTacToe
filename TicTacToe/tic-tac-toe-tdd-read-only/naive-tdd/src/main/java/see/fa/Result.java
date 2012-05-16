package see.fa;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Result {
	X_WINS, O_WINS, GAME_NOT_FINISH;
	
	private static final Map<Mark, Result> WINNING_MARK_TO_RESULT; 
	
	static {
		Map<Mark, Result> winningMarkToResult = new HashMap<Mark, Result>();
		winningMarkToResult.put(Mark.X, X_WINS);
		winningMarkToResult.put(Mark.O, O_WINS);
		
		WINNING_MARK_TO_RESULT = Collections.unmodifiableMap(winningMarkToResult);
	}
	
	public static Result valueOf(Line line) {
		Mark commonMark = line != null ? line.getCommonMark() : null;
		
		Result result = WINNING_MARK_TO_RESULT.containsKey(commonMark) ? WINNING_MARK_TO_RESULT.get(commonMark) : GAME_NOT_FINISH; 
		
		return result;
	}
}
