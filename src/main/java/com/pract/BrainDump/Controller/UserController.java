package com.pract.BrainDump.Controller;

import com.pract.BrainDump.Entity.User;
import com.pract.BrainDump.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user") //this annotation means when you put this in URL then it'll point to this controller, if yoy don't put this
//it'll get confused between 2 controllers and won't know for which controller you've hit the api
@Tag(name = "User APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editUserDetails(@RequestBody User user, @PathVariable String id){
        Optional<User> updatedUser = userService.editUserDetails(user, id);
        if(updatedUser.isEmpty()){
            return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.NO_CONTENT);
    }

}
