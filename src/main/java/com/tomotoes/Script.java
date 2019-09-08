package com.tomotoes;

import com.udojava.evalex.Expression;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 13:24
 */
public class Script {

	public static String resolve(String script) {
		return new Expression(script).eval().toString();
	}
}
