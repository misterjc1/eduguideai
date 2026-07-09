package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.MessageModel;
import com.wouti.zoom_academia.entities.QMessageModel;
import com.wouti.zoom_academia.transverse.TypeMessage;

public interface MessageModelRepository extends JpaRepository<MessageModel, Long>, QuerydslPredicateExecutor<MessageModel>,QuerydslBinderCustomizer<QMessageModel> {

	@Override
	 default public void customize(QuerydslBindings bindings, QMessageModel root) {
			bindings.bind(String.class).first(
					(StringPath path, String value) -> path.containsIgnoreCase(value));
		}
	@Override
	@Query("select m from MessageModel m where m.isDeleted = false")
	public List<MessageModel> findAll();

	@Query("select m from MessageModel m where m.isDeleted = false and m.codeMessageModel = ?1")
	public MessageModel findByCodeMessageModel(String codeMessageModel);

	@Query("select n from MessageModel n where n.isDeleted=false and n.type = ?1")
	public MessageModel findMessageModelByType(TypeMessage type);

}
