package org.wdn.service;

import org.wdn.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    Boolean add(ServiceDto service);
    Boolean update(ServiceDto service);
    Boolean deleteById(Long id);
    Boolean deleteAll();
    Boolean deleteByServiceName(String serviceName);
    ServiceDto searchById(Long id);
    List<ServiceDto> searchByServiceName(String serviceName);
    List<ServiceDto> searchByEmployeeId(Long emplId);
    List<ServiceDto> getAll();
    Long getLastId();
}
