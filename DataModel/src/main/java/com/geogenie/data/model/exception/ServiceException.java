package com.geogenie.data.model.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Error Returned from web service
 * @author ajoinwal
 *
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.NONE)
public class ServiceException extends Throwable{

	
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String code;
	@XmlElement
	private String message;
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ServiceException(){
		super();
	}
	
	public ServiceException(String code, String message){
		super(message);
		this.code = code;
		this.message = message;
	}
	
	
}
