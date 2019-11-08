package com.hjf.blog.service;

import com.hjf.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
