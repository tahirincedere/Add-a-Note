package com.tahir.dao;

import java.util.ArrayList;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

import com.tahir.entity.Note;

import antlr.collections.List;

//Springe bir DAO olduðunu söylüyoruz.
@Repository
public class NoteDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//CRUD ÝSLEMLERÝ
	
	//create
	
	public Long insert(Note note)
	{
		
		return (Long)sessionFactory.getCurrentSession().save(note);
		
		
	}
	//update
	public void  update(Note note)
	{
			
	 sessionFactory.getCurrentSession().update(note);
		
		
	}
	
	//PERSÝST

	public void persist(Note note)
	{
		
		// persist, id veritabanýnda varsa update eder yoksa insert yapar.
		
		sessionFactory.getCurrentSession().persist(note);
		
		
	}
	
	//DELETE
	public void delete(Note note)
	{
		
	sessionFactory.getCurrentSession().delete(note);
		
		
	}
	//READ ÝSLEMLERÝ
	
	//singleresult 1 tane obje geleceði icin kullandýk
	public Note getFindById(Long id)
	{Query query=sessionFactory.getCurrentSession().createQuery("FROM Note where id=:id").setLong("id", id);
	return (Note) query.getSingleResult();
		
	}
	
//BURADA BÝR COK OBJE GELECEGI ICIN RESULTLÝST OLARAK DONDURDUK.
	public ArrayList<Note> getAll()
	{
		Query query=sessionFactory.getCurrentSession().createQuery("FROM Note");
		return (ArrayList<Note>) query.getResultList();
		
	}
	
	public ArrayList<Note> getAll(Long user_id)
	{
		Query query=sessionFactory.getCurrentSession().createQuery("FROM Note where user_id=:user_id order by id desc")
				.setLong("user_id", user_id);
		return (ArrayList<Note>) query.getResultList();
		
	}

}
