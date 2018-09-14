package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        // get hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query + sort by lastName
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
        // execute query and get result
        List<Customer> customers = theQuery.getResultList();
        // return results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save or update the customer
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {

        Session currentSession = sessionFactory.getCurrentSession();
        // create a query for exact customer
        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        // Query - delete on id
        Query query = currentSession.createQuery("delete from Customer where id=:CustomerId");
        query.setParameter("CustomerId", theId);

        query.executeUpdate();

    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;

        if (theSearchName != null && theSearchName.trim().length() > 0) {
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            theQuery = currentSession.createQuery("from Customer", Customer.class);
        }
        List<Customer> customers = theQuery.getResultList();
        return customers;
    }

}





