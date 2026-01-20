package utilpkg;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {
	private NumberUtil() {}
	
	public static double round2(double value) {
		return BigDecimal.valueOf(value)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}
	
	public static double applySoftCurve(double value, double pivot, double power) {
		if (value <= pivot) return value;
		return pivot + Math.pow(value - pivot, power);
	}
}
