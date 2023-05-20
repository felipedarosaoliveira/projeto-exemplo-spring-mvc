package br.com.treinamento.tasks.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import br.com.treinamento.tasks.domain.task.StatusTask;
import br.com.treinamento.tasks.domain.task.Task;
import br.com.treinamento.tasks.domain.task.TaskService;
import br.com.treinamento.tasks.domain.user.User;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping("/tasks/{code}")
	public String formTask(@PathVariable String code, Model model) {
		
		Task currentTask = null;
		String errorMessage = "";
		if( "novo".equals(code)) {
			currentTask = new Task();
		}else {
			try {
				currentTask = service.findById(Integer.parseInt(code));
				errorMessage = currentTask != null ? "" : "Não foi encontrada uma tarefa com o código informado";
			}catch (RuntimeException e) {
				errorMessage = "Não foi encontrada uma tarefa com o código informado";
			}
		}
		
		model.addAttribute("allStatus", StatusTask.values());
		model.addAttribute("errorMessage",errorMessage);
		model.addAttribute("message","oiiiiiii");
		model.addAttribute("currentTask", currentTask != null ? currentTask : new Task());
		
		return "tasks/task-form";
	}
	
	@PostMapping(path = "/tasks")
	public String saveTask(Task task, 
						   @Nullable @SessionAttribute User currentUser, 
						   Model model) {
		String message = "";
		String errorMessage = "";
		
		if(task != null) {
			task.setUserId(currentUser.getId());
			if(task.getId()== null) {
				task = service.insert(task);
			}else {
				task = service.update(task);
			}
			message = "Tarefa salva com sucesso";
		}else {
			errorMessage = "Não foi possível salvar os dados da tarefa";
		}
		
		
		model.addAttribute("allStatus", StatusTask.values());
		model.addAttribute("errorMessage",errorMessage);
		model.addAttribute("message",message);
		model.addAttribute("currentTask", task != null ? task : new Task());
		
		return "tasks/task-form";
	}
	
	@GetMapping("/dashboard/tasks")
	public String getDashboard(@Nullable @SessionAttribute User currentUser, 
							   @Nullable @RequestParam String title, 
							   @Nullable @RequestParam String status,
			                   Model model) {
		
		model.addAttribute("totalOpenTasks", service.countTotalOpenTasks(currentUser));
		model.addAttribute("totalFinishedTasks", service.countTotalFinishedTasks(currentUser));
		model.addAttribute("totalMyTasks", service.countTotalTaks(currentUser));
		model.addAttribute("totalTasks", service.countTotalTasks());
		model.addAttribute("tasks", service.findAllTasks(currentUser, getStatus(status),title));
		
		return "tasks/dashboard";
	}
	
	private StatusTask getStatus(String code) {
		code = code != null ? code : "";
		for(StatusTask type : StatusTask.values()) {
			if(type.name().equalsIgnoreCase(code)) {
				return type;
			}
		}
		return null;
		
	}

}
