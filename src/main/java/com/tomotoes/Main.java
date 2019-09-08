package com.tomotoes;

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
public class Main {
	public static ArrayList<String> result;
	public static Generator generator;
	public static Option option;

	public static List<String> getResult(int amount) {
		generator.setArithmetics(new ArrayList<>());

		IntStream.range(0, amount).parallel().forEach(generator::generate);
		val arithmetics = generator.getArithmetics();

		Map<String, Double> map = new HashMap<>(arithmetics.size());
		arithmetics.parallelStream().forEach(arithmetic -> map.put(arithmetic, Script.eval(arithmetic)));

		return map.entrySet().parallelStream()
			.filter(entry -> entry.getValue() <= option.getBound())
			.map(entry -> entry.getKey() + " = " + entry.getValue())
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		option = Commander.parse(args);
		generator = new Generator(option);
		result = new ArrayList<>(option.getAmount());

		while (result.size() != option.getAmount()) {
			result.addAll(getResult(option.getAmount() - result.size()));
		}

		Loggerr logger = new Loggerr(option.getFilePath());
		result.forEach(logger::log);
	}
}
