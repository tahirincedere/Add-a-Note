package com.tahir.service;

import java.util.ArrayList;


import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tahir.dao.NoteDAO;
import com.tahir.dto.UserLoginDto;
import com.tahir.entity.Note;
import com.tahir.entity.User;
import com.tahir.security.LoginFilter;

@Service
//Burada kullan�ld��� zaman b�t�n operasyonlarda kullan�l�r.
@Transactional
public class NoteService {

	//Autowired ile notedao'yu �a��r�yoruz. g�vdeden bagl�yoruz.
	
	@Autowired
	private UserService userService;
	
@Autowired
private NoteDAO noteDAO;


/*  Buras� business layer dao ile controller aras�nda bir k�pr� ba�� g�r�r.
    Controllerden gelen istekleri daoya iletir kaydeder ekleme yapar vb bir �ok islem yapar daodan gelen islemleri 
    Controllera ekler.*/
	public Long createNote (Note note,HttpServletRequest request)
	{	
		
		note.setUser_id(LoginFilter.user.getId());
	return this.noteDAO.insert(note);
		
	}
	
	public Long  updateNote(Note note,HttpServletRequest request)
	{		
		 this.noteDAO.update(note);
		 return 1L;
		
	}

	public Long deleteNote(Note note,HttpServletRequest request)
	{
		this.noteDAO.delete(note);
		return 1l;
	}
	
	public ArrayList<Note> getAll(Long user_id)
	{
		return this.noteDAO.getAll(user_id);
	}
	
	public ArrayList<Note> getAll()
	{
		return this.noteDAO.getAll();
	}
	
	public ArrayList<Note> getAll(UserLoginDto login)
	{
		User userm=new User();
		userm.setUsername(login.getUsername());
		userm.setPassword(login.getPassword());
		User user=userService.getFindByIdUsernameAndPass(userm);
		return noteDAO.getAll(user.getId());
	}
	
	
	public Note getFindNoteById(Long id)
	{return this.noteDAO.getFindById(id);
		
	}
	


}
