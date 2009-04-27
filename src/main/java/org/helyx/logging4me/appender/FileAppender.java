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

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import org.helyx.logging4me.SystemLogger;
import org.helyx.logging4me.LoggerEvent;
import org.helyx.logging4me.util.FileUtil;

public class FileAppender extends AbstractAppender {

	private static final String CAT = "FILE";
	
	public static String FILE_APPENDER_NAME = "FILE";

	private String filePath;
	private FileConnection fc;
	private PrintStream ps;
	
	public FileAppender(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void onWrite(LoggerEvent loggerEvent, String logMessage) {
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
		SystemLogger.debug(CAT, "First root path: " + firstRootPath);
		fc = FileUtil.openFileConnection(firstRootPath, filePath, Connector.READ_WRITE);
		
		SystemLogger.debug(CAT, "Log File path: " + fc.getPath());
		SystemLogger.debug(CAT, "Log File url: " + fc.getURL());
		boolean fileExists = fc.exists();
		
		SystemLogger.debug(CAT, fileExists ? "Log file already exists" : "Log file does not exist");

		if (!fileExists) {
			SystemLogger.debug(CAT, "Creating file");
			fc.create();
		}

		SystemLogger.debug(CAT, fc.canWrite() ? "Log file is writable" : "Log file is not writable");

		ps = new PrintStream(fc.openOutputStream());
	}

	public void close() throws Exception {
		if (ps != null) {
			ps.flush();
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
