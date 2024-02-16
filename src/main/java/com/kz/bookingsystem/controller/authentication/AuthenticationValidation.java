package com.kz.bookingsystem.controller.authentication;


import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.exception.FieldError;
import com.kz.bookingsystem.exception.IntegrationException;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationValidation implements AuthencationConstant {

//	private final AuthenticationManager authenticationManager;
//
//	public AuthenticationValidation(AuthenticationManager authenticationManager) {
//		this.authenticationManager = authenticationManager;
//	}
//
//
//	public void authenticate(String username, String password) {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException ex) {
//			throw new DisabledException(ex.getMessage());
//		} catch (BadCredentialsException ex) {
//			throw new BadCredentialsException(ex.getMessage());
//		} catch (InternalAuthenticationServiceException ex) {
//			throw new InternalAuthenticationServiceException(ex.getMessage());
//		}catch (Exception ex){
//			throw new CoreApiException("Authentication Fail","401","cannot authenticate");
//		}
//	}
//
//	public void validateRequest(String username, String password) {
//		if (username == null && password == null) {
//			String message = FieldError.ErrorMessage.USER_PASSWROD_REQUIRED.getMessage();
//			String code = FieldError.FieldCode.USER_PASSWROD_REQUIRED.getCode();
//			throw new IntegrationException(WRONG_AUTHENCATION, message, code);
//		}
//		if (username == null) {
//			String message = FieldError.ErrorMessage.USER_NAME_REQUIRED.getMessage();
//			String code = FieldError.FieldCode.USER_NAME_REQUIRED.getCode();
//			throw new IntegrationException(WRONG_AUTHENCATION, message, code);
//		}
//		if (password == null) {
//			String message = FieldError.ErrorMessage.RQ_PASSWORD_REQUIRED.getMessage();
//			String code = FieldError.FieldCode.RQ_PASSWORD_REQUIRED.getCode();
//			throw new IntegrationException(WRONG_AUTHENCATION, message, code);
//		}
//
//	}
}
