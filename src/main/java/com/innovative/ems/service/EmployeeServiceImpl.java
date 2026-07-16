package com.innovative.ems.service;

import com.innovative.ems.entity.Employee;
import com.innovative.ems.repository.EmployeeRepository;
import com.innovative.ems.repository.EmployeeRepositoryImpl;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl() {
        this.repository = new EmployeeRepositoryImpl();
    }

    private static final double MAX_SALARY = 2_00_000;

    @Override
    public void save(Employee employee) {
        validateEmployee(employee);
        repository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        validateId(id);
        return repository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Employee employee) {
        validateId(employee.getId());
        validateEmployee(employee);
        repository.update(employee);
    }

    @Override
    public boolean delete(int id) {
        validateId(id);
        return repository.delete(id);

    }

    private void validateEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee object cannot be null.");
        }

        String name = validateEmployeeName(employee.getName());
        employee.setName(name);

        String department = validateDepartment(employee.getDepartment());
        employee.setDepartment(department);

        validateSalary(employee.getSalary());


    }

    private void validateId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Employee ID must be greater than 0.");
        }

    }

    private String validateEmployeeName(String name) {
        // -------------------- Name Validation --------------------

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }

        name = name.trim();

        if (name.length() > 100) {
            throw new IllegalArgumentException(
                    "Employee name cannot exceed 100 characters.");
        }

        return name;


    }

    private String validateDepartment(String department) {
        // ---------------- Department Validation ------------------

        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException(
                    "Department cannot be empty.");
        }

        department = department.trim();

        if (department.length() > 50) {
            throw new IllegalArgumentException(
                    "Department name cannot exceed 50 characters.");
        }

        return department;

    }

    private void validateSalary(double salary) {
        // ------------------ Salary Validation --------------------

        if (salary <= 0) {
            throw new IllegalArgumentException(
                    "Salary must be greater than 0.");
        }

        if (salary > MAX_SALARY) {
            throw new IllegalArgumentException(
                    "Salary cannot exceed ₹" + MAX_SALARY);
        }

    }


}
