package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;


    @RestController
    @RequestMapping("/api/admin")
    public class RestAdminController {
        private final UserService userService;
        private final RoleService roleService;

        @Autowired
        public RestAdminController(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
        }

        @GetMapping("/users")
        public ResponseEntity<List<User>> getListUsers(){
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersAndRoles());
        }

        @PostMapping("/users")
        public ResponseEntity<User> createUser(@RequestBody User user) {
            userService.createUser(user);
            User createdUser = userService.findByUsername(user.getUsername());
            return createdUser != null
                    ? new ResponseEntity<>(createdUser, HttpStatus.CREATED)
                    : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @PutMapping("/users/{id}")
        public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id) {
            userService.updateUser(id, user);
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<List<User>> deleteUser(@PathVariable("id") Long id) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersAndRoles());
        }
    }

