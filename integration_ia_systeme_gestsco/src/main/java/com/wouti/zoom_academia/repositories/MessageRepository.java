package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Message;
import com.wouti.zoom_academia.entities.QMessage;
import com.wouti.zoom_academia.transverse.EtatMessage;
import com.wouti.zoom_academia.transverse.TypeMessage;

public interface MessageRepository extends JpaRepository<Message, Long>, QuerydslPredicateExecutor<Message>,QuerydslBinderCustomizer<QMessage> {
	@Override
	 default public void customize(QuerydslBindings bindings, QMessage root) {
			bindings.bind(String.class).first(
					(StringPath path, String value) -> path.containsIgnoreCase(value));
		}
	@Override
	@Query("select m from Message m where m.isDeleted = false")
	public List<Message> findAll();

	@Query("select m from Message m where m.isDeleted = false and m.codeMessage = ?1")
	public Message findByCodeMessage(String codeMessage);

	@Query("select n from Message n where n.isDeleted=false and n.type = ?1")
	public List<Message> findMessageByType(TypeMessage type);

	@Query("select n from Message n where n.isDeleted=false and n.etat = ?1")
	public List<Message> findMessageByEtat(EtatMessage etat);
}
