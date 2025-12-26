package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;

/**
 * 员工服务接口
 * @author sky
 */
public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO 员工登录数据传输对象
     * @return 员工实体对象
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 保存员工信息
     * @param employeeDTO 员工数据传输对象
     */
    void save(EmployeeDTO employeeDTO);
}
