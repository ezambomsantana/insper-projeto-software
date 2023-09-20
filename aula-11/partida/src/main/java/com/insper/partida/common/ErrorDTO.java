package com.insper.partida.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDTO {
    private String message;
    private Integer code;
    private LocalDateTime time;
}
