package br.com.treinamento.tasks.infra.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.tasks.domain.user.User;

public interface UserRepositoryJPA extends JpaRepository<User, Integer>{
	
	public Optional<User> findByEmail(String email);

}
