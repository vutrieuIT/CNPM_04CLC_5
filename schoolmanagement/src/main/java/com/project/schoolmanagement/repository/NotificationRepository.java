package com.project.schoolmanagement.repository;

import com.project.schoolmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
