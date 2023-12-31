package com.jadhoc.expensetracker.services;

import com.jadhoc.expensetracker.domain.User;
import com.jadhoc.expensetracker.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
