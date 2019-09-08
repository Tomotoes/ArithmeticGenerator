package com.tomotoes.utils;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Simon
 * @project arithmetic-generator
 * @package com.tomotoes.utils
 * @date 2019/9/8 14:54
 */
public class LoggerFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return record.getMessage() + "\n";
	}
}
