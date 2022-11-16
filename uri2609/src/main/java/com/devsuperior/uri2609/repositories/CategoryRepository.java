package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(nativeQuery = true, value = "SELECT categories.name, SUM(products.amount) "
			+" AS sum FROM products "
			+ "INNER JOIN categories "
			+ "ON products.id_categories = categories.id "
			+ "GROUP BY categories.name")
	List<CategorySumProjection> search1();
	
	@Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount) as sum) "
			+" FROM Product obj "
			+ "GROUP BY obj.category.name")
	List<CategorySumDTO> search2();
}
