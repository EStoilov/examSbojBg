package org.softuni.exam_prep_sboj.web.beans;

import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobApplicationDeleteBean {
    private JobApplicationService jobApplicationService;
    
    private JobApplicationServiceModel jobAppModel;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
        this.initModel();
    }

    private void initModel() { String id = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap().get("id");
    
        this.jobAppModel =  this.jobApplicationService
                .findById(id);
    }

    public void deleteJobApplication() throws IOException {
       this.jobApplicationService.delete(jobAppModel.getId());

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/home");
    }

    public JobApplicationServiceModel getJobAppModel() {
        return jobAppModel;
    }

    public void setJobAppModel(JobApplicationServiceModel jobAppModel) {
        this.jobAppModel = jobAppModel;
    }
}
