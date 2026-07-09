package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Notification;
import com.wouti.zoom_academia.entities.QNotification;


public interface NotificationRepository extends JpaRepository<Notification, Long>, QuerydslPredicateExecutor<Notification>,QuerydslBinderCustomizer<QNotification>{

	  @Override
		default public void customize(QuerydslBindings bindings, QNotification root) {
			bindings.bind(String.class).first((StringPath path, String value)->path.containsIgnoreCase(value));
		}

		@Query("select s from Notification s where s.isDeleted = false and s.codeNotification = ?1")
		public Notification findByCodeNotification(String codeNotification);


		


	}

