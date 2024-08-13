package com.example.Practice_m1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mock")
public class LibraryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  IssueRepository issueRepository;

    @GetMapping
    public String say(){
        return "HI AM ON!";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issueBook(@RequestBody Issue issue) {
        Optional<User> user = userRepository.findById(issue.getUser().getId());
        if(user.isPresent()){
            User u=user.get();
            if(!u.isSubscribed()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok().body(issueRepository.save(issue));
            }
        }else{
            throw new UserNotFoundException("Not Found User");
        }
    }

    @GetMapping("/renew-user-subscription/{id}")
    public User renewUserSubscription(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setSubscribed(true);
            return userRepository.save(u);
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }
}
