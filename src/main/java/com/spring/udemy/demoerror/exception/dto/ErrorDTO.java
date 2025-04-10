package com.spring.udemy.demoerror.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorDTO {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    private List<CampoErrorDTO> errores;
}
