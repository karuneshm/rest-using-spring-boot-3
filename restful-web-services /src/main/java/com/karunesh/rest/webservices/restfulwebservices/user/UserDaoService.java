package com.karunesh.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;



@Component
public class UserDaoService {
	
	public static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Jim",LocalDate.now().minusYears(20)));
		
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		
		Predicate<User> predicate = user -> user.getId().equals(id);
		User user = users.stream().filter(predicate).findFirst().orElse(null);
		return user;
		
	}

	public User saveUser(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
    public void deleteById(int id) {
		
		Predicate<User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
		
	}
	
}
