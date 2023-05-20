package br.com.treinamento.tasks.infra.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.treinamento.tasks.domain.task.StatusTask;
import br.com.treinamento.tasks.domain.task.Task;

public interface TaskRepositoryJPA extends JpaRepository<Task, Integer>{
	
	@Query("select t from 	Task t where t.userId = :userId")
	public List<Task> findAllByUserId(Integer userId);
	
	@Query("select t from 	Task t where t.userId = :userId and t.status = :status")
	public List<Task> findAllByUserIdAndStatus(Integer userId, StatusTask status);
	
	@Query("select t from 	Task t where t.userId = :userId and t.status = :status and lower(t.title) like lower(concat(:title,'%'))")
	public List<Task> findAllByUserIdAndStatusAndTitleStartWitch(Integer userId, StatusTask status, String title);
	
	@Query("select t from 	Task t where t.userId = :userId and lower(t.title) like lower(concat(:title,'%'))")
	public List<Task> findAllByUserIdAndTitleStartWitch( Integer userId, String title);
	
	public long countByUserIdAndStatus(Integer userId,  StatusTask status);
	
	public long countByUserId(Integer userId);
	
	

}
