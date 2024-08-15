package com.management.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.primary.Employee;

@Service
public class EmployeeService {

    @Autowired
    private SessionFactory sessionFactory;

    public void batchInsertEmployees(List<Employee> employees) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            int batchSize = 30;
            for (int i = 0; i < employees.size(); i++) {
                session.persist(employees.get(i));  // Replaced save with persist

                if (i % batchSize == 0 && i > 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
