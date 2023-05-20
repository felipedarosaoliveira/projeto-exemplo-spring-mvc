package br.com.treinamento.tasks.domain.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	
	@Autowired
	private IUserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public boolean insert(User user) {
		repository.insert(user);
		return true;
	}
	public boolean update(User user) {
		repository.update(user);
		return true;
	}
	public boolean delete(Integer id) {
		return repository.delete(id);
	}
	public User findById(Integer id) {
		return repository.findById(id);
	}
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	public User authenticate(String email, String password) {
		User user = repository.findByEmail(email);
		if(user != null && user.getPassword() != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	
	

}
