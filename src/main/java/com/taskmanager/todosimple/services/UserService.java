package com.taskmanager.todosimple.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taskmanager.todosimple.repositories.UserRepository;

import com.taskmanager.todosimple.models.User;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found id: " + id + ", Type: " + User.class.getName()));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user = this.userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = findById(user.getId());
        newUser.setPassword(user.getPassword());
        return this.userRepository.save(newUser);
    }

    public void delete(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete user id: " + id + ", Type: " + User.class.getName() + " - cannot delete because there are tasks associated with this user.");
        }
        
    }

}
