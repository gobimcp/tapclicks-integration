package com.tapclicks.integration.yashi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TapClicksIntegrationApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TapClicksService tapClicksService;

	public static void main(String[] args) {
		SpringApplication.run(TapClicksIntegrationApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		try {
			logger.debug("Starting TapClicks Integartion for Yashi..");
			// Connect to the FTP server and download files
			tapClicksService.downloadFileFromFTPServer();
			// process the data feed and store it into db
			tapClicksService.processDataFiles();
			logger.debug("Ending TapClicks Integartion for Yashi..");
		} catch (Exception e) {
			logger.error("Exception in the main program : ", e);
		}
	}
}
