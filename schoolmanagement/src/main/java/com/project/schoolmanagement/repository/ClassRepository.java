package com.project.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.schoolmanagement.entity.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {

}
