package org.softuni.exam_prep_sboj.service;

import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.repository.JobApplicationRepository;

import java.util.List;
public interface JobApplicationService {
    boolean addJob(JobApplicationServiceModel jobApplicationServiceModel);
    
    List<JobApplicationServiceModel> getAllJobApplications();
    
    void delete(String id);
    
    JobApplicationServiceModel findById(String id);
}
