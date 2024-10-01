package com.limb0s.limb0sBoot.controllers;


import com.limb0s.limb0sBoot.models.User;
import com.limb0s.limb0sBoot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("userList", userService.getUsers());
        return "user/users";
    }

    @GetMapping("/addUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/newUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/newUser";
        }
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/updateUser";
        }
        userService.updateUser(user.getId(), user);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

}
