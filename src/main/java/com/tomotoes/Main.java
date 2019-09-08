package com.tomotoes;

import lombok.extern.java.Log;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
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
	public static ArrayList<String> results;
	public static Generator generator;
	public static Option option;

	public static List<String> getResult(int amount) {
		generator.setArithmetics(new CopyOnWriteArrayList<>());

		IntStream.range(0, amount).parallel().forEach(generator::generate);
		val arithmetics = generator.getArithmetics();

		Map<String, Double> resultsInArithmetics = new ConcurrentHashMap<>(arithmetics.size());
		arithmetics.parallelStream()
			.filter(arithmetic -> results.parallelStream().noneMatch(result -> result.startsWith(arithmetic)))
			.forEach(arithmetic -> resultsInArithmetics.put(arithmetic, Script.eval(arithmetic)));

		return resultsInArithmetics.entrySet().parallelStream()
			.filter(entry -> entry.getValue() <= option.getBound())
			.map(entry -> entry.getKey() + " = " + entry.getValue())
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		option = Commander.parse(args);
		generator = new Generator(option);
		results = new ArrayList<>(option.getAmount());

		int numberOfAttempts = 0;
		val maximumOfAttempts = 30;
		while (results.size() != option.getAmount()) {
			val result = getResult(option.getAmount() - results.size());

			// There may be failures.
			// For example, requiring 1000 arithmetics to be generated, and the maximum number of operands is 2 and the maximum number of operands is 2.
			// Obviously, this is not possible.
			numberOfAttempts = result.size() != 0 ? 0 : numberOfAttempts + 1;
			if (numberOfAttempts == maximumOfAttempts) {
				log.warning("Unable to generate the specified number of arithmetic.");
				return;
			}

			results.addAll(result);
		}

		Loggerr logger = new Loggerr(option.getFilePath());
		results.forEach(logger::log);
	}
}
