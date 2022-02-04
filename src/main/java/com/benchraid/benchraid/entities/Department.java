package com.benchraid.benchraid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
public class Department extends Entitie{

}
