package org.softuni.exam_prep_sboj.repository;

import org.softuni.exam_prep_sboj.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    
    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(jobApplication);
            this.entityManager.getTransaction().commit();

            return jobApplication;
        } catch (Exception e){
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();
        List<JobApplication> jobApplications = this.entityManager
                .createQuery("SELECT u FROM JobApplication u ", JobApplication.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return jobApplications;
    }

    @Override
    public JobApplication findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            JobApplication jobApplication= this.entityManager
                    .createQuery("SELECT u FROM JobApplication u WHERE u.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return jobApplication;
        } catch (Exception e ){
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public void delete(String id) {
        JobApplication jobApplication = 
                this.findById(id);
        if(jobApplication != null){
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(jobApplication);
            this.entityManager.getTransaction().commit();
        }
    }
}
