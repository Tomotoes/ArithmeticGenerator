package com.tomotoes;

import com.tomotoes.utils.Number;
import com.tomotoes.utils.Operator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
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
	@Setter
	private ArrayList<String> arithmetics;
	private Number number;
	private Operator operator;
	private int quantity;

	public Generator(@NonNull Option option) {
		this.arithmetics = new ArrayList<>();
		this.quantity = option.getQuantity();
		this.number = new Number(option.getBound(), option.negative);
		this.operator = new Operator(option.mulAndDiv);
	}

	public void generate(int j) {
		val formula = new StringBuilder();

		IntStream.range(0, quantity).forEach(i -> {
			formula.append(number.getRandom());
			if (i == this.quantity - 1) {
				return;
			}
			formula.append(' ').append(operator.getRandom()).append(' ');
		});

		this.arithmetics.add(formula.toString());
	}

}
