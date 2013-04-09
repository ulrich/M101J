package com.tengen.hw2;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HM2_2 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();

        DB students = mongoClient.getDB("students");

        DBCollection grades = students.getCollection("grades");

        BasicDBObject type = new BasicDBObject("type", "homework");

        BasicDBObject sort = new BasicDBObject("student_id", 1);
        sort.append("score", 1);

        DBCursor dbCursor = grades.find(type).sort(sort);

        Map<Integer, Key> map = new HashMap<Integer, Key>();

        while (dbCursor.hasNext()) {
            DBObject cursor = dbCursor.next();

            ObjectId _id = (ObjectId) cursor.get("_id");

            Integer student_id = (Integer) cursor.get("student_id");

            Double score = (Double) cursor.get("score");

            Key key = map.get(student_id);

            if ((key == null) || (score < key.value)) {
                map.put(student_id, new Key(_id, score));
            }
        }
        for (Key key : map.values()) {
            System.out.println("Removing _id=" + key._id);

            grades.remove(new BasicDBObject("_id", key._id));
        }

        System.out.println(map.size());
    }

    static class Key {
        ObjectId _id;

        Double value;

        public Key(ObjectId _id, Double value) {
            this._id = _id;

            this.value = value;
        }
    }
}
