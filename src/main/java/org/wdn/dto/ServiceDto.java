package org.wdn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private Long serviceId;
    private String serviceName;
    private Double price;
    private String serviceAddress;
    private String serviceDescription;
    private String employeeId;
}
