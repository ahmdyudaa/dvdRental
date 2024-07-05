package id.dojo.model;

import com.google.gson.Gson;
import id.dojo.PgConnection;
import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.List;

public class Actor {
    private int actor_id;
    private String  first_name;
    private String last_name;
    private Timestamp last_update;

    static Sql2o sql2o = PgConnection.getSql2o();
    static Gson gson = new Gson();


    public static String listActor(){
        String query = "SELECT * FROM actor";
        return DBUtils.queryAll(query,Actor.class);
    }

    public static String getActor(int actorId){
        String query = "SELECT actor_id, first_name, last_name, last_update FROM actor WHERE actor_id = :actorId";
        return DBUtils.querySingle(query, Actor.class, actorId);
    }

    public static String addActor(String firstName, String lastName) {
        String query = "INSERT INTO actor (first_name, last_name) VALUES (:firstName, :lastName)";
        return DBUtils.create(query, firstName, lastName);
    }

    public static String updateActor(int actorId, String firstName, String lastName) {
        String query = "UPDATE actor SET first_name = :firstName, last_name = :lastName WHERE actor_id = :actorId";
        return DBUtils.update(query, firstName, lastName, actorId);
    }

    public static String deleteActor(int actorId) {
        String query = "DELETE FROM actor WHERE actor_id = :actorId";
        return DBUtils.delete(query, actorId);
    }


}
