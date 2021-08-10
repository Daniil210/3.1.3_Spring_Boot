package com.danila.spring_boot_crud;

import com.danila.spring_boot_crud.Dao.UserDao;
import com.danila.spring_boot_crud.Model.Role;
import com.danila.spring_boot_crud.Model.User;
import com.danila.spring_boot_crud.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class ApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void Testing() {
		/*AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userService = context.getBean(UserDao.class);*/


        User user1 = new User("naaasos", "laaaas");
        User user2 = new User("aaa", "aaa");
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

		user1.getRoles().add(role2);
		//user1.setId(1l);
		//user2.getRoles().add(role2);
		//user2.getRoles().add(role1);
		userService.saveUser(user1);
		//userService.updateUser(user1);

		//System.out.println(userService.getUserByEmail("memtt@pozvonim.com"));
		//userService.deleteUserById(user1);
	}

}
