package com.lucasJanning.ficticiusclean.repository;


import com.lucasJanning.ficticiusclean.model.VeiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculoCombustivelRepository extends JpaRepository<VeiculoModel, Long> {
    @Override
    <S extends VeiculoModel> S save(S entity);

    @Override
    <S extends VeiculoModel> List<S> saveAll(Iterable<S> entities);

    @Override
    List<VeiculoModel> findAll();
}
