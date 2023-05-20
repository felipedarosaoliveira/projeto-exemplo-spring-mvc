package br.com.treinamento.tasks.domain.user;

import java.util.List;
import java.util.Map;

public interface IUserRepository {
	
	public List<User> findAll();
	public User insert(User user);
	public User update(User user);
	public boolean delete(Integer id);
	public User findById(Integer id);
	public User findByEmail(String email);
	
	

}
