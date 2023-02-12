package com.elitexplorer.backend.toconly.repository;

import com.elitexplorer.backend.toconly.model.entity.TocOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TocOnlyRepository extends JpaRepository<TocOnly,Integer> {
}
