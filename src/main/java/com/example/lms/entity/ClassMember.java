package com.example.lms.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "class_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ClassMember(Classroom classroom, User user) {
        this.classroom = classroom;
        this.user = user;
    }
}
