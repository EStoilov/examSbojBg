package org.softuni.exam_prep_sboj.service;

import org.modelmapper.ModelMapper;
import org.softuni.exam_prep_sboj.domain.entities.JobApplication;
import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {
    
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addJob(JobApplicationServiceModel jobApplicationServiceModel) {
        JobApplication jobApplication =this.jobApplicationRepository
                .save(this.modelMapper.map(jobApplicationServiceModel, JobApplication.class));
        return jobApplication != null;
    }

    @Override
    public List<JobApplicationServiceModel> getAllJobApplications() {
        return this.jobApplicationRepository.findAll()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }

    @Override
    public JobApplicationServiceModel findById(String id) {
        return this.modelMapper.map(this.jobApplicationRepository.findById(id), JobApplicationServiceModel.class);
    }
}
