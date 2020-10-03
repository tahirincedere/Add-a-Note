package com.tahir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tahir.notalma.HomeController;
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void registerMail(String mail,String key)
	{
		SimpleMailMessage email=new SimpleMailMessage();
		email.setFrom("tahirincedere@gmail.com");
		email.setTo(mail);
		email.setSubject("�yeli�i tamamla");
		email.setText("�yeli�i tamamlamak i�in a�a��daki linke t�klay�n�z.\n\n"
				
				+HomeController.url+"/reg/"+key);
		mailSender.send(email);
		
	}
}
