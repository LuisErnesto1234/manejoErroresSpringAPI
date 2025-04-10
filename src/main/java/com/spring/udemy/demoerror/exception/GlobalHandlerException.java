package com.spring.udemy.demoerror.exception;

import com.spring.udemy.demoerror.exception.dto.CampoErrorDTO;
import com.spring.udemy.demoerror.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidaciones(MethodArgumentNotValidException ex) {

        List<CampoErrorDTO> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new CampoErrorDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDTO error = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                LocalDateTime.now(),
                errores
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDTO> manejoDeRuta(NoHandlerFoundException ex){
        ErrorDTO errorDTO = new ErrorDTO(
          HttpStatus.NOT_FOUND.value(),
          "Ruta no encontrada",
                LocalDateTime.now(),
                List.of(new CampoErrorDTO("ruta", "La URL '" + ex.getRequestURL() + "' no existe")));

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> manejoDeArgumentoInvalido(MethodArgumentTypeMismatchException ex){
        String nombreParametro = ex.getName(); // nombre del parámetro en la URL o query
        String valorIngresado = String.valueOf(ex.getValue()); // lo que intentó ingresar el usuario
        String tipoEsperado = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                "El argumento ingresado es incompatible",
                LocalDateTime.now(),
                List.of(new CampoErrorDTO(nombreParametro, "El valor '" + valorIngresado + "' no es válido para el tipo '" + tipoEsperado + "'")));

    return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    // 🧩 Error genérico (catch-all)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarErroresGenericos(Exception ex) {
        ErrorDTO error = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocurrió un error inesperado",
                LocalDateTime.now(),
                List.of(new CampoErrorDTO("error", ex.getMessage()))
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}