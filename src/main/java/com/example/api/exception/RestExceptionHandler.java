package com.example.api.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
/*
 * ExceptionHandler
 * Victor Augusto
 */
	public RestExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

	
	/*
	 *  tratar MethodArgumentNotValidException
	 */
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
	                                                              HttpStatus status, WebRequest request) {
		List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
	}
	
	/*
	 * Personalizar response
	 */
	
	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField()))
                .collect(Collectors.toList());
    }
    
    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<Object> handleInvalidCep(InvalidCepException ex){
    	List<ErrorObject> erros = new ArrayList<>();
    	erros.add(new ErrorObject("O cep informado é invalido", "zipCode"));
    	ErrorResponse erroResponse = new ErrorResponse("Ocorreu um erro", 
    			                          HttpStatus.NOT_FOUND.value(),
    			                          HttpStatus.NOT_FOUND.getReasonPhrase(),
    			                          erros
    			                          );
    	return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    
    }
    
    @ExceptionHandler(CostumerNotFoundException.class)
    public ResponseEntity<Object> handleCostumerNotFound(CostumerNotFoundException ex){
    	List<ErrorObject> erros = new ArrayList<>();
    	erros.add(new ErrorObject("O Cliente nao foi encontrado na base de dados!", "Id"));
    	ErrorResponse erroResponse = new ErrorResponse("Ocorreu um erro",
    			                                       HttpStatus.NOT_FOUND.value(),
    			                                       HttpStatus.NOT_FOUND.getReasonPhrase(),
    			                                       erros);
    	return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Object> handleAddressNotFound(AddressNotFoundException ex){
    	List<ErrorObject> erros = new ArrayList<>();
    	erros.add(new ErrorObject("O Endereco nao foi encontrado na base de dados para o cliente informado!", "Id"));
    	ErrorResponse erroResponse = new ErrorResponse("Ocorreu um erro",
    			                                       HttpStatus.NOT_FOUND.value(),
    			                                       HttpStatus.NOT_FOUND.getReasonPhrase(),
    			                                       erros);
    	return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }
}

