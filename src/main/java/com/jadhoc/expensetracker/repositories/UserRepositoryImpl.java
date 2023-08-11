package com.jadhoc.expensetracker.repositories;

import com.jadhoc.expensetracker.domain.User;
import com.jadhoc.expensetracker.exceptions.EtAuthException;

public class UserRepositoryImpl implements UserRepository{

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
