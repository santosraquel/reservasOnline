package com.reservaonline.repositorios;

import com.reservaonline.modelos.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

}
