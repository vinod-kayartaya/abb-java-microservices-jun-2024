package com.abb;

import com.abb.entity.Customer;
import com.abb.entity.Gender;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddCustomer {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TRAINING");
        log.debug("factory is a variable of type {}", factory.getClass().getName());
        EntityManager em = factory.createEntityManager();
        log.debug("em is a variable of type {}", em.getClass().getName());

        Customer c1 = new Customer();
        c1.setFirstname("Vinod");
        c1.setLastname("Kumar");
        c1.setEmail("vinod@vinod.co");
        c1.setPhone("9731424784");
        c1.setCity("Bengaluru");
        c1.setGender(Gender.MALE);

        var tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(c1);
            tx.commit();
            System.out.println("Customer data saved.");
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        }
    }

}
