package com.taskmanager.todosimple.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.taskmanager.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
