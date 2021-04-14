package com.han.gp;

import com.han.gp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraduationProjectApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		System.out.println(userService.getUserByUserName("han"));

	}

}
