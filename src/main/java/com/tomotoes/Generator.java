package com.tomotoes;

import com.tomotoes.utils.Number;
import com.tomotoes.utils.Operator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
	private int bound;
	private Lock lock = new ReentrantLock();

	public Generator(@NonNull Option option) {
		this.quantity = option.getQuantity();
		this.bound = option.getBound();
		this.number = new Number(bound, option.negative);
		this.operator = new Operator(option.mulAndDiv);
	}

	public void generate(int j) {
		val formula = new StringBuilder();

		IntStream.range(0, this.quantity).forEach(i -> {
			String num = number.getRandom();

			// There may be a bug.
			// If the bounds of the operand are 0, you can only randomize the number in [0,1), so num is always equal to 0.
			// If 0 is preceded by a "/" sign, then it is in an infinite loop.
			// So let the exception of "/ 0" exist, in the subsequent parse process, the exception will be reported, and then the processing can be captured.
			if ("0".equals(num) && formula.toString().endsWith("/ ") && bound > 1) {
				while ("0".equals(num)) {
					num = number.getRandom();
				}
			}

			formula.append(num);
			if (i == this.quantity - 1) {
				return;
			}
			formula.append(' ').append(operator.getRandom()).append(' ');
		});

		lock.lock();
		this.arithmetics.add(formula.toString());
		lock.unlock();
	}

}
