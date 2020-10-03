package com.tahir.dao;

import java.util.ArrayList;




import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import com.tahir.entity.User;

import antlr.collections.List;

//Springe bir DAO olduðunu söylüyoruz.
@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	//CRUD ÝSLEMLERÝ
	
	//create
	
	public Long insert(User user)
	{
		
		return (Long)sessionFactory.getCurrentSession().save(user);
		
		
	}
	//update
	public void  update(User user)
	{
			
	 sessionFactory.getCurrentSession().update(user);
		
		
	}
	

	
	public User getFindByIdUsernameAndPass(String username,String pass)
	{
Query query=sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND password=:pass AND active=:active")
.setString("username",username)
.setString("pass",pass)
.setBoolean("active",true);
User user=null;
try {
	user= (User) query.getSingleResult();
} catch (javax.persistence.NoResultException e) {
	user=null;
}
		return user;
	}
	
		
	
	
	
	
	public User getFindByIdUsername(String username)
	{
Query query=sessionFactory.getCurrentSession().createQuery("FROM User where username=:username")
.setString("username",username);
	return (User) query.getSingleResult();
		
	}
	
	public User getFindByKey(String key)
	{
Query query=sessionFactory.getCurrentSession().createQuery("FROM User where keyreg=:key")
.setString("key",key);
User user=null;

try {
	user= (User) query.getSingleResult();
} catch (javax.persistence.NoResultException e) {
	user=null;
}
		return user;
	}
	

}
