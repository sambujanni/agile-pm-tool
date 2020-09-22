package com.siva.agilepmtool.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MapValidationErrors {

    public ResponseEntity<Map<String, String>> mapErrors(BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()) {
            Map<String, String> map = result.getFieldErrors().stream()
                    .collect(Collectors
                            .toMap(res -> res.getField(), res -> res.getDefaultMessage(),
                                    (a1, a2) -> a1));
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
