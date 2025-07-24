package com.pknu.example.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Member {
    @Id
    @Column(name="mem_id")
    private String mem_id;

    @Column(nullable=false)
    private String mem_pass;
    private String mem_name;
    private String mem_regno1;
    private String mem_regno2;

    private LocalDate mem_bir;

    private String mem_zip;
    private String mem_add1;
    private String mem_add2;
    private String mem_hometel;
    private String mem_comtel;
    private String mem_hp;
    private String mem_mail;
    private String mem_job;
    private String mem_like;
    private String mem_memorial;

    private LocalDate mem_memorialday;

    private int mem_mileage;
    private String mem_delete;
}
