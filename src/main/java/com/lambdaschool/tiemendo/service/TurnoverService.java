package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Turnover;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface TurnoverService {
    ArrayList<Turnover> findAll(Pageable p);
    ArrayList<Turnover> findAllByRetailer(long id);
    Turnover findById(long id);
    Turnover add(Turnover turnover);
    Turnover update(Turnover turnover);
    Turnover delete(long id);
}
