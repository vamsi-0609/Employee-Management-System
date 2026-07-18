package com.innovative.ems.repository;

import com.innovative.ems.entity.Employee;
import com.innovative.ems.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Exception occurred while saving the employee details.");
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }

    @Override
    public Employee findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("From Employee", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }

    @Override
    public void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while updating the Employee Record");
            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) { //try-with-resources which automatically closes the opened resources after using them.
            transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            if (employee == null) {  //Refers as a Guard clause which terminates whether the condition fails or else continue with the below code.
                return false;

            }
            session.remove(employee);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error occurred while deleting employee record.");
            e.printStackTrace();
        }
        return false;


    }
}
