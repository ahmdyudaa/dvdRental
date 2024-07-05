package id.dojo.helper;
import com.google.gson.Gson;
import id.dojo.PgConnection;
import id.dojo.model.Actor;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class DBUtils<T> {

    private static Sql2o sql2o = PgConnection.getSql2o();
    static Gson gson = new Gson();
    //DBUtils
    public Res<List<T>> list(String query, Class<T> mapper) {
        try (Connection con = sql2o.open()) {
            List<T> list = con.createQuery(query).executeAndFetch(mapper);
            return new Res<List<T>>("Berhasil Mendapatkan Data.", list);
        }
        catch (Sql2oException sql2oException) {
            sql2oException.printStackTrace();
            return new Res(sql2oException.toString(),null );
        } catch (Exception e){
            e.printStackTrace();
            return new Res(e.toString(), null);
        }
    }

    public Res<T> get(String query,Object param, Class<T> mapper) {
        try (Connection con = sql2o.open()) {
            T data = con.createQuery(query).withParams(param).executeAndFetchFirst(mapper);
            return new Res<T>("Berhasil Mendapatkan data", data);
        } catch (Sql2oException sql2oException) {
            return new Res(sql2oException.toString(),null);
        } catch (Exception e){
            return new Res(e.toString(), null);
        }
    }
    //END DBUtils

    public static <T> String queryAll(String query, Class<T> clazz) {
        try (Connection con = sql2o.open()) {
            List<T> result = con.createQuery(query).executeAndFetch(clazz);
            Res<List<T>> res = new Res<>("Berhasil mendapatkan semua data", result);
            return gson.toJson(res);
        } catch (Sql2oException e) {
            e.printStackTrace();
            return handleError(e);
        }catch(Exception e){
            e.printStackTrace();
            return gson.toJson(new Res<>(e.toString(), null));
        }
    }

    public static <T> String querySingle(String query, Class<T> clazz, int id) {
        try (Connection con = sql2o.open()) {
            T result = con.createQuery(query).addParameter("actorId", id).executeAndFetchFirst(clazz);
            Res<T> res = new Res<>("Berhasil mendapatkan data", result);
            return gson.toJson(res);
        } catch (Sql2oException e) {
            return handleError(e);
        }
    }

    public static String create(String query, Object... params) {
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("firstName", params[0])
                    .addParameter("lastName", params[1])
                    .executeUpdate()
                    .getKey();
            return gson.toJson(new Res<>("Berhasil memperbarui data", null));
        } catch (Sql2oException e) {
            return handleError(e);
        }
    }

    public static String update(String query, Object... params) {
        try (Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("firstName", params[0])
                    .addParameter("lastName", params[1])
                    .addParameter("actorId", params.length > 2 ? params[2] : null)
                    .executeUpdate();
            return gson.toJson(new Res<>("Berhasil memperbarui data", null));
        } catch (Sql2oException e) {
            return handleError(e);
        }
    }

    public static String delete(String query, int id){
        try (Connection con = sql2o.open()) {
            con.createQuery(query).addParameter("actorId", id).executeUpdate();
            return gson.toJson(new Res<>("Berhasil memperbarui data", null));
        } catch (Sql2oException e) {
            return handleError(e);
        }
    }

    private static String handleError(Sql2oException e) {
        return gson.toJson(new Res<>("Gagal melakukan query: " + e.getMessage(), null));
    }



}
