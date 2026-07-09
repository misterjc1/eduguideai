
package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.Animation;
import com.wouti.zoom_academia.entities.QAnimation;

public interface AnimationRepository extends JpaRepository<Animation, Long>, QuerydslPredicateExecutor<Animation>,QuerydslBinderCustomizer<QAnimation> {

	@Override
	default public void customize(QuerydslBindings bindings, QAnimation root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

	@Query("select c from Animation c where c.isDeleted = false")
	public List<Animation> findAllAnimation();

	@Query("select c from Animation c where c.isDeleted = false and c.codeAnimation = ?1")
	public Animation findByCodeAnimation(String codeAnimation);

}
