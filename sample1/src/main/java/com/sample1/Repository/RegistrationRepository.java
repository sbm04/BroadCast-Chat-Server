package com.sample1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample1.model.RegistrationModel;

public interface RegistrationRepository extends JpaRepository<RegistrationModel, Integer> {

	@Query("select a from RegistrationModel a where a.mobilenumber=:mobilenumber and a.password=:password")
	public RegistrationModel findbymobilenumber(@Param("mobilenumber") String mobilenumber,@Param("password") String password);
	
	@Query("select a from RegistrationModel a where a.mobilenumber=:mobilenumber")
	public RegistrationModel UserExistorNot(@Param("mobilenumber") String reciverId);
}
