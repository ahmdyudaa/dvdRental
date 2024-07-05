package id.dojo;

import org.sql2o.Sql2o;

public class PgConnection {

        private static Sql2o sql2o;

        static {
            System.out.println("run koneksi");
            sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dvd_rental", "test", "test");
        }
        public static Sql2o getSql2o(){return sql2o;}
}
