package com.currencyfair.demo;

import com.currencyfair.demo.controller.DemoController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	DemoController demoController;

	@Test
	void contextLoads() {
		Assert.notNull(demoController,"Demo Controller is null");
	}

}
