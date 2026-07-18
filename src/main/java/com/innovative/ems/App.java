package com.innovative.ems;

import com.innovative.ems.config.AppConfig;
import com.innovative.ems.entity.Employee;
import com.innovative.ems.service.EmployeeService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner sc = new Scanner(System.in);
        EmployeeService service = context.getBean(EmployeeService.class);

        System.out.println("Welcome to Employee Management System");

        int choice;
        while (true) {
            System.out.println("\n=========== EMPLOYEE MANAGEMENT SERVICE =============== \n1. Save New Employee.\n2. Find Employee By Id.\n3.Find All Employee Data.\n4. Update Employee Record.\n5.Delete Employee Record.\n6. Exit.");
            System.out.println("Enter the choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Department: ");
                    String department = sc.nextLine();
                    System.out.println("Enter Salary: ");
                    double salary = sc.nextDouble();
                    Employee employee = new Employee(name, department, salary);
                    System.out.println(employee);
                    service.save(employee);
                    System.out.println("Employee saved successfully");
                    System.out.println(employee);
                    break;
                }
                case 2: {
                    System.out.println("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Employee employee = service.findById(id);
                    if (employee != null) {
                        System.out.println("Employee with ID: " + id + " Exists.\nThe Employee Details are ");
                        System.out.println("Employee ID: " + employee.getId());
                        System.out.println("Name: " + employee.getName());
                        System.out.println("Department: " + employee.getDepartment());
                        System.out.println("Salary: " + employee.getSalary());
                    } else {
                        System.out.println("Employee with ID: " + id + " doesn't exists");
                    }
                    break;
                }
                case 3: {
                    List<Employee> employeeList = service.findAll();
                    if (!employeeList.isEmpty()) {
                        System.out.println("\n--------- Total Employee Records ---------");
                        for (Employee employee : employeeList) {
                            System.out.println("ID: " + employee.getId() + " Name: " + employee.getName() + " Department: " + employee.getDepartment() + " Salary: " + employee.getSalary());
                        }
                    } else {
                        System.out.println("No Records Found.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();
                    Employee employee = service.findById(empId);
                    if (employee == null) {
                        System.out.println("Employee record doesn't Exists.");
                        break;

                    }
                    System.out.println("Employee with ID: " + empId + " Exists.\nThe Current employee details are ");
                    System.out.println(employee);
                    System.out.println("Enter the Name: ");
                    employee.setName(sc.nextLine());
                    System.out.println("Enter the Department: ");
                    employee.setDepartment(sc.nextLine());
                    System.out.println("Enter the Salary: ");
                    employee.setSalary(sc.nextDouble());
                    service.update(employee);
                    System.out.println("Employee Record updated successfully..");
                    break;


                }
                case 5: {
                    System.out.println("Enter Employee ID:");
                    int empId = sc.nextInt();
                    boolean deleted = service.delete(empId);
                    if (!deleted) {
                        System.out.println("Employee record not found");
                    } else {
                        System.out.println("Employee record removed successfully.");
                    }
                    break;
                }
                case 6: {
                    System.out.println("<-----------X Exiting X----------->");
                    sc.close();
                    return;
                }
                default: {
                    System.out.println("Invalid choice. Try again");
                }


            }


        }
    }
}
