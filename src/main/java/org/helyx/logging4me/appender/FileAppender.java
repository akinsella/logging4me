/**
 * Copyright (C) 2007-2009 Alexis Kinsella - http://www.helyx.org - <Helyx.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helyx.logging4me.appender;

import java.io.PrintStream;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import org.helyx.logging4me.Logger;
import org.helyx.logging4me.LoggerFactory;
import org.helyx.logging4me.util.FileUtil;

public class FileAppender extends AbstractAppender {
	
	private static final Logger logger = LoggerFactory.getLogger("FILE_APPENDER");
	
	public static String FILE_APPENDER_NAME = "FILE";

	private String filePath;
	private FileConnection fc;
	private PrintStream ps;
	
	public FileAppender(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void onWrite(int level, Logger logger, String message, Date date, String logMessage) {
		try {
			if (ps != null) {
				ps.print(logMessage + "\r\n");
			}
		}
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	}

	public void open() throws Exception {
		String firstRootPath = FileUtil.findFirstRoot();
		logger.info("First root path: " + firstRootPath);
		fc = FileUtil.openFileConnection(firstRootPath, filePath, Connector.READ_WRITE);
		
		logger.info("Log File path: " + fc.getPath());
		logger.info("Log File url: " + fc.getURL());
		boolean fileExists = fc.exists();
		
		logger.info(fileExists ? "Log file already exists" : "Log file does not exist");

		if (!fileExists) {
			logger.info("Creating file");
			fc.create();
		}

		logger.info(fc.canWrite() ? "Log file is writable" : "Log file is not writable");

		ps = new PrintStream(fc.openOutputStream());
	}

	public void close() throws Exception {
		if (ps != null) {
			try {
				ps.close();
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
		if (fc != null) {
			try {
				fc.close();
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}

	public void flush() throws Exception {
		if (ps != null) {
			ps.flush();
		}
	}

	public String getName() {
		return FILE_APPENDER_NAME;
	}

}
