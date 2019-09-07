package com.tomotoes;

import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 18:48
 */
public class Generator {
	@Getter
	private ArrayList<String> arithmetics;
	private Number number;
	private Operator operator;
	private int quantity;

	public Generator(@NonNull Option option) {
		val amount = option.getAmount();
		this.arithmetics = new ArrayList<>(amount);
		this.quantity = option.getQuantity();
		this.number = new Number(option.getBound(), option.negative);
		this.operator = new Operator(option.mulAndDiv);
		IntStream.range(0, amount).forEach(i -> this.generate());
	}

	public void generate() {
		StringBuilder formula = new StringBuilder();

		IntStream.range(0, quantity).forEach(i -> {
			String num = number.getRandom();

			val isDiv = formula.toString().trim().endsWith("/");
			if(isDiv){
				while ("0".equals(num)) {
					num = number.getRandom();
				}
			}
			formula.append(number.getRandom());

			if (i != this.quantity - 1) {
				formula.append(' ').append(operator.getRandom()).append(' ');
			}
		});

		if (!arithmetics.contains(formula.toString())) {
			this.arithmetics.add(formula + " = " + Script.resolve(formula.toString()));
			return;
		}
		generate();
	}

}
