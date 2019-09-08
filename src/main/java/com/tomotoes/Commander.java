package com.tomotoes;

import lombok.extern.java.Log;
import lombok.val;
import org.apache.commons.cli.*;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 18:41
 */
@Log
public class Commander {

	private static Options options = new Options();

	public static void setOptions() {
		options.addOption("a", "amount", true, "The number of arithmetics, the default is 10.");
		options.addOption("m", "mulAndDiv", false, "Whether to include multiplication and division operations, the default is false.");
		options.addOption("b", "bound", true, "The range of number, the default is 100.");
		options.addOption("n", "negative", false, "Whether it contains negative numbers, the default is false.");
		options.addOption("q", "quantity", true, "The number of numbers in arithmetic, the default is 2.");
		options.addOption("f", "filePath", true, "The path of file, the default is ./result.txt.");
		options.addOption("h", "help", false, "Print help information.");
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

			val amount = Integer.parseInt(commandLine.getOptionValue("a", Option.getDefaultAmount()));
			val mulAndDiv = commandLine.hasOption("m");
			val bound = Integer.parseInt(commandLine.getOptionValue("b", Option.getDefaultBound()));
			val negative = commandLine.hasOption("n");
			val quantity = Integer.parseInt(commandLine.getOptionValue("q", Option.getDefaultQuantity()));
			val filePath = commandLine.getOptionValue("f", Option.getDefaultFilePath());

			if (bound < 0 || amount < 0 || quantity <= 1) {
				log.warning("Input parameter error. See 'java -jar arithmetic-generator-1.0-SNAPSHOT --help'");
				System.exit(1);
			}

			return new Option(amount, mulAndDiv, bound, negative, quantity, filePath);
		} catch (ParseException e) {
			e.printStackTrace();
			log.warning("Input parameter error. See 'java -jar arithmetic-generator-1.0-SNAPSHOT --help'");
			System.exit(1);
		}
		return null;
	}
}
