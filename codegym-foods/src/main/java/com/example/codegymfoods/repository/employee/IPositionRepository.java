package com.example.codegymfoods.repository.employee;

import com.example.codegymfoods.model.employee.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPositionRepository extends JpaRepository<Position, Integer> {
    List<Position> findAll();
}
