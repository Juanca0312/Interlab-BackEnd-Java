package com.acme.interlab.repository;

import com.acme.interlab.model.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
