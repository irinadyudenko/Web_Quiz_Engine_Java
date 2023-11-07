package engine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import engine.dto.UserDto;
import engine.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto) ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }
}
