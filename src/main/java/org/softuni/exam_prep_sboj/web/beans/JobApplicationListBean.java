package org.softuni.exam_prep_sboj.web.beans;

import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
@Named
@RequestScoped
public class JobApplicationListBean {
    
    private List<JobApplicationServiceModel> jobApplications;
    
    private JobApplicationService jobApplicationService;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
        this.initJobApplications();
    }

    private void initJobApplications() {
        this.jobApplications = this.jobApplicationService.
                getAllJobApplications();
        this.jobApplications
                .forEach(j-> j.setSector(j.getSector().toString().toLowerCase()));
    }

    public List<JobApplicationServiceModel> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplicationServiceModel> jobApplications) {
        this.jobApplications = jobApplications;
    }
}
