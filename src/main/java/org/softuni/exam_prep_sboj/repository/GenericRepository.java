package org.softuni.exam_prep_sboj.repository;

import java.util.List;
public interface GenericRepository <ENTITY, ID>{
    
    ENTITY save (ENTITY entity);
    
    List<ENTITY> findAll();
    
    ENTITY findById(ID id);
}
