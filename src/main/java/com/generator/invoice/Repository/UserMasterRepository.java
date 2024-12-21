package com.generator.invoice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generator.invoice.Entities.UserMaster;
import java.util.Optional;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

	Optional<UserMaster> findByEmail(String username);
	
}