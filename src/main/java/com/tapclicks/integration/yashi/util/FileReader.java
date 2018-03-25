package com.tapclicks.integration.yashi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapclicks.integration.yashi.YashiFeed;

public class FileReader {
	private final static Logger logger = LoggerFactory.getLogger(FileReader.class);

	public static void readFiles(String localDir, YashiFeed yashiFeed) {
		// Read the files
		File dir = new File(localDir);
		File[] files = dir.listFiles();

		for (File file : files) {
			try {
				List<String> list = new ArrayList<>();
				InputStream inputFS = new FileInputStream(file);
				try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {
					list = br.lines().skip(1).collect(Collectors.toList());
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Error while reading the files : ", e);
				}

				for (String dataLine : list) {
					try {
						yashiFeed.processLineItem(dataLine);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Error while processing the line : ", e);
						logger.error("Failed record from the data feed : " + dataLine);
						System.out.println("Error while processing line : " + dataLine);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while reading the files : ", e);
			}

		}

	}

	public static void deleteFiles(String localDir) throws Exception {
		File dir = new File(localDir);
		File[] files = dir.listFiles();
		for (File file : files) {
			file.delete();
		}
	}

}
