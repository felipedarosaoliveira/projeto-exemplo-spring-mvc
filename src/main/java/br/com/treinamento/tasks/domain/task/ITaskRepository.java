package br.com.treinamento.tasks.domain.task;

import java.util.List;
import java.util.Map;

public interface ITaskRepository {
	
	public List<Task> findAll();
	public List<Task> findAllByUserId(Integer userId);
	public List<Task> findAllByUserIdAndStatus(Integer userId, StatusTask status);
	public long countByUserIdAndStatus(Integer userId, StatusTask status);
	public long count();
	public List<Task> findAll(Integer userId, String title, StatusTask status);
	public Task insert(Task task);
	public Task update(Task task);
	public boolean delete(Integer id);
	public Task findById(Integer id);

}
