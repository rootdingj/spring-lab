package com.spring.data.cache.cache;

import com.spring.data.cache.cache.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching(proxyTargetClass = true)
public class CacheDemoApplication implements ApplicationRunner {
	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(CacheDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// cache
		/*log.info("Count: {}", coffeeService.findAllCoffee().size());
		for (int i = 0; i < 10; i++) {
			log.info("Reading from cache.");
			coffeeService.findAllCoffee();
		}
		coffeeService.reloadCoffee();
		log.info("Reading after refresh.");
		coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));*/

		// redis
		log.info("Count: {}", coffeeService.findAllCoffee().size());
		for (int i = 0; i < 5; i++) {
			log.info("Reading from cache.");
			coffeeService.findAllCoffee();
		}
		Thread.sleep(5_000);
		log.info("Reading after refresh.");
		coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
	}
}
