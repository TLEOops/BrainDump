package com.pract.BrainDump.Service;

import com.pract.BrainDump.Entity.Note;
import com.pract.BrainDump.Entity.User;
import com.pract.BrainDump.Repository.NoteRepository;
import com.pract.BrainDump.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public Optional<User> editUserDetails(User user, String id) {
        Optional<User> optionalOldUser = userRepository.findById(id);
        if(optionalOldUser.isEmpty()){
            return Optional.empty(); //If no user exists with that ID, the method returns an empty Optional to indicate "no result".
        }
        User oldUser = optionalOldUser.get();
        oldUser.setUserName(user.getUserName());
        oldUser.setPassword(user.getPassword());
        return Optional.of(userRepository.save(oldUser));

    }
    //findById may or may not find a user, so it returns an Optional<User> instead of a raw object.
    //User u = userRepository.findById(id); // could be null
    //u.getUserName(); // BOOM – NullPointerException

    public boolean deleteUser(String id) {
        userRepository.deleteById(id);
        return true;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public Optional<User> findUserById(String myId) {
        return userRepository.findById(myId);
    }
}
