package com.tomotoes;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes
 * @date 2019/9/7 20:00
 */
public class Log {
	private PrintWriter writer;

	public Log(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			this.writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toFile(String message) {
		System.out.println(message);
		writer.println(message);
	}

	public void close() {
		this.writer.close();
	}

}
