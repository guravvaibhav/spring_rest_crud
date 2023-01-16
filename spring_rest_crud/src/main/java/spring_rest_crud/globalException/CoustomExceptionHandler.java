package spring_rest_crud.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CoustomExceptionHandler extends ResponseEntityExceptionHandler{
	private String INCORRECT_REQUEST = "INCORRECT EMPLOYEE ID";
	private String INSUFFICIENT_DETAILS = "INSUFFICIENT_DETAILS";
	private String SERVER_UNDER_MAINTANANCE="Server under maintainance.. please visit after some time..";
	
	
	
	@ExceptionHandler(EmployeeNotAvailableException.class)
	public final ResponseEntity<ErrorResponce> handleEmployeeNotPresentException(EmployeeNotAvailableException ex){
		ErrorResponce error=new ErrorResponce();
		error.setMsg(INCORRECT_REQUEST);
		error.setDetails(ex.getLocalizedMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InSufficientDetailException.class)
	public final ResponseEntity<ErrorResponce> handleinsufficientDetailsException(InSufficientDetailException isd,WebRequest reques){
		ErrorResponce error=new ErrorResponce();
		error.setMsg(INSUFFICIENT_DETAILS);
		error.setDetails(isd.getLocalizedMessage());
	
		return new ResponseEntity<ErrorResponce>(error,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponce>handleGeneralException(Exception e){
		ErrorResponce error=new ErrorResponce();
		error.setMsg(SERVER_UNDER_MAINTANANCE);
		error.setDetails(e.getLocalizedMessage());
		return new ResponseEntity<ErrorResponce>(error,HttpStatus.SERVICE_UNAVAILABLE);
		
	}

}
