package com.abb.programs;

import com.abb.entity.Category;
import com.abb.entity.Product;
import com.abb.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveCategoryWithProducts {
    public static void main(String[] args) {

        Category c1 = new Category();
        c1.setName("Beverages");
        c1.setDescription("Soft drinks etc");

        Product p1 = new Product();
        p1.setName("Chai");
        p1.setUnitPrice(290.0);
        p1.setUnitDescription("250gm tea powder");
        p1.setUnitsInStock(21);
        p1.setCategory(c1);


        Product p2 = new Product();
        p2.setName("Mocha");
        p2.setUnitPrice(250.0);
        p2.setUnitDescription("200gm Coffee powder");
        p2.setUnitsInStock(55);
        p2.setCategory(c1);

        try(EntityManager em = JpaUtil.createEntityManager()){
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            // at this time, c1, p1, p2 are "new" objects (not associated with "em")
            em.persist(c1); // because of the cascade option setting, p1 and p2 are also persisted here
            try {
                tx.commit();
                log.debug("c1, p1, p2 saved to db");
            } catch (Exception e) {
                tx.rollback();
                throw new RuntimeException(e);
            }
            // at this time, c1, p1, p2 are called "managed" objects (w.r.t em)
        } // em.close() called automatically here; db connection is closed here
        catch (Exception e){
            log.warn("error while saving", e);
        }

        // at this time c1, p1, p2 are called "detached" objects (w.r.t em)

    }
}
