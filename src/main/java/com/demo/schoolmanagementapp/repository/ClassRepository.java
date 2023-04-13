package com.demo.schoolmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.schoolmanagementapp.entity.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {

}
