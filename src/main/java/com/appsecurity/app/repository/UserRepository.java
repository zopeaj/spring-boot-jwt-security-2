package com.appsecurity.app.repository;

import java.util.Optional;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.appsecurity.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	  Optional<User> findByEmail(String email);
}

//public class UserRepository {
//	@PersistenceContext(unitName="userdbContext")
//	private EntityManager entityManager; 
//	
//	@Transactional
//	public void save(User user) {
//		entityManager.persist(user);
//	}
//	
//	public Optional<User> findByEmail(String username){
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<User> cq = cb.createQuery(User.class);
//		return null;
//	}
//}
