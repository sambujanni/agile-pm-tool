package com.siva.agilepmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotBlank(message = "Project Name is Required")
    private String projectName;
    @NotBlank(message = "Project Identifier is Required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 Chars")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Description is Required")
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}
