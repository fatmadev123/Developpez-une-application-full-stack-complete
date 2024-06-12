package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserService userService;

    /**
     * Action to get the connected user
     * @return ResponseEntity<UserDto>: the connected user
     */
    @GetMapping()
    public ResponseEntity<UserDto> getMe() {
        try {
            User user = this.userService.getMe();
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Action to update the connected user
     * @return ResponseEntity<UserDto>: the updated user
     */
    @PutMapping()
    public ResponseEntity<UserDto> update(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            User user = this.userService.update(signupRequest.getEmail(), signupRequest.getUserName(), signupRequest.getPassword());
            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
