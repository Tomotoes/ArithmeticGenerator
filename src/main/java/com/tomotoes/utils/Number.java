package com.tomotoes.utils;

import java.util.Random;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 15:57
 */
public class Number {
	private int bound;
	private boolean negative;
	private Random random = new Random();

	public Number(int bound, boolean negative) {
		this.bound = bound;
		this.negative = negative;
	}

	public String getRandom() {
		int number = random.nextInt(bound);
		if (negative && Math.random() < 0.5) {
			number = -number;
		}
		return number >= 0 ? Integer.toString(number) : "(" + number + ")";
	}
}
