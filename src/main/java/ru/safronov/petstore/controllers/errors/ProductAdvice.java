package ru.safronov.petstore.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import ru.safronov.petstore.services.exeptions.ArgumentNotValidException;
import ru.safronov.petstore.services.exeptions.CartNotFoundException;
import ru.safronov.petstore.services.exeptions.ProductNotFoundException;

@ControllerAdvice
public class ProductAdvice {

    @ExceptionHandler
    public ResponseEntity<Problem> productNotFoundHande(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Problem.builder()
                        .withType(Problem.DEFAULT_TYPE)
                        .withTitle(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<Problem> cartNotFoundHande(CartNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Problem.builder()
                        .withType(Problem.DEFAULT_TYPE)
                        .withTitle(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<Problem> argumentNotValidHande(ArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Problem.builder()
                        .withType(Problem.DEFAULT_TYPE)
                        .withTitle(e.getMessage())
                        .build()
                );
    }
}
