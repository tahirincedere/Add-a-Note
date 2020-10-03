package com.tahir.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.dto.UserLoginDto;
import com.tahir.entity.Note;
import com.tahir.service.NoteService;
/*RestController burasýsýnýn bir rest api olduðunu belirtiyoruz.
Web servisleridir * HomeControllerdan bir farký yoktur.*/

@RestController
@RequestMapping(value = "/rest")

public class NoteEndPoint {
	
	@Autowired
	private NoteService noteService;

	
	@RequestMapping(value = "/getAll",method =RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(@RequestBody UserLoginDto login)
	{
		return new ResponseEntity(noteService.getAll(login),HttpStatus.OK);
		
		
	}
}
