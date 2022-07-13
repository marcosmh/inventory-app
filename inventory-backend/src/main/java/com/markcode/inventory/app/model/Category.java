package com.markcode.inventory.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Category")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_CATEGORY", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;



}
