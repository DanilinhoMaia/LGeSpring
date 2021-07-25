package br.com.lgespring.lgespringproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lgespring.lgespringproject.model.Projeto;

public interface ProjectRepository extends JpaRepository<Projeto, Long> {
	

}
