package com.tomotoes;

import com.tomotoes.utils.LoggerFormatter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.File;
import java.util.logging.*;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 20:00
 */

@Log
public class Loggerr {
	@SneakyThrows
	public Loggerr(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		log.setUseParentHandlers(false);
		FileHandler fileHandler = new FileHandler(fileName);
		fileHandler.setFormatter(new LoggerFormatter());
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new LoggerFormatter());
		log.addHandler(fileHandler);
		log.addHandler(consoleHandler);
	}

	public void log(String message) {
		log.info(message);
	}

}
