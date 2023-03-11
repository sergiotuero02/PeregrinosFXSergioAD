package com.example.PeregrinosFX.Connections;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4oConnect {
    public static final ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "peregrinos.db4o");
}
