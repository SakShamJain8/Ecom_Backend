package com.ecom.Controller;

import com.ecom.Services.UserService;
import com.ecom.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        Date date = new Date();
        userDto.setDate(date);
        UserDto createUser = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUser , HttpStatus.CREATED);
    }
    @GetMapping("findById/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable int userId){
        UserDto findById = this.userService.getById(userId);
        return new ResponseEntity<UserDto>(findById , HttpStatus.FOUND);
    }

    @DeleteMapping("deleteuser/{userId}")
    public void delete(@PathVariable int userId){
        this.userService.delete(userId);
    }
    @GetMapping("findall")
    public ResponseEntity<List<UserDto>>findAllUser() {
        List<UserDto> findAllUser= this.userService.findAllUser();
        return new ResponseEntity<List<UserDto>>(findAllUser , HttpStatus.FOUND);
    }
}
