package id.dojo.model;

import id.dojo.PgConnection;
import id.dojo.helper.Res;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.Timestamp;
import java.util.List;

public class Payment {
    private int payment_id;
    private int customer_id;
    private int staff_id;
    private int rental_id;
    private int amount;
    private Timestamp payment_date;

    static Sql2o sql2o = PgConnection.getSql2o();

    public String toString(){
        return "id : " + this.payment_id +
                " customerId : " + this.customer_id +
                " staffId" + this.staff_id +
                " rentalId : " + this.rental_id +
                " amount : " + this.amount +
                " paymentDate " + this.payment_date+ "\n";
    }

    public static Res<List<Payment>> listPayment(){
        try(Connection con = sql2o.open()) {
            List<Payment> payments = con.createQuery("SELECT payment_id, customer_id, staff_id, rental_id, amount, payment_id FROM payment").executeAndFetch(Payment.class);
            Res<List<Payment>>res = new Res<List<Payment>>(
                    "Berhasil mendapatkan data payment",
                    payments
            );
            return res;
        }
    }

}
