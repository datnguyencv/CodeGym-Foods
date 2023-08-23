package com.example.codegymfoods.service.employee.impl;

import com.example.codegymfoods.model.employee.Position;
import com.example.codegymfoods.repository.employee.IPositionRepository;
import com.example.codegymfoods.service.employee.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;
    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
