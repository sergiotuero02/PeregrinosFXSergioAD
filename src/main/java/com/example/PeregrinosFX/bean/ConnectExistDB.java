package com.example.PeregrinosFX.bean;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import java.io.File;
import java.util.ArrayList;

public class ConnectExistDB {
    private String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/carnets";
    private String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection colleccion = null;
    private XMLResource carnetxml = null;
    public void almacenarCarnet(File file, Parada parada) {
        try {
            //Iniciar driver
            Class clase = Class.forName(driver);
            Database database = (Database) clase.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            colleccion = DatabaseManager.getCollection(URI, "admin", "");

            String paradacolection = parada.getNombre(); // Nombre de la subcolección

            Collection subColeccion = colleccion.getChildCollection(paradacolection);

            while (subColeccion == null) {
                CollectionManagementService management = (CollectionManagementService)
                        colleccion.getService("CollectionManagementService", "1.0");
                try {
                    management.createCollection("" + parada.getNombre());
                } catch (XMLDBException e) {
                    e.printStackTrace();
                }
                subColeccion = colleccion.getChildCollection(paradacolection);
            }
            if (subColeccion != null) {
                carnetxml = (XMLResource) subColeccion.createResource(file.getName(), "XMLResource");
                carnetxml.setContent(file);
                subColeccion.storeResource(carnetxml);
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

        if (colleccion != null) {
            try {
                colleccion.close();
                /// Apagar el servidor ExistDB
                //DatabaseInstanceManager manager = (DatabaseInstanceManager) col.getService("DatabaseInstanceManager", "1.0");
                //manager.shutdown();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
    public void carnets(Parada parada, ListView carnetsLV) {
        try{
        ArrayList<String> carnets = new ArrayList<String>();
        carnetsLV.getItems().clear();
        try {
            //Iniciar driver
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            colleccion = DatabaseManager.getCollection(URI, "admin", "");
            String nombreSubColeccion = parada.getNombre();
            Collection subCol = colleccion.getChildCollection(nombreSubColeccion);
            try {
                String[] carnet;
                carnet = subCol.listResources();
                for(String c : carnet){
                    carnets.add(c);
                }
            } catch (XMLDBException e) {
                e.printStackTrace();
            }

            for (String c : carnets) {
                carnetsLV.getItems().add(c);
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
    }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("La parada seleccionada no tiene ningún carnet almacenado en la bd.");
            alert.showAndWait();
        }
    }
}




