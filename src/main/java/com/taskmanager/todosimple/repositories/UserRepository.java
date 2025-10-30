package com.taskmanager.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanager.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
