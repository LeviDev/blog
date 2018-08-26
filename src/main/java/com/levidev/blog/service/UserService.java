package com.levidev.blog.service;

import com.levidev.blog.po.User;

public interface UserService {
    User CheckUser(String username, String password);
}
