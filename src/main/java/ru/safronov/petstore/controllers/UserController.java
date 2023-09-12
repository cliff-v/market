package ru.safronov.petstore.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.safronov.petstore.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/email")
    public ResponseEntity<String> getCurrentUserEmail(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserEmail(authentication.getName()));
    }

    @GetMapping(path = "/email_old")
    public ResponseEntity<String> getCurrentUserEmail() {
        return ResponseEntity.ok(userService.getCurrentUserEmail());
    }
}
