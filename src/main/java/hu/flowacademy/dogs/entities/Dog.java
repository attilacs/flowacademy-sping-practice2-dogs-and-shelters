package hu.flowacademy.dogs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
@Data
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String chipId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Shelter shelter;
}
