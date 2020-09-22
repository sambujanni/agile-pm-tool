package com.siva.agilepmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectNotFoundExceptionResponse {
    String projectNotFound;
}
