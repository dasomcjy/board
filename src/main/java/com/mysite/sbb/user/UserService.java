package com.mysite.sbb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public boolean isUserIdExists(String userid) {
    	return userRepository.existsByuserid(userid);
    }

    public SiteUser create( String userid, String username, String email, String password, String companyname, String phonenumber) {
        SiteUser user = new SiteUser();
        user.setUserid(userid);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setCompanyname(companyname);
        user.setPhonenumber(phonenumber);
        this.userRepository.save(user);
        return user;
    }
    
    public SiteUser getUser(String userid) {
        Optional<SiteUser> siteUser = this.userRepository.findByuserid(userid);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}


