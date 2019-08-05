package database;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;

public class dbutil {
    private MongoDatabase store;
    private MongoCollection<Document> scol;
    private MongoCollection<Document> fcol;
    private MongoCollection<Document> smcol;
    private String databaseName = "gradify-storage";
    private String facultyCollectionName = "faculties";
    private String studentMarksCollectionName = "studentmarks";
    private String studentCollectionName = "students";

    private boolean checkIfCollectionNotExists(MongoDatabase database, String collectionName){
        MongoIterable<String> collection =  database.listCollectionNames();
        for(String s : collection) {
            if(s.equals(collectionName)) {
                return false;
            }
        }
        return true;
    }

    private void connectStudentDB(){
        MongoClient mongoClient = new MongoClient();
        store = mongoClient.getDatabase(databaseName);
        if(checkIfCollectionNotExists(store, studentCollectionName)) {
            store.createCollection(studentCollectionName);
            scol = store.getCollection(studentCollectionName);
        }
        else {
            scol = store.getCollection(studentCollectionName);
        }
    }

    private void connectStudentMarksDB(){
        MongoClient mongoClient = new MongoClient();
        store = mongoClient.getDatabase(databaseName);
        if(checkIfCollectionNotExists(store, studentMarksCollectionName)) {
            store.createCollection(studentMarksCollectionName);
            smcol = store.getCollection(studentMarksCollectionName);
        }
        else {
            smcol = store.getCollection(studentMarksCollectionName);
        }
    }

    private void connectFacultyDB(){
        MongoClient mongoClient = new MongoClient();
        store = mongoClient.getDatabase(databaseName);
        if(checkIfCollectionNotExists(store, facultyCollectionName)) {
            store.createCollection(facultyCollectionName);
            fcol = store.getCollection(facultyCollectionName);
        }
        else {
            fcol = store.getCollection(facultyCollectionName);
        }
    }

    public void addStudent(String studentName, String studentBatchNo, String studentRollNo, int studentRegNo) {
        connectStudentDB();
        Document document = new Document("name", studentName)
                                    .append("rollno", studentRollNo)
                                    .append("batchno", studentBatchNo)
                                    .append("regno", studentRegNo);
        scol.insertOne(document);
    }

    public void addFaculty(String facultyName, String facultyUserID, String facultyPassword) {
        connectFacultyDB();
        Document document = new Document("name", facultyName)
                .append("userid", facultyUserID)
                .append("password", facultyPassword);
        fcol.insertOne(document);
    }

    public void addMarks(int studentRegNo, String subjectCode, int midSem, int endSem, int classTest, int attendance) {
        connectStudentMarksDB();
        smcol.updateMany(
                and(eq("regno", studentRegNo), eq("subjectcode", subjectCode)),
                combine(set("midsem", midSem),
                        set("endsem", endSem),
                        set("classtest", classTest),
                        set("attendance", attendance),
                        currentDate("lastModified")));
    }

    public void addSubject(String subjectCode, String batchNo, String subjectName, String semester, int credits) {
        connectStudentDB();
        connectStudentMarksDB();
        MongoIterable<Document> retobj = scol.find(eq("batchno",batchNo))
                .projection(fields(include("regno"), excludeId()));
        int regNo;
        for (Document d:retobj) {
            regNo = d.getInteger("regno");
            Document document = new Document("regno", regNo)
                    .append("subjectcode", subjectCode)
                    .append("subjectname", subjectName)
                    .append("midsem", 0)
                    .append("endsem", 0)
                    .append("classtest", 0)
                    .append("attendance", 0)
                    .append("semester", semester)
                    .append("credits", credits);
            smcol.insertOne(document);
        }
    }

    public boolean canLogin(String facultyUserID, String facultyPaasword) {
        connectFacultyDB();
        MongoIterable<Document> retobj = fcol.find(and(eq("userid",facultyUserID), eq("password", facultyPaasword)))
                .projection(fields(include("name"), excludeId()));
        int i = 0;
        for (Document d:retobj) {
            i++;
        }
        if(i==1) {
            return true;
        }
        else{
            return false;
        }
    }

    public String canLoginStr(String facultyUserID, String facultyPaasword) {
        connectFacultyDB();
        MongoIterable<Document> retobj = fcol.find(and(eq("userid",facultyUserID), eq("password", facultyPaasword)))
                .projection(fields(include("name"), excludeId()));
        String tname = "";
        int i = 0;
        for (Document d:retobj) {
            i++;
            tname = d.getString("name");
        }
        if(i==1) {
            return tname;
        }
        else{
            return tname;
        }
    }

    public MongoIterable<Document> getMarks(String semester, int regNo) {
        connectStudentMarksDB();
        MongoIterable<Document> retobj = smcol.find(and(eq("regno",regNo), eq("semester", semester)));
        return retobj;
    }

    public MongoIterable<Document> getMarkssub(String subcode, int regNo) {
        connectStudentMarksDB();
        MongoIterable<Document> retobj = smcol.find(and(eq("regno",regNo), eq("subjectcode", subcode)));
        return retobj;
    }

    public MongoIterable<Document> getStudentDetails(int regNo) {
        connectStudentDB();
        System.out.println(regNo);
        MongoIterable<Document> retobj = scol.find(eq("regno",regNo))
                .projection(fields(include("name", "batchno", "rollno", "regno"), excludeId()));
        for (Document d:retobj
             ) {
        }
        return retobj;
    }
}