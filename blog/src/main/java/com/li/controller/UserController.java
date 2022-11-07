package com.li.controller;

import com.li.common.Result;
import com.li.entity.User;
import com.li.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<User> selectAll(){
        return userService.selectAll();
    }

    @GetMapping("/")
    public Result selectPage(
            @RequestParam(value = "pageNum",defaultValue = "1",required = true) Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10",required = true) Integer pageSize,
            @RequestParam(value = "username",defaultValue = "",required = false) String username,
            @RequestParam(value = "role",defaultValue = "",required = false) String role)
    {
        int startIndex = (pageNum - 1) * pageSize;
        int total = userService.selectCount(username,role);
        List<User> userList = userService.selectPage(startIndex,pageSize,username,role);
        Result result = new Result().success();
        result.set("total",total).set("userList",userList);
        return result;
    }

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") String id){
        return userService.selectById(id);
    }

    @PostMapping
    public Result save(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id){
        return userService.deleteById(id);
    }

}

