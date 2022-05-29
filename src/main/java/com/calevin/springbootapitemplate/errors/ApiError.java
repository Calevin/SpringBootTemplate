package com.calevin.springbootapitemplate.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime date;
    private String message;

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.date = LocalDateTime.now();
        this.message = message;
    }
}
