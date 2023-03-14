package com.example.quiz_api_management.answer;

import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

/*
    Notice that JpaRepository extends PagingAndSortingRepository which can also do methods such as pagination and sorting.
    While we just want to do CRUD operations for Answer model so that CrudRepository is simply fair.
 */
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Optional<Answer> findById(int answerId);
}
