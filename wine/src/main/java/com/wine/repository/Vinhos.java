package com.wine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wine.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

}
