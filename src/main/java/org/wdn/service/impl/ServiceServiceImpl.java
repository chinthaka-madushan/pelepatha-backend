package org.wdn.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wdn.dto.ServiceDto;
import org.wdn.entity.ServiceEntity;
import org.wdn.entity.UserEntity;
import org.wdn.repository.ServiceRepository;
import org.wdn.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    final ServiceRepository serviceRepository;
    final ModelMapper modelMapper;
    @Override
    public Boolean add(@Valid ServiceDto service) {
        try {
            serviceRepository.save(modelMapper.map(service, ServiceEntity.class));
            return true;
        }catch (Exception e){
            log.error("Error add user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(@Valid ServiceDto service) {
        try {
            serviceRepository.save(modelMapper.map(service, ServiceEntity.class));
            return true;
        }catch (Exception e){
            log.error("Error update user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            serviceRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("Error delete user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteAll() {
        try {
            serviceRepository.deleteAll();
            return true;
        }catch (Exception e){
            log.error("Error delete all user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteByServiceName(String serviceName) {
        try {
            serviceRepository.deleteByServiceName(serviceName);
            return true;
        }catch (Exception e){
            log.error("Error delete user :( => "+e.getMessage());
            return false;
        }
    }

    @Override
    public ServiceDto searchById(Long id) {
        try {
            Optional<ServiceEntity> byId = serviceRepository.findById(id);
            return modelMapper.map(byId, ServiceDto.class);
        }catch (Exception e){
            log.error("Error search user :( => "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ServiceDto> searchByServiceName(String serviceName) {
        try {
            List<ServiceDto> serList = new ArrayList<>();
            List<ServiceEntity> allByServiceName = serviceRepository.findAllByServiceName(serviceName);
            allByServiceName.forEach(ser ->{
                serList.add(modelMapper.map(ser, ServiceDto.class));
            });
            return serList;
        }catch (Exception e){
            log.error("Error search user :( => "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ServiceDto> searchByEmployeeId(Long emplId) {
        try {
            List<ServiceDto> serList = new ArrayList<>();
            List<ServiceEntity> allByServiceName = serviceRepository.findAllByEmployeeId(emplId);
            allByServiceName.forEach(ser ->{
                serList.add(modelMapper.map(ser, ServiceDto.class));
            });
            return serList;
        }catch (Exception e){
            log.error("Error search user :( => "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ServiceDto> getAll() {
        try {
            List<ServiceDto> serList = new ArrayList<>();
            List<ServiceEntity> allByServiceName = serviceRepository.findAll();
            allByServiceName.forEach(ser ->{
                serList.add(modelMapper.map(ser, ServiceDto.class));
            });
            return serList;
        }catch (Exception e){
            log.error("Error get all user :( => "+e.getMessage());
            return null;
        }
    }

    @Override
    public Long getLastId() {
        List<ServiceEntity> all = serviceRepository.findAll();
        Long lastId = 0L;
        for (int i=0 ; i< all.size()-1 ; i++){
            Long fI = all.get(i).getServiceId();
            Long sI = all.get(i+1).getServiceId();
            if (fI<sI){
                lastId = sI;
            }
        }
        return lastId;
    }
}
