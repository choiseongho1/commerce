package com.ho.commerce.api.option.repository;

import com.ho.commerce.api.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option,Long> {
}
