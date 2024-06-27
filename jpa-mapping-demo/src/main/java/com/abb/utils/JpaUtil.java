package com.abb.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JpaUtil {

    private JpaUtil() {
    }

    public static EntityManager createEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TRAINING");
        return factory.createEntityManager();
    }
}
