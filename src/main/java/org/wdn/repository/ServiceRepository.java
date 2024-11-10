package org.wdn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdn.entity.ServiceEntity;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
    List<ServiceEntity> findAllByServiceName(String serviceName);
    List<ServiceEntity> findAllByEmployeeId(Long employeeId);
    void deleteByServiceName(String serviceName);
}
