package com.tomotoes;

import java.util.ArrayList;

/**
 * @author Simon
 * @project formula
 * @package com.tomotoes
 * @date 2019/9/7 11:40
 */
public class Operator {
	private static String ADD = "+";
	private static String SUB = "-";
	private static String MUL = "*";
	private static String DIV = "/";

	private ArrayList<String> operators = new ArrayList<String>() {
	};

	public Operator(boolean mulAndDiv) {
		this.operators.add(ADD);
		this.operators.add(SUB);
		if (mulAndDiv) {
			this.operators.add(MUL);
			this.operators.add(DIV);
		}
	}

	public String getRandom() {
		return this.operators.get((int) (Math.random() * this.operators.size()));
	}
}
