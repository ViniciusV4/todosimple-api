package com.taskmanager.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taskmanager.todosimple.models.Task;
import com.taskmanager.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found id: " + id + ", Type: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId) {
        List<Task> tasks = this.taskRepository.findByUserId(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task task) {
        task.setId(null);
        task.setUser(this.userService.findById(task.getUser().getId()));
        return this.taskRepository.save(task);
    }

    @Transactional
    public Task update(Task task) {
        Task newTask = findById(task.getId());
        newTask.setDescription(task.getDescription());
        return this.taskRepository.save(newTask);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete task id: " + id + ", Type: " + Task.class.getName() + " - cannot delete because there are tasks associated with this user.");
        }
    }
}
