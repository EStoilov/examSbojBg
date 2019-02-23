package org.softuni.exam_prep_sboj.repository;

import org.softuni.exam_prep_sboj.domain.entities.JobApplication;
import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
public interface JobApplicationRepository extends GenericRepository<JobApplication, String>{
    
    void delete(String id);
}
