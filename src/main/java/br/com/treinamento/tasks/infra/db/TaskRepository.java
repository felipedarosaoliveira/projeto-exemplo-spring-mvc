package br.com.treinamento.tasks.infra.db;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.treinamento.tasks.domain.task.ITaskRepository;
import br.com.treinamento.tasks.domain.task.StatusTask;
import br.com.treinamento.tasks.domain.task.Task;

@Repository
public class TaskRepository implements ITaskRepository {

	@Autowired
	TaskRepositoryJPA jpaRepository;

	@Override
	public List<Task> findAll() {
		return jpaRepository.findAll();
	}

	@Override
	public List<Task> findAllByUserId(Integer userId) {
		return jpaRepository.findAllByUserId(userId);
	}

	@Override
	public List<Task> findAllByUserIdAndStatus(Integer userId, StatusTask status) {
		return jpaRepository.findAllByUserIdAndStatus(userId, status);
	}

	@Override
	public long countByUserIdAndStatus(Integer userId, StatusTask status) {
		if(userId == null && status == null) {
			return 0;
		}
		if(status == null) {
			return jpaRepository.countByUserId(userId);
		}
		return jpaRepository.countByUserIdAndStatus(userId, status);
	}

	@Override
	public long count() {
		return jpaRepository.count();
	}

	@Override
	public List<Task> findAll(Integer userId, String title, StatusTask status) {
		title = title != null ? title.trim() : "";
		if(userId == null && status == null) {
			return Collections.emptyList();
		}
		if(status == null) {
			return jpaRepository.findAllByUserIdAndTitleStartWitch(userId, title);
		}
		return jpaRepository.findAllByUserIdAndStatusAndTitleStartWitch(userId, status, title);
	}

	@Override
	public Task insert(Task task) {
		return jpaRepository.save(task);
	}

	@Override
	public Task update(Task task) {
		return jpaRepository.save(task);
	}

	@Override
	public boolean delete(Integer id) {
		jpaRepository.deleteById(id);
		return ! jpaRepository.existsById(id);
	}

	@Override
	public Task findById(Integer id) {
		Optional<Task> optional = jpaRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	

}
