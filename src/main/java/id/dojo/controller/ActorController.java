package id.dojo.controller;

import id.dojo.model.Actor;
import io.javalin.http.Handler;

public class ActorController {
    public static Handler listActorApi = ctx -> ctx.json(Actor.listActor());

    public static Handler getActorApi = ctx -> {
        int actorId = Integer.parseInt(ctx.pathParam("actor_id"));
        ctx.json(Actor.getActor(actorId));
    };

    public static Handler addActorApi = ctx -> {
        String firstName = ctx.formParam("first_name");
        String lastName = ctx.formParam("last_name");
        ctx.json(Actor.addActor(firstName, lastName));
    };

    public static Handler updateActorApi = ctx -> {
        int actorId = Integer.parseInt(ctx.formParam("actor_id"));
        String firstName = ctx.formParam("first_name");
        String lastName = ctx.formParam("last_name");
        ctx.json(Actor.updateActor(actorId, firstName, lastName));
    };

    public static Handler deleteActorApi = ctx -> {
        int actorId = Integer.parseInt(ctx.formParam("actor_id"));
        ctx.json(Actor.deleteActor(actorId));
    };



}
