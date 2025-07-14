package com.example.lms.repository;

import com.example.lms.entity.ClassMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassMemberRepository extends JpaRepository<ClassMember, Long> {
    List<ClassMember> findByClassroomId(Long classroomId);
}
