package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.common.GenericService;
import com.kz.bookingsystem.dto.ChangePasswordDTO;
import com.kz.bookingsystem.dto.UserDTO;
import com.kz.bookingsystem.dto.projection.UserView;
import com.kz.bookingsystem.entity.User;
import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.repository.UserRepository;
import com.kz.bookingsystem.service.UserService;
//import io.swagger.models.auth.In;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public GenericService<Long> save(UserDTO request) {
        GenericService<Long> result = new GenericService<>();
        try{
            String userId = request.getUserId();

        }
        catch (Exception e){
            throw new CoreApiException("Fail to register", result.getCode(), e.getMessage());
        }
        return result;
    }

    @Override
    public GenericService<UserDTO> getUserByID(Integer id) {
        GenericService<UserDTO>  result = new GenericService<>();
        try {
            var user = repository.findById(id);
            if(user.isPresent()) {
                UserDTO userData = convertEntityToDTO(user.get());
                result.success(userData);
            }
            else {
                result.success(null);
            }
        }
        catch (CoreApiException ex) {
            throw new CoreApiException(ex.getTitle(), ex.getCode(), ex.getMessage());
        }
        return result;
    }

    @Override
    public GenericService<List<UserDTO>> getAllUsers() {
        GenericService<List<UserDTO>> result = new GenericService<>();
        try {
            List<User> users = repository.findAll();
            List<UserDTO> userData = new ArrayList<>();

            users.forEach(item -> {
                UserDTO dto = convertEntityToDTO(item);
                userData.add(dto);
            });
            result.success(userData);
        }
        catch (Exception ex) {
            result.fail(ex, ex.getMessage());
        }
        return result;
    }

    @Override
    public GenericService<Integer> updaetPassword(ChangePasswordDTO request) {
        GenericService<Integer> result = new GenericService<>();
        try{
            var user = repository.findById(request.getId());
            User userData = user.get();
            boolean isPasswordMatch = passwordEncoder.matches(request.getOldPassword(), userData.getPassword());
            if(!isPasswordMatch) {
                result.fail(new Throwable(),"Wrong Password");
                throw new CoreApiException("Fail to update Password", result.getCode(), "Wrong Password.");
            }
            userData.setPassword(passwordEncoder.encode(request.getNewPassword()));
            repository.save(userData);
            result.success(userData.getId());
        }
        catch (Exception ex){
            result.fail(ex, ex.getMessage());
        }
        return result;
    }

    @Override
    public Object findUser(String name, String email, String countryName, Pageable pageable) {
        GenericService<Page<UserView>> result = new GenericService<>();

        try {
            Page<UserView> users = repository.findUser(name, email, countryName, pageable);

            result.setStatus(Boolean.TRUE);
            result.success(users);
        } catch (Exception e) {
            throw new CoreApiException("Fail to retrieve users", result.getCode(), result.getMessage());
        }

        return result;
    }

    @Override
    public GenericService<Integer> deleteById(Integer id) {
        GenericService<Integer> result = new GenericService<>();
        try {
            repository.deleteById(id);
            result.success(id);
        }
        catch (Exception e) {
            throw new CoreApiException("Fail to delete user", "500", e.getMessage());
        }
        return null;
    }

    private UserDTO convertEntityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .userId(user.getUserid())
                .userName(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
