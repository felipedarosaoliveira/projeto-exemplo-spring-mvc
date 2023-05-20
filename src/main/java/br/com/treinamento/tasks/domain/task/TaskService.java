package br.com.treinamento.tasks.domain.task;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.tasks.domain.user.User;

@Service
public class TaskService {

	@Autowired
	private ITaskRepository repository;

	public List<Task> findAll() {
		return repository.findAll();
	}


	public Task insert(Task task) {
		return repository.insert(task);
	}

	public Task update(Task task) {
		return repository.update(task);
	}

	public boolean delete(Integer id) {
		return repository.delete(id);
	}

	public Task findById(Integer id) {
		return repository.findById(id);
	}

	public Long countTotalOpenTasks(User currentUser) {
		Integer id = currentUser != null ? currentUser.getId() : null;
		return repository.countByUserIdAndStatus(id, StatusTask.Open);
	}

	public Long countTotalFinishedTasks(User currentUser) {
		Integer id = currentUser != null ? currentUser.getId() : null;
		return repository.countByUserIdAndStatus(id, StatusTask.Closed);
	}

	public long countTotalTaks(User currentUser) {
		Integer id = currentUser != null ? currentUser.getId() : null;
		return repository.countByUserIdAndStatus(id, null);
	}

	public long countTotalTasks() {
		return repository.count();
	}

	public List<Task> findAllTasks(User currentUser, StatusTask status, String title) {
		title = title != null ? title.trim() : "";
		if(currentUser == null || currentUser.getId() == null) {
			return Collections.emptyList();
		}
		if(title.length() > 0) {
			return repository.findAll(currentUser.getId(),title,status);
		}
		if(status != null) {
			return repository.findAllByUserIdAndStatus(currentUser.getId(), status);
		}
		
		return repository.findAllByUserId(currentUser.getId());
	}

}
