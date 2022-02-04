package com.benchraid.benchraid.controller;

import com.benchraid.benchraid.entities.Task;
import com.benchraid.benchraid.entities.User;
import com.benchraid.benchraid.repositories.TaskRepository;
import com.benchraid.benchraid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Value("${upload.path}")
    private String uploadPath;



    @GetMapping("/manage")
    public String manage(@RequestParam(name = "name", defaultValue = "") String name,Model model) {
        model.addAttribute("title", "Admin Panel");
        Iterable<User> users;
        if(name != null && !name.isEmpty()) {
            users = userRepository.findByLastnameOrFirstname(name);
        }
        else {
            users = userRepository.findAll();
        }
        model.addAttribute("users", users);
        return "admin";
    }


    @GetMapping("/")
    public String index(@RequestParam(name = "name", defaultValue = "") String name,Model model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("title", "Main Page");
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("title", "Home");
        model.addAttribute("tasks", tasks);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
        model.addAttribute("isAdmin", hasAdminRole);
        String name =  ((User)authentication.getPrincipal()).getFirstname();
        model.addAttribute("name", name);

        return "main";
    }

    @PostMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(name = "title") String title,
                       @RequestParam(name = "desc") String desc,
                       Model model,
                       @RequestParam(name = "file") MultipartFile file) throws IOException {
        Task task = new Task();
        task.setTitle(title);
        task.setDesc(desc);
        task.setCreatedDate(new Date());

        if(!file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String resultFilename = uuid + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            task.setFilename(resultFilename);
        }

        User existUser = userRepository.findByUsername(user.getUsername());
        existUser.getId();
        task.setAuthor(existUser);
        taskRepository.save(task);
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("title", "Main Page");
        return "index";
    }




    @PostMapping("/sendFactory/signuprequest")
    public String signIn(@RequestParam(name = "email") String email,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "password_") String password_,
                         @RequestParam(name = "firstname") String firstname,
                         @RequestParam(name = "lastname") String lastname){
        if (password == password_){
            System.out.println(true);
        }
        User user = new User();
        user.setId(null);
        user.setUsername(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userRepository.save(user);
        return "redirect:/manage";
    }

}