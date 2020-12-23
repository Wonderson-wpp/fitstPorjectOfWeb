package com.peng.bookstore.user.dao;

import com.peng.bookstore.user.domain.User;
import com.peng.bookstore.utils.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    public User findByUsername(String username) {
        try {
            return queryRunner.query("select * from user where username = ?", new BeanHandler<User>(User.class), username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User findByEmail(String email) {
        try {
            return queryRunner.query("select * from user where email = ?", new BeanHandler<User>(User.class), email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try {
            queryRunner.update("insert into user(uid, username,password, email, code, state) values(?, ?, ?, ?, ?, ?)",
                    user.getUid(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getCode(),
                    user.getState());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User findByCode(String code) {
        try {
            return queryRunner.query("select * from user where code = ?", new BeanHandler<User>(User.class), code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateState(User user) {
        try {
            queryRunner.update("update user set state = ? where username = ?", true, user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
