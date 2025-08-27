package br.com.dluzedesign.wood.dwoodbackend.services;

import br.com.dluzedesign.wood.dwoodbackend.dtos.RegisterDTO;
import br.com.dluzedesign.wood.dwoodbackend.models.User;
import br.com.dluzedesign.wood.dwoodbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(RegisterDTO data){
        User user = new User();
        BeanUtils.copyProperties(data, user);
        user.setPassword(new BCryptPasswordEncoder().encode(data.password()));
        return userRepository.save(user);
    }
    public boolean existByEmail(String email){
        return userRepository.findByEmail(email) != null;
    }
}
