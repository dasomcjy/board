package com.mysite.sbb.user;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }
    

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/checkUserId")
    public ResponseEntity<String> checkUserId(@RequestParam(value="userid") String userid) {
        boolean exists = userRepository.existsByuserid(userid);
        if (exists) {
            return ResponseEntity.ok("사용자 ID가 이미 존재합니다.");
        } else {
            return ResponseEntity.ok("사용자 ID가 중복되지 않습니다.");
        }
    }
    
    
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", 
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
             
            userService.create(userCreateForm.getUserid(), userCreateForm.getUsername(), 
                    userCreateForm.getEmail(), userCreateForm.getPassword1(), userCreateForm.getCompanyname(),
                    userCreateForm.getPhonenumber());

        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
    
}




