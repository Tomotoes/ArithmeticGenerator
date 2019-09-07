package com.tomotoes;

import lombok.val;
import org.apache.commons.cli.*;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 18:41
 */

public class Args {

	private static Options options = new Options();

	public static void setOptions() {
		options.addOption("a", "amount", true, "The number of arithmetics, the default is 10");
		options.addOption("m", "mulAndDiv", false, "Whether to include multiplication and division operations, the default is false");
		options.addOption("b", "bound", true, "The range of number, the default is 100");
		options.addOption("n", "negative", false, "Whether it contains negative numbers, the default is false");
		options.addOption("q", "quantity", true, "The number of numbers, the default is 2");
		options.addOption("h", "help", false, "Print help");
	}

	public static Option parse(String[] args) {
		setOptions();

		CommandLineParser parser = new DefaultParser();
		CommandLine commandLine;
		try {
			commandLine = parser.parse(options, args);
			if (commandLine.hasOption("h")) {
				HelpFormatter hf = new HelpFormatter();
				hf.setWidth(110);
				hf.printHelp("arithmetic-generator", options, true);
				System.exit(0);
				return null;
			}
			val amount = Integer.parseInt(commandLine.getOptionValue("a", "10"));
			val mulAndDiv = commandLine.hasOption("m");
			val bound = Integer.parseInt(commandLine.getOptionValue("b", "100"));
			val negative = commandLine.hasOption("n");
			val quantity = Integer.parseInt(commandLine.getOptionValue("q", "2"));

			return new Option(amount, mulAndDiv, bound, negative, quantity);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
