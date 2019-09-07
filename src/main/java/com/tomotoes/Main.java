package com.tomotoes;

import lombok.val;

/**
 * @author Simon
 * @project formula
 * @package com.tomotoes
 * @date 2019/9/7 11:39
 */
public class Main {
	public static void main(String[] args) {
		Option option = Args.parse(args);
		Generator generator = new Generator(option);
		val arithmetics = generator.getArithmetics();
		Log log = new Log("./result.txt");
		arithmetics.forEach(log::toFile);
		log.close();
	}
}
