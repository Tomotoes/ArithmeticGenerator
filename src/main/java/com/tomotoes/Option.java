package com.tomotoes;

import lombok.Data;

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
	//生成的文件路径
	public String filePath;

	public Option(int amount, boolean mulAndDiv, int bound, boolean negative, int quantity, String filePath) {
		setAmount(amount);
		setMulAndDiv(mulAndDiv);
		setBound(bound);
		setNegative(negative);
		setQuantity(quantity);
		setFilePath(filePath);
	}

	public static String getDefaultAmount() {
		return "10";
	}

	public static String getDefaultBound() {
		return "100";
	}

	public static String getDefaultQuantity() { return "2"; }

	public static String getDefaultFilePath() {
		return "./result.txt";
	}
}
