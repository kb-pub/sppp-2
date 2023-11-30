package app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError(){
        return ResponseEntity.of( Optional.of( "Denied!" ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unhandled(Exception e){
        return ResponseEntity.of( Optional.of( "Error: " + e.getMessage() ));
    }
}
