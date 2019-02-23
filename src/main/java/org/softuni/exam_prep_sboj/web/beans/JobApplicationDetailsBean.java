package org.softuni.exam_prep_sboj.web.beans;

import jdk.jfr.Name;
import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobApplicationDetailsBean {
    
    private JobApplicationService jobApplicationService;

    private JobApplicationServiceModel jobAppModel;

    public JobApplicationDetailsBean() {
    }

    @Inject
    public JobApplicationDetailsBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
        this.initModel();
    }

    private void initModel() { String id = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap().get("id");

        this.jobAppModel =  this.jobApplicationService
                .findById(id);
    }

    public JobApplicationServiceModel getJobAppModel() {
        return jobAppModel;
    }

    public void setJobAppModel(JobApplicationServiceModel jobAppModel) {
        this.jobAppModel = jobAppModel;
    }
}
