package com.example.PeregrinosFX.bean;

import org.w3c.dom.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import java.io.File;

public class ConnectExistDB {
    String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    String driver = "org.exist.xmldb.DatabaseImpl";
    Collection col = null;
    XMLResource res = null;

    private void verDatosCol(Parada parada) {
        String resources[] = new String[0];
        try {
            resources = col.listResources();
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < resources.length; i++) {
            System.out.println(resources[i]);
        }
    }

    public void guardar(File file, Parada parada, Document document) {
        try {
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            col = DatabaseManager.getCollection(URI + "/db", "admin", "");
            String nombreColeccionPrincipal = "/db/carnets";
            String nombreSubColeccion = parada.getNombre();
            Collection colPrincipal = col.getChildCollection(nombreColeccionPrincipal);
            Collection subCol = colPrincipal.getChildCollection(nombreSubColeccion);
            if (subCol != null) {
                res = (XMLResource) col.createResource(file.getName(), "XMLResource");
                res.setContent(document);
                col.storeResource(res);
            } else {
                CollectionManagementService mgtService = (CollectionManagementService)
                        col.getService("CollectionManagementService", "1.0");
                try {
                    mgtService.createCollection(parada.getNombre().toLowerCase());
                } catch (XMLDBException e) {
                    e.printStackTrace();
                }
            }
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (col != null) {
            try {
                col.close();
                //DatabaseInstanceManager manager = (DatabaseInstanceManager) col.getService("DatabaseInstanceManager", "1.0");
                //manager.shutdown();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }}




