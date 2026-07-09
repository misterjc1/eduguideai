package com.wouti.zoom_academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Session;
import com.wouti.zoom_academia.entities.QSession;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long>, 
    QuerydslPredicateExecutor<Session>, QuerydslBinderCustomizer<QSession> {

    @Override
    default public void customize(QuerydslBindings bindings, QSession root) {
        bindings.bind(String.class).first((StringPath path, String value) -> 
            path.containsIgnoreCase(value));
    }

    @Query("select s from Session s where s.isDeleted = false and s.codeSession = ?1")
    public Session findByCodeSession(String codeSession);
    
    @Query("select s from Session s where s.isDeleted = false and s.utilisateur.codeUtilisateur = ?1")
    public List<Session> findByUser(String codeUtilisateur);
}