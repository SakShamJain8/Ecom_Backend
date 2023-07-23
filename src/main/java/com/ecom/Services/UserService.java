package com.ecom.Services;

import com.ecom.Model.User;
import com.ecom.Repository.UserRepository;
import com.ecom.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper mapper;
    public UserDto createUser(UserDto userDto){
        User user = this.mapper.map(userDto, User.class);

        User saveUser = this.userRepo.save(user);
        UserDto saveUserDto = this.mapper.map(saveUser , UserDto.class);
        return saveUserDto;
    }

    public UserDto getById(int userId){
        User findById = this.userRepo.findByuserId(userId);
        UserDto userDto = this.mapper.map(findById , UserDto.class);
        return userDto;
    }

    public void delete(int userId){
        User findById = this.userRepo.findByuserId(userId);
        this.userRepo.delete(findById);
    }

    public List<UserDto> findAllUser(){
        List<User> findAll = this.userRepo.findAll();
        List<UserDto> collect = findAll.stream().map(each ->this.mapper.map(each , UserDto.class)).collect(Collectors.toList());
        return collect;
    }
}
