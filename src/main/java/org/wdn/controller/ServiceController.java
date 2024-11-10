package org.wdn.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.wdn.dto.ServiceDto;
import org.wdn.service.ServiceService;

import java.util.HashMap;
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
        if (service.add(serviceDto)){
            log.info("Received request to add user : {}",serviceDto);
            return ResponseEntity.ok("service added successfuly");
        }else {
            return ResponseEntity.ok("service can't added :(");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody ServiceDto serviceDto){
        if (service.update(serviceDto)){
            log.info("Received request to update user : {}",serviceDto);
            return ResponseEntity.ok("service updated successfully");
        }else {
            return ResponseEntity.ok("service can't update");
        }
    }
    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable Long id){
        if (service.deleteById(id)){
            log.info("Received request to delete service : {}",id);
            return ResponseEntity.ok("service deleted successfully");
        }else {
            return ResponseEntity.ok("service can't delete");
        }
    }
    @DeleteMapping("delete-by-serviceName/{serviceName}")
    public ResponseEntity<String> deleteByServiceName(@PathVariable String serviceName){
        if (service.deleteByServiceName(serviceName)){
            log.info("Received request to delete service : {}",serviceName);
            return ResponseEntity.ok("service deleted successfully");
        }else {
            return ResponseEntity.ok("service can't delete");
        }
    }
    @DeleteMapping("delete-all")
    public ResponseEntity<String> deleteAll(){
        if (service.deleteAll()){
            log.info("Received request to delete all service ");
            return ResponseEntity.ok("service deleted successfully");
        }else {
            return ResponseEntity.ok("service can't delete");
        }
    }
    @GetMapping("/search-by-id/{id}")
    public Map<String,ServiceDto> searchById(@PathVariable Long id){
        ServiceDto serviceDto = service.searchById(id);
        Map<String,ServiceDto> map = new HashMap<>();
        log.debug("Received request to search by id : {} -> {}",id,serviceDto);
        map.put("service",serviceDto);
        return map;
    }
    @GetMapping("/search-by-serviceName/{serviceName}")
    public Map<String,List<ServiceDto>> searchByServiceName(@PathVariable String serviceName){
        List<ServiceDto> serviceDtos = service.searchByServiceName(serviceName);
        Map<String,List<ServiceDto>> map = new HashMap<>();
        log.debug("Received request to search by name : {} ",serviceName);
        map.put("service",serviceDtos);
        return map;
    }
    @GetMapping("/search-by-employeeId/{employeeId}")
    public Map<String, List<ServiceDto>> searchByEmployeeId(Long id){
        List<ServiceDto> serviceDtos = service.searchByEmployeeId(id);
        Map<String,List<ServiceDto>> map = new HashMap<>();
        log.debug("Received request to search by employeeId : {} ",id);
        map.put("service",serviceDtos);
        return map;
    }
    @GetMapping("/get-all")
    public Map<String, List<ServiceDto>> getAll(){
        List<ServiceDto> serviceDtos = service.getAll();
        Map<String,List<ServiceDto>> map = new HashMap<>();
        log.debug("Received request get all");
        map.put("service",serviceDtos);
        return map;
    }
    @GetMapping("/get-last-id")
    public Long getLastId(){
        return service.getLastId();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Validation error: {}", errors);
        return errors;
    }

}
