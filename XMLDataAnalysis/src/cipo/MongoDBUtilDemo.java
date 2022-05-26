package cipo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
//import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.io.FileUtils;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MongoDBUtilDemo {

    //private static final String DEVHOSTNAME = "perseus-crud-test.eks.test.int.compumark.com";
    private static final String DEVHOSTNAME = "ctr.test.dev-compumark.com";

    private static final String PRODHOSTNAME = "pmgo-pl201.int.compumark.com";

    private static final int DEVPORT = 27017;  // old 28019

    private static final int PRODPORT = 27017;

    private static final String DEVDBNAME = "ctr"; // old: ctr-dev-perseus

    private static final String PRODDBNAME = "ctr";


    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient(PRODHOSTNAME, PRODPORT);
    	MongoDatabase database = mongoClient.getDatabase(PRODDBNAME);

    	//MongoClient mongoClient = new MongoClient(DEVHOSTNAME, DEVPORT);
        //MongoDatabase database = mongoClient.getDatabase(DEVDBNAME);

        MongoCollection<Document> collection = database.getCollection("trademarks.cana");
        //MongoCollection<Document> collection = database.getCollection("trademarks.usaf");
        
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.Dates.DtGiEntered\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.Notes.AdditionalInfo.ExpCancTxt\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.Description.CaDsgnCd\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.HistoricalInfo.History.ActInf\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.GoodsServices.WaresServices.Indicator\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.OwnerAffiliates.Agents.Agent.Name\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.Description.DsgnPhrases\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.Doc.Description.TrmNamCrossRef\"";
        //String jpath = "\"Doc.n-document.n-docbody.Document.id\"";
        String jpath = "\"Doc.n-document.n-docbody.Document.Doc.GoodsServices.ServiceInfo\"";
        
       
        
        //String query="[{"+jpath+": { $in: ['73268609', '77076232', '77916362']}}]";
        //String query="[{"+jpath+": { $in: ['90113316', '90113001', '90112619']}}]";
        //String query="[{"+jpath+": { $in: ['90113001']}}]";

        
       
        //String query="[{\"Doc.n-document.n-docbody.Document.Doc.Description.TrmNamCrossRef\": { $exists: true }}, {\"Doc.n-document.n-docbody.Document.Doc.Dates.DtUpdated\":  { $gte: /20200723/ }}]";
        //object►doc►n-document►n-docbody►Document►Doc►Classification►IntClaInfo►Cla
              
        //String query="[{\"Doc.n-document.n-docbody.Document.Doc.Classification.IntClaInfo.Cla\": { $exists: false}}, {\"Doc.n-document.n-docbody.Document.Doc.Dates.DtUpdated\":  /202006/}]";
        //String query="[{\"Doc.n-document.n-docbody.Document.Doc.Classification.IntClaInfo.Cla\": { $exists: false}}]";
        //String query="[{\"Doc.n-document.n-docbody.Document.Doc.Classification.IntClaInfo.Cla\": { $in: ['57'] } }]";
        String query="[{\"Doc.n-document.n-docbody.Document.Doc.GoodsServices.GoodsInfo.Text\": { $exists: true }}"; 
        
        FileWriter fw1=new FileWriter("c:\\raj\\mongosearchoutput.txt");
        BsonArray parse = BsonArray.parse(query);
        BasicDBList dbList = new BasicDBList();
        dbList.addAll(parse);
        System.out.println(dbList.size());
        BasicDBObject dbObject = new BasicDBObject("$and", dbList);
        //File file = new File("C:\\raj\\mongooutput.txt");
        int counter = 0;
        try (MongoCursor<Document> cur = collection.find(dbObject).iterator()) {

        	while ((cur.hasNext()) && counter<100) {

                Document doc = cur.next();
                fw1.write("\ncounter ="+counter);
                
                
                //String name = doc.getString("TrmNamCrossRef");
                
                //doc.get(key)
                System.out.println("\n"+doc.toJson());
                
                fw1.write("\n"+doc.toJson());
                //FileUtils.writeStringToFile(file, "counter ="+counter);
                //FileUtils.writeStringToFile(file, doc.toJson(), StandardCharsets.UTF_8);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
