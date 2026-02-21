package com.incident.platform.ingestionservice.Repository;

import com.incident.platform.ingestionservice.Entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mednj
 **/
public interface LogRepository extends JpaRepository<LogEntity, Long> {


}
