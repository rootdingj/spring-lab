package com.dj;

import com.dj.mapper.CoffeeMapper;
import com.dj.mybatis.MybatisDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class MybatisDemoApplicationTest {
	@Autowired
	CoffeeMapper coffeeMapper;

	@Test
	public void queryCoffee() {
		coffeeMapper.findAllWithRowBounds(new RowBounds(1, 3))
				.forEach(c -> log.info("Page(1) Coffee {}", c));

	}

}
