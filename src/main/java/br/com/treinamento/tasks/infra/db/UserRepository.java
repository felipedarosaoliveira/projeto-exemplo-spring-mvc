package br.com.treinamento.tasks.infra.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.treinamento.tasks.domain.user.IUserRepository;
import br.com.treinamento.tasks.domain.user.User;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
	UserRepositoryJPA repositoryJPA;

	@Override
	public List<User> findAll() {
		return repositoryJPA.findAll();
	}
	
	
	@Override
	public User insert(User user) {
		return repositoryJPA.save(user);
	}

	@Override
	public User update(User user) {
		return repositoryJPA.save(user);
	}

	@Override
	public boolean delete(Integer id) {
		repositoryJPA.deleteById(id);
		return true;
	}

	@Override
	public User findById(Integer id) {
	 Optional<User> optional = repositoryJPA.findById(id);
	 if(optional.isPresent()) {
		 return optional.get();
	 }
	 return null;
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> optional  = repositoryJPA.findByEmail(email);
		if(optional.isPresent()) {
			 return optional.get();
		 }
		 return null;
	}

}
