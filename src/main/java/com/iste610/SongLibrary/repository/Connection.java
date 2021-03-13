package com.iste610.SongLibrary.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Connection {

    //TODO: razmisliti gdje uspostaviti konekciju na bazu? Prilikom pokretanja applikacije?
    public MongoDatabase connectDatabase() {
        MongoClientURI connectionString =
                new MongoClientURI("mongodb+srv://team-user:teletabisi@songlibrary.scmrf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(connectionString);

        return mongoClient.getDatabase("SongLibrary");
    }
}
