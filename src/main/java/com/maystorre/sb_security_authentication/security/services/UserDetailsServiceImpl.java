package com.maystorre.sb_security_authentication.security.services;


import com.maystorre.sb_security_authentication.model.entity.User;
import com.maystorre.sb_security_authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { //UserDetailsService içinde 1 adet aşağıdaki func var başka bişey yok.
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email) //user'i ne ile bulmak istiyosan buraya onu yazıyosun. findByXx şeklinde. Aama üstteki loadUserByUsername demen şart.
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }


}