package com.DaoClasses;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.EntityClasses.User;
import com.ModelClasses.retrieve;
import com.ModelClasses.submit;


public interface usersDao {
	public submit addUser1(submit users);
	public boolean addUser2(User users);
	public List<User> getAllUsers();
	public retrieve getUserById(retrieve userid);
	public boolean deleteUser(User users);
}
