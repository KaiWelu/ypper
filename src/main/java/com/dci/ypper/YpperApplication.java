package com.dci.ypper;

import com.book.spring_book.SpringBookApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YpperApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(YpperApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YpperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("===== YPPR IS UP AND RUNNING! =====");
	}

}
