package org.wdn.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wdn.dto.ServiceDto;
import org.wdn.service.ServiceService;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/service")
@RestController
public class ServiceController {
    final ServiceService service;

    @GetMapping("add")
    public ResponseEntity<String> add(@Valid @RequestBody ServiceDto serviceDto){

    }
    @PutMapping()
    public ResponseEntity<String> update(@Valid @RequestBody ServiceDto serviceDto){

    }
    @DeleteMapping()
    public ResponseEntity<String> deleteById(Long id){

    }
    @DeleteMapping
    public ResponseEntity<String> deleteByServiceName(String serviceName){

    }
    @DeleteMapping()
    public ResponseEntity<String> deleteall(){

    }
    @GetMapping()
    public Map<String,ServiceDto> searchById(Long id){

    }
    @GetMapping()
    public Map<String, List<ServiceDto>> searchByServiceName(String serviceName){

    }
    @GetMapping()
    public Map<String, List<ServiceDto>> searchByEmployeeId(Long id){

    }
    @GetMapping()
    public Map<String, List<ServiceDto>> getAll(){

    }
    @GetMapping()
    public Long getLastId(){

    }

}
