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

//      可能存在 bug , 假如操作数的范围(bound)是 0, 所以只能随机 [0,1) 中的数字, 即 num 一直等于 0
//      如果 0 之前是一个 "/" 号, 那么就陷入了 死循环,
//      所以 就让 "/ 0" 这个异常情况存在, 在后续 parse 过程 ,一定会报异常, 然后再捕获处理即可

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
