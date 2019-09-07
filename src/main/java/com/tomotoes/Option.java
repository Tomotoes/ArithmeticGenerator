package com.tomotoes;

import lombok.Data;
import lombok.Getter;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 16:40
 */
@Data
public class Option {
	// 方程数量
	public int amount = 10;
	// 是否包含乘除
	public boolean mulAndDiv = false;
	// 数值范围
	public int bound = 100;
	// 是否包含负数
	public boolean negative = false;
	// 方程中数字的数量
	public int quantity;

	public Option(int amount, boolean mulAndDiv, int bound, boolean negative, int quantity) {
		setAmount(amount);
		setMulAndDiv(mulAndDiv);
		setBound(bound);
		setNegative(negative);
		setQuantity(quantity);
	}
}
