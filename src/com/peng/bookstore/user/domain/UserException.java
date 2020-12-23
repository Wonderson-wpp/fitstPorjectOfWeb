package com.peng.bookstore.user.domain;

public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
