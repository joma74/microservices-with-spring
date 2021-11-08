package com.eazybytes.microserviceswithspring.accounts.repository;

import com.eazybytes.microserviceswithspring.accounts.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    Accounts findByCustomerId(int cutsomerId);
}
