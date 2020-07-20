package com.myapp.aws.dynamodb.repositories;

import com.myapp.aws.dynamodb.model.IbanStructure;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface IbanStructureRepository extends CrudRepository<IbanStructure, String> {
    Optional<IbanStructure> findById(String id);
}
