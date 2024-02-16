package com.kz.bookingsystem.controller.user;

import com.kz.bookingsystem.dto.ChangePasswordDTO;
import com.kz.bookingsystem.dto.UserDTO;
import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.handler.ResponseSuccessHandler;
import com.kz.bookingsystem.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

//@Api(tags = "User", value = "User Api")
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") Integer id) {
        var res = new ResponseSuccessHandler();
        var result = userService.getUserByID(id);
        res.setBody(result.getData());
        return res.response();
    }

    @GetMapping("/")
    public ResponseEntity<String> getAllUser() {
        var res = new ResponseSuccessHandler();
        var users = userService.getAllUsers();
        res.setBody(users);
        return res.response();
    }

    @GetMapping("/getwithpager")
    public ResponseEntity<String> findUser(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "countryName", required = false) String countryName,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ){
        var responseHandler = new ResponseSuccessHandler();
        name = Optional.ofNullable(name).orElse("");
        email = Optional.ofNullable(email).orElse("");
        countryName = Optional.ofNullable(countryName).orElse("");

        var result = userService.findUser(
                name, email, countryName,
                PageRequest.of(offset > 0 ? offset -1 : 0, limit, Sort.by("id").descending())
            );

        responseHandler.setBody(result);
        return responseHandler.response();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        var responseHandler = new ResponseSuccessHandler();
        var message = userService.deleteById(id);
        responseHandler.setBody(message);
        return responseHandler.response();
    }

    @PostMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO request) {
        var res = new ResponseSuccessHandler();
        var result = userService.updaetPassword(request);
        res.setBody(result.getData());
        return res.response();
    }

}
