package org.sid.dao;

import java.util.List;

import org.sid.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
//@RepositoryRestResource
@CrossOrigin("*")
public interface productRepesotory extends JpaRepository<Product, Long> {
			@Override
			List<Product> findAll();

			
			@Query("select p from Product p  where p.category.id=:id")
			public List<Product> getListProductByCategorie(@Param("id") Long id);
			
			@Query("select p from Product p  where p.promotion = true")
			public List<Product> getListProductPromotion();

			@Query("select p from Product p  where p.availabe = true")
			public List<Product> getListProductDisponible();


	/*
	 * @RestResource(path ="selectedProducts") public List<Product>
	 * findBySelectIsTrue();
	 * 
	 * @RestResource(path ="productsByKeyword") public List<Product>
	 * findByNameContains(@Param("mc") String mc );
	 */
	

}
