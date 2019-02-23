package org.softuni.exam_prep_sboj.repository;

import org.softuni.exam_prep_sboj.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username);
}
