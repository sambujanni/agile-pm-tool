package com.siva.agilepmtool.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MapValidationErrors {

    public ResponseEntity<Map<String, String>> mapErrors(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> map = result.getFieldErrors().stream()
                    .collect(Collectors
                            .toMap(entry -> entry.getField(), entry -> entry.getDefaultMessage()));
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
