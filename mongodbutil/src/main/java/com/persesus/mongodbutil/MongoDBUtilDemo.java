package com.persesus.mongodbutil;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.io.FileUtils;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MongoDBUtilDemo {

    private static final String DEVHOSTNAME = "deng-pl111.int.compumark.com";

    private static final String PRODHOSTNAME = "pmgo-pl201.int.compumark.com";

    private static final int DEVPORT = 28019;

    private static final int PRODPORT = 27017;

    private static final String DEVDBNAME = "ctr-dev-perseus";

    private static final String PRODDBNAME = "ctr";


    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(PRODHOSTNAME, PRODPORT);

        MongoDatabase database = mongoClient.getDatabase(PRODDBNAME);

        MongoCollection<Document> collection = database.getCollection("trademarks.cana");
        String query="[{\"Doc.n-document.n-docbody.Document.Doc.Dates.DtGiEntered\": { $exists: true }}, {\"Doc.n-document.n-docbody.Document.Doc.Dates.DtUpdated\":  /202006/}]";
        BsonArray parse = BsonArray.parse(query);
        BasicDBList dbList = new BasicDBList();
        dbList.addAll(parse);
        System.out.println(dbList.size());
        BasicDBObject dbObject = new BasicDBObject("$and", dbList);
        final File file = new File("C:\\Users\\alabdul\\Project\\Clarivate\\Documents\\DSL_Transformation\\MongoDBOutput\\output.txt");
        try (MongoCursor<Document> cur = collection.find(dbObject).iterator()) {

            while (cur.hasNext()) {

                Document doc = cur.next();
                System.out.println("Found JSON Object");
                FileUtils.writeStringToFile(file, doc.toJson(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
