package com.tomotoes;

import lombok.extern.java.Log;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 15:39
 */
@Log
public class Main {
	public static ArrayList<String> result;
	public static Generator generator;
	public static Option option;

	public static List<String> getResult(int amount) {
		generator.setArithmetics(new ArrayList<>());

		IntStream.range(0, amount).parallel().forEach(generator::generate);
		val arithmetics = generator.getArithmetics();

		Map<String, Double> map = new HashMap<>(arithmetics.size());
		arithmetics.forEach(arithmetic -> map.put(arithmetic, Script.eval(arithmetic)));

		return map.entrySet().parallelStream()
			.filter(entry -> entry.getValue() <= option.getBound())
			.map(entry -> entry.getKey() + " = " + entry.getValue())
			.filter(s -> !result.contains(s))
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		option = Commander.parse(args);
		generator = new Generator(option);
		result = new ArrayList<>(option.getAmount());

		int numberOfAttempts = 0;
		val maximumOfAttempts = 30;

		while (result.size() != option.getAmount()) {
			val r = getResult(option.getAmount() - result.size());

			// There may be failures.
			// For example, requiring 1000 arithmetics to be generated, and the maximum number of operands is 2 and the maximum number of operands is 2.
			// Obviously, this is not possible.
			numberOfAttempts = r.size() != 0 ? 0 : numberOfAttempts + 1;
			if (numberOfAttempts == maximumOfAttempts) {
				log.warning("Unable to generate the specified number of arithmetic.");
				return;
			}

			result.addAll(r);
		}

		Loggerr logger = new Loggerr(option.getFilePath());
		result.forEach(logger::log);
	}
}
