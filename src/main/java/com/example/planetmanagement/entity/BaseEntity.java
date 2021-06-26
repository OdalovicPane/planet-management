package com.example.planetmanagement.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    @CreationTimestamp
    private LocalDateTime create;

    @UpdateTimestamp
    private LocalDateTime version;

}
