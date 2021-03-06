package com.geogenie.geo.service.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.geogenie.data.model.exception.MessageDTO;
import com.geogenie.data.model.exception.ServiceException;
import com.geogenie.data.model.exception.MessageDTO.MessageType;

@ControllerAdvice
public class ServiceExceptionAdvice {

	@Autowired
	private MessageSource msgSource;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public MessageDTO processServerError(Exception ex) {

		MessageDTO message = null;

		message = new MessageDTO(ex.getMessage(), MessageType.ERROR);

		return message;
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public MessageDTO processServerError(ServiceException ex) {

		MessageDTO message = null;

		message = new MessageDTO("Error occured while performing search", MessageType.ERROR,ex);

		return message;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		FieldError error = result.getFieldError();
		return processFieldError(error);
	}

	private MessageDTO processFieldError(FieldError error) {
		MessageDTO message = null;
		if (error != null) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String msg = msgSource.getMessage(error.getDefaultMessage(), null,
					currentLocale);
			message = new MessageDTO(msg, MessageType.ERROR);
		}
		return message;
	}

}
