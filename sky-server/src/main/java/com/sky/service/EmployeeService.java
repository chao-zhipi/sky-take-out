package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

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

    /**
     * 员工分页查询
     * @param employeePageQueryDTO 员工分页查询数据传输对象
     * @return 分页结果
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用或禁用员工账号
     * @param status 状态
     * @param id 员工id
     */
    void startOrStop(int status, long id);

    /**
     * 根据id查询员工信息
     * @param id 员工id
     * @return 员工实体对象
     */
    Employee getById(long id);

    /**
     * 更新员工信息
     * @param employeeDTO 员工数据传输对象
     */
    void update(EmployeeDTO employeeDTO);
}
