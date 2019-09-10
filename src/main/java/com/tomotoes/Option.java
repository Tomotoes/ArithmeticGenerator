package com.tomotoes;

import lombok.Data;
import lombok.extern.java.Log;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 16:40
 */
@Data
@Log
public class Option {
	// The number of arithmetics, the default is 10.
	public int amount = 10;
	// Whether to include multiplication and division operations, the default is false.
	public boolean mulAndDiv = false;
	// The range of number, the default is 100.
	public int bound = 100;
	// Whether it contains negative numbers, the default is false.
	public boolean negative = false;
	// The number of numbers in arithmetic, the default is 2.
	public int quantity;
	// The path of file, the default is ./result.txt.
	public String filePath;

	public Option(int amount, boolean mulAndDiv, int bound, boolean negative, int quantity, String filePath) {
		if (bound < 0 || amount < 0 || quantity <= 1) {
			log.warning("Input parameter error. See 'java -jar arithmetic-generator-1.0-SNAPSHOT --help'");
			System.exit(1);
		}

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
