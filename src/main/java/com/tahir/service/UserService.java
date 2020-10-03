package com.tahir.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tahir.entity.User;
import com.tahir.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MailService mailService;

	public Long insert(User user) {
		String uuid = UUID.randomUUID().toString();
		user.setKeyreg(uuid);
		if (this.userDao.insert(user) > 0) {
			mailService.registerMail(user.getEmail(), user.getKeyreg());
		}
		return 1l;

	}

	public void update(User user) {

		this.userDao.update(user);

	}

	public User getFindByIdUsernameAndPass(User user) {
		return userDao.getFindByIdUsernameAndPass(user.getUsername(),user.getPassword());

	}

	public User getFindByIdUsername(String username) {
		return this.userDao.getFindByIdUsername(username);

	}

	public boolean getFindByKey(String key) {
		User user=this.userDao.getFindByKey(key);
		if (user!= null) {
			user.setActive(true);
			update(user);
			return true;
		} else {
			return false;
		}
	}

}
