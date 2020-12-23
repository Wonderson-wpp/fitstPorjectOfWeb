package com.peng.bookstore.user.service;

import com.peng.bookstore.user.dao.UserDao;
import com.peng.bookstore.user.domain.User;
import com.peng.bookstore.user.domain.UserException;

public class UserService {

    private UserDao userDao = new UserDao();

    public void regist(User form) throws UserException {
//        System.out.println("service user : " + form);
        User user = userDao.findByUsername(form.getUsername());
//        System.out.println("Service user : " + user);
        if (user != null) throw new UserException("您输入的用户名已经被注册");
        user = userDao.findByEmail(form.getEmail());
//        System.out.println("Service user : " + user);
        if (user != null) throw new UserException("您输入的邮箱已经被注册");

//        System.out.println("service user2 : " + form);
        userDao.addUser(form);
    }

    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if (user == null) throw new UserException("您输入的用户不存在");
        if (!user.getPassword().equals(form.getPassword())) {
            throw new UserException("您输入的密码不正确");
        }

        System.out.println("service login : " + user);
//        form.setState(true);
        if (!user.getState()) throw new UserException("您是死的");

        return user;
    }


    public void active(String code) throws UserException {
        User user = userDao.findByCode(code);
        if (user == null) {
            throw new UserException("不要拿假的激活码骗人");
        }
        if (user.getState()) {
            throw new UserException("您已经激活过了，不要重复激活");
        }

        userDao.updateState(user);

        System.out.println("service active " + user);
    }
}
