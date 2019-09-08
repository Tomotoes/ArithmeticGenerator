package com.tomotoes;

import com.udojava.evalex.Expression;
import lombok.val;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 13:24
 */
public class Script {

	public static double eval(String script) {
		try {
			val result = new Expression(script).eval().toString();
			return Double.parseDouble(result);
		} catch (ArithmeticException e) {
			return Double.MAX_VALUE;
		}
	}
}
