package com.siva.agilepmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String projectSequence;
    @NotBlank(message = "Please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    //ManyToOne with Backlog
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Backlog backlog;

    @Column(updatable = false)
    private String projectIdentifier;
    private Date create_At;
    private Date update_At;

    @PrePersist
    protected void onCreate() {
        this.create_At = new Date();
    }
    @PreUpdate
    protected  void onUpdate() {
        this.update_At = new Date();
    }

}
