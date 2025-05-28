package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.PersonalAdministrativo;

@Repository
public interface PersonalAdministrativoRepository extends JpaRepository<PersonalAdministrativo, Long>{

}
