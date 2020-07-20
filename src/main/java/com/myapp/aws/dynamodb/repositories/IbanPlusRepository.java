package com.myapp.aws.dynamodb.repositories;

import com.myapp.aws.dynamodb.model.IbanPlus;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface IbanPlusRepository extends CrudRepository<IbanPlus, String> {
    Optional<IbanPlus> findById(String ibanNationalId);
}
