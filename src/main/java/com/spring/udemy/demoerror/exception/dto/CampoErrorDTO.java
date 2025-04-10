package com.spring.udemy.demoerror.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CampoErrorDTO {
    private String campo;
    private String mensaje;
}
