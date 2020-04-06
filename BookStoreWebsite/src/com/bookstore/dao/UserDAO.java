package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;
import com.bookstore.utility.HashGenerationException;
import com.bookstore.utility.HashGeneratorUtils;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	private HashGeneratorUtils hash;
	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
		hash = new HashGeneratorUtils();
	}
	
	public Users create(Users t) {
		String hashPass;
		try {
			hashPass = HashGeneratorUtils.generateMD5(t.getPassword());
			t.setPassword(hashPass);
		} catch (HashGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.create(t);
	}
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		String hashedPass;
		try {
			hashedPass = hash.generateMD5(password);
			parameters.put("email", email);
			parameters.put("password", hashedPass);
			List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
			return listUsers.size() == 1;
		} catch (HashGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Users findByEmail(String email) {
		List<Users> listResult = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if(listResult != null && listResult.size() >= 1) {
			return listResult.get(0);
		}
		return null;
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		String hashPass;
		try {
			hashPass = HashGeneratorUtils.generateMD5(user.getPassword());
			user.setPassword(hashPass);
		} catch (HashGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		// TODO Auto-generated method stub
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Users.class, id);
	}
	
	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Users.countAll");
	}

	
}
