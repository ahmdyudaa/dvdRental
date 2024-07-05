package id.dojo.model;

import id.dojo.helper.DBUtils;
import id.dojo.helper.Res;

import java.sql.Timestamp;

public class Inventory {
    private Integer inventory_id;
    private Integer film_id;
    private Integer store_id;
    private Timestamp last_update;

    public static Res<Inventory> getInventoryById(Integer inventoryId){
        Res<Inventory> data = new DBUtils<Inventory>().get("SELECT inventory_id, film_id, store_id, last_update FROM inventory WHERE inventory_id = :p1;", inventoryId, Inventory.class);
        return data;
    }
}
