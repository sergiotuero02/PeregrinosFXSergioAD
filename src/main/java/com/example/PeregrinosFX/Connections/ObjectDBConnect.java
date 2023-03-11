package com.example.PeregrinosFX.Connections;

import com.objectdb.jpa.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ObjectDBConnect {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                    "$objectdb/db/p2.odb");
    public static EntityManager em = emf.createEntityManager();
}
