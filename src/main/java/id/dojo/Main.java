package id.dojo;
import com.google.gson.Gson;
import id.dojo.controller.ActorController;
import id.dojo.controller.FilmCategoryController;
import id.dojo.helper.Res;
import id.dojo.model.Actor;
import io.javalin.Javalin;
import java.util.List;

import static id.dojo.model.Actor.listActor;
import static id.dojo.model.Payment.listPayment;

public class Main {
    static Gson gson = new Gson();

    public static void main(String[] args) {
        var app = Javalin.create(/*config*/).start(7070);

        app.get("/actor", ActorController.listActorApi);
        app.get("/actor/{actor_id}", ActorController.getActorApi);
        app.post("/actor", ActorController.addActorApi);
        app.put("/actor", ActorController.updateActorApi);
        app.delete("/actor/delete", ActorController.deleteActorApi);

        app.get("/film-category", FilmCategoryController.listFilmCategory);
    }
}