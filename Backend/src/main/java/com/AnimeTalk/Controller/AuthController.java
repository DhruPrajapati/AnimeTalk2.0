package com.AnimeTalk.Controller;

import com.AnimeTalk.Config.JwtProvider;
import com.AnimeTalk.Repository.UserRepository;
import com.AnimeTalk.Request.LoginRequest;
import com.AnimeTalk.Response.AuthResponse;
import com.AnimeTalk.ServiceImplementation.CustomUserDetailsService;
import com.AnimeTalk.Service.UserService;
import com.AnimeTalk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetails;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist != null)
            throw new Exception("Email already exist!!");

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Register Success!!");
        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest){

        Authentication authentication =  authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"login Success!!");
        return res;

    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetails.loadUserByUsername(email);

        if(userDetails == null)
            throw new BadCredentialsException("Invalid user or password");

        if(!passwordEncoder.matches(password,userDetails.getPassword()))
            throw new BadCredentialsException("Invalid user or password");

        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }
}
