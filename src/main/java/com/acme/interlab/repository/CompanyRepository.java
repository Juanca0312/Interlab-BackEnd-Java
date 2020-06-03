package com.acme.interlab.repository;

import com.acme.interlab.model.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
