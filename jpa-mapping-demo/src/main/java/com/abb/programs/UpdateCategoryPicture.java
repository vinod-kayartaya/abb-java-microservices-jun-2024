package com.abb.programs;

import com.abb.entity.Category;
import com.abb.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.io.FileInputStream;
import java.io.IOException;

public class UpdateCategoryPicture {

    static Category getCategoryById(Integer id){
        try(EntityManager em = JpaUtil.createEntityManager()){
            return em.find(Category.class, id);
            // just before returning, the object returned is a "managed" object
        } // em.close() called here automatically
        // the object of category returned earlier is not a "detached" object
    }

    public static void main(String[] args) throws IOException {
        var c1 = getCategoryById(1); // c1 is a detached object
        System.out.println("Name = " + c1.getName());
        System.out.println("Desc = " + c1.getDescription());

        try(EntityManager em = JpaUtil.createEntityManager()){
            c1 = em.merge(c1); // c1 now becomes managed object again
            System.out.println("Products in this category: ");
            c1.getProducts().forEach( System.out::println );

            String filename = "/Users/vinod/Documents/_temp/beverages.jpeg";
            try(FileInputStream in = new FileInputStream(filename)){
                byte[] picture = new byte[in.available()];
                in.read(picture);
                c1.setPicture(picture); // c1 being a managed object, entity manager will send an UPDATE command during commit
            }// out.close() called here

            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("category picture is updated!");
        }
    }
}
