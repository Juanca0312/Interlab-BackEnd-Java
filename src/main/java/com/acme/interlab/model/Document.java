package com.acme.interlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Document extends AuditModel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotNull
@NotBlank
@Size(max = 20)
@Column(unique = true)
private String name;

@NotNull
@NotBlank
@Lob
private String description;

@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "user_id", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
private User user;

}
