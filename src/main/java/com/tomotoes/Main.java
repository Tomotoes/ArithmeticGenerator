package com.tomotoes;

import lombok.val;

import java.util.stream.IntStream;

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
		IntStream.range(0, option.getAmount()).forEach(generator::generate);
		val arithmetics = generator.getArithmetics();

		val result = arithmetics.stream().map(arithmetic -> arithmetic + " = " + Script.resolve(arithmetic));

		Log log = new Log(option.getFilePath());
		result.forEach(log::toFile);
		log.close();
	}
}
