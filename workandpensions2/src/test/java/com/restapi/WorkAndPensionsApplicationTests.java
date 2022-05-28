package com.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.restapi.controller.WorkandPensionsController;
import com.restapi.service.WAPService;

import junit.framework.Assert;

@SpringBootTest
class WorkAndPensionsApplicationTests {

	@Autowired
	private WAPService wAPService;

	@Autowired
	private WorkandPensionsController workandPensionsController;

	@Test
	void contextLoads() throws Exception {
		assertThat(workandPensionsController).isNotNull();
	}

	@SuppressWarnings("deprecation")
	@Test
	void checkCode() {

		int res = wAPService.checkStaus();
		Assert.assertEquals(res, 200);
	}

	@Test
	void checkRecords() {
		boolean res1 = wAPService.checkSum();
		assertTrue(res1);

	}
}
