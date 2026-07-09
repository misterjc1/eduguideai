package com.wouti.zoom_academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;
import com.wouti.zoom_academia.entities.QZoneCouverture;
import com.wouti.zoom_academia.entities.ZoneCouverture;


public interface ZoneCouvertureRepository extends JpaRepository<ZoneCouverture, Long>, QuerydslPredicateExecutor<ZoneCouverture>,QuerydslBinderCustomizer<QZoneCouverture>  {

	@Override
	default public void customize(QuerydslBindings bindings, QZoneCouverture root) {
		bindings.bind(String.class).first(
				(StringPath path, String value) -> path.containsIgnoreCase(value));
	}

    @Override
	@Query("select p from ZoneCouverture p where p.isDeleted = false")
	public List<ZoneCouverture> findAll();

    @Query("select p from ZoneCouverture p where p.isDeleted = false and p.codeZoneCouverture= ?1")
	public ZoneCouverture findByCode(String code);
}
