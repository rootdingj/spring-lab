package com.spring.data.redis;

import com.spring.data.redis.model.Coffee;
import com.spring.data.redis.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories
public class RedisDemoApplication implements ApplicationRunner {

	private static final String KEY = "COFFEE_MENU";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ReactiveStringRedisTemplate redisTemplate;

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private JedisPoolConfig jedisPoolConfig;


	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}

	@Bean
	ReactiveStringRedisTemplate reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
		return new ReactiveStringRedisTemplate(factory);
	}

	@Bean
	@ConfigurationProperties("redis")
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}

	@Bean(destroyMethod = "close")
	public JedisPool jedisPool(@Value("${redis.host}") String host) {
		return new JedisPool(jedisPoolConfig(), host);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*ReactiveHashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
		CountDownLatch cdl = new CountDownLatch(1);

		List<Coffee> list = jdbcTemplate.query(
				"select * from t_coffee", (rs, i) ->
						Coffee.builder()
								.id(rs.getLong("id"))
								.name(rs.getString("name"))
								.price(rs.getLong("price"))
								.build()
		);

		Flux.fromIterable(list)
				.publishOn(Schedulers.single())
				.doOnComplete(() -> log.info("list ok"))
				.flatMap(c -> {
					log.info("try to put {},{}", c.getName(), c.getPrice());
					return hashOps.put(KEY, c.getName(), c.getPrice().toString());
				})
				.doOnComplete(() -> log.info("set ok"))
				.concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
				.doOnComplete(() -> log.info("expire ok"))
				.onErrorResume(e -> {
					log.error("exception {}", e.getMessage());
					return Mono.just(false);
				})
				.subscribe(b -> log.info("Boolean: {}", b),
						e -> log.error("Exception {}", e.getMessage()),
						() -> cdl.countDown());
		log.info("Waiting");
		cdl.await();*/

		log.info(jedisPoolConfig.toString());
		try (Jedis jedis = jedisPool.getResource()) {
			coffeeService.findAllCoffee().forEach(c -> {
				jedis.hset("springbucks-menu",
						c.getName(),
						Long.toString(c.getPrice().getAmountMinorLong()));
			});

			Map<String, String> menu = jedis.hgetAll("springbucks-menu");
			log.info("Menu: {}", menu);

			String price = jedis.hget("springbucks-menu", "espresso");
			log.info("espresso - {}",
					Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
		}

	}
}
