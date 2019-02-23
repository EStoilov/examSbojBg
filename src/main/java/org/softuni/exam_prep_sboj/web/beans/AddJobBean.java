package org.softuni.exam_prep_sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam_prep_sboj.domain.models.binding.JobApplicationAddJobBindingModel;
import org.softuni.exam_prep_sboj.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam_prep_sboj.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class AddJobBean {
    private JobApplicationAddJobBindingModel jobApplicationAddJobBindingModel;
    
    private  JobApplicationService jobApplicationService;
    private  ModelMapper modelMapper;

    public AddJobBean(){}

    @Inject
    public AddJobBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.jobApplicationAddJobBindingModel = new JobApplicationAddJobBindingModel();
    }

    public JobApplicationAddJobBindingModel getJobApplicationAddJobBindingModel() {
        return jobApplicationAddJobBindingModel;
    }

    public void setJobApplicationAddJobBindingModel(JobApplicationAddJobBindingModel jobApplicationAddJobBindingModel) {
        this.jobApplicationAddJobBindingModel = jobApplicationAddJobBindingModel;
    }
    
    public void registerJob() throws IOException {
        if(!this.jobApplicationService
                .addJob(this.modelMapper.map(jobApplicationAddJobBindingModel,JobApplicationServiceModel.class))){
            throw new IllegalArgumentException("Something went wrong!");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
