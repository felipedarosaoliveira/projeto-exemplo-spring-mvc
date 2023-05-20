package br.com.treinamento.tasks.infra.controller;

import br.com.treinamento.tasks.domain.task.StatusTask;

public class TaskDTO {
	
	private String id;
	private String userId;
	private String title;
	private String description;
	private StatusTask status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusTask getStatus() {
		return status;
	}
	public void setStatus(StatusTask status) {
		this.status = status;
	}
	
	

}
