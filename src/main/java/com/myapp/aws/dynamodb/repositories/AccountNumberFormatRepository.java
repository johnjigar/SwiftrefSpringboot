package com.myapp.aws.dynamodb.repositories;

import com.myapp.aws.dynamodb.model.AccountNumberFormat;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface AccountNumberFormatRepository extends CrudRepository<AccountNumberFormat, String> {
    Optional<AccountNumberFormat> findById(String id);
}
