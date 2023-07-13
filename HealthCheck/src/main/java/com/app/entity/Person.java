package com.app.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double height;
    private double weight;
   @Value(value = "${my.property:default-value}")
    
    private double bmi;

    // Constructors, getters, and setters
    
    public double calculateBMI() {
        double heightInMeters = height / 100; // Convert height to meters
        return weight / (heightInMeters * heightInMeters); // BMI formula: weight(kg) / height(m^2)
    }
    // ...
}
