package com.tahir.notalma;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tahir.entity.Note;
import com.tahir.security.LoginFilter;
import com.tahir.service.MailService;
import com.tahir.service.NoteService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	public static String url="http://localhost:8080/notalma";
	
	@Autowired
	private NoteService noteService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home2( Model model) {
		
		
		
		
		
		
		
		
		
		return "redirect:/index";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home( Model model) {
		
		
		
		
		
		
		
		
		
		return "redirect:/index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model,HttpServletRequest req) {
		
		model.addAttribute("baslik","Not Alma");
		model.addAttribute("notlar", noteService.getAll(1l));
		model.addAttribute("user",req.getSession().getAttribute("user"));
		
		
		
		
		
		
		
		return "index";
	}
	@RequestMapping(value = "/error_404", method = RequestMethod.GET)
	public String error( Model model) {
		
		
		
		
		
		
		return "error_404";
	}
	@RequestMapping(value = "/detay/{id}", method = RequestMethod.GET)
	public String detay(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("id", id);
		
		
		
		
		
		return "detail";
	}
	
	@RequestMapping(value = "/ekle", method = RequestMethod.GET)
	public String ekle( Model model) {
		
		
		
		
		
		
		return "addNote";
	}
	
	
	@RequestMapping(value = "/addNote",method = RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note,HttpServletRequest request)
	{

		System.out.println(note.toString());
		noteService.createNote(note,request);
		return new ResponseEntity<String>("OK",HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value ="/getNotes", method = RequestMethod.POST )
	public ResponseEntity<ArrayList<Note>> getNotes(HttpServletRequest request)
	{
		
		return new ResponseEntity<>(noteService.getAll(LoginFilter.user.getId()),HttpStatus.CREATED);
		
		
		
		
	}

	@RequestMapping(value ="/getNote", method = RequestMethod.POST)
	public ResponseEntity<Note> getNote(@RequestBody String id,HttpServletRequest request)
	{
		
		Note note=noteService.getFindNoteById(Long.parseLong(id));
		if(note.getId().equals(LoginFilter.user.getId()))
		return new ResponseEntity<>(noteService.getFindNoteById(Long.parseLong(id)),HttpStatus.CREATED);
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
		
			
		
		
	}
	
	@RequestMapping(value = "/updateNote",method = RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note,HttpServletRequest request)
	{
            Note oldNote=noteService.getFindNoteById(note.getId());
            oldNote.setTittle(note.getTittle());
            oldNote.setContent(note.getContent());
           
		
		noteService.updateNote(oldNote, request);
		return new ResponseEntity<String>("OK",HttpStatus.CREATED);
		
	}
	@RequestMapping(value = "/deleteNote",method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note,HttpServletRequest request)
	{
            Note oldNote=noteService.getFindNoteById(note.getId());
         
		
		noteService.deleteNote(oldNote, request);
		return new ResponseEntity<String>("OK",HttpStatus.CREATED);
		
	}
	
	
	
	
}


