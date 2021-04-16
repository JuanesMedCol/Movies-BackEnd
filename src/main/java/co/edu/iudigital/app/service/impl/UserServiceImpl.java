package co.edu.iudigital.app.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.iudigital.app.model.entity.User;
import co.edu.iudigital.app.model.repository.UserRepository;
import co.edu.iudigital.app.service.iface.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void create(User user) {
			user.setCreateAt(LocalDateTime.now());
			userRepository.save(user);
		}
	
	@Override
	public User login(User user) throws Exception {
		User existsUser = userRepository
				.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(existsUser == null) {
			throw new Exception("Usuario y/o contraseña invalido!");
		}
		return existsUser;
	}
	
	@Override
	public void edit(User user, int userId) {
		Optional<User> existsUser = userRepository.findById(userId);
		if(existsUser.isPresent()) {
			existsUser.get().setName(user.getName());
			existsUser.get().setEmail(user.getEmail());
			existsUser.get().setPassword(user.getPassword());
			existsUser.get().setProfileId(user.getProfileId());
			}
			userRepository.save(existsUser.get());
		}

}

