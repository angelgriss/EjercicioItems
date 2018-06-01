package controller;

import com.google.gson.Gson;
import model.Item;
import model.ItemException;
import model.Pictures;
import service.ItemService;
import service.ItemServiceElasticSearch;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

public class ItemControllerVistas {
    public static void main(String [] args)
    {
        port(9494);

        final ItemService itemService=new ItemServiceElasticSearch();

        staticFileLocation("/public");

        try
        {
            get("/", (request, response) -> {
                return new ModelAndView(new HashMap(), "templates/hello.vtl");
            }, new VelocityTemplateEngine());

            get("/list", (request, response) -> {
                TimeUnit.SECONDS.sleep(1);
                List<Item> items=itemService.getAll();
                Map<String, Object> contextMap = new HashMap<String, Object>();
                contextMap.put("db_data",items);
                return new ModelAndView(contextMap, "templates/hello.vtl");
            }, new VelocityTemplateEngine());

            get("/item/create", (request, response) -> {
                return new ModelAndView(new HashMap(), "templates/itemCreate.vtl");
            }, new VelocityTemplateEngine());

            get("/item/:id", (request, response) -> {
                String id = request.params(":id");
                Item item=itemService.getItem(id);

                Map<String, Object> contextMap = new HashMap<String, Object>();
                //contextMap.put("db_data",item);
                contextMap.put("idItem",item.getId());
                contextMap.put("title",item.getTitle());
                contextMap.put("category_id",item.getCategory_id());
                contextMap.put("price",item.getPrice());
                contextMap.put("currency_id",item.getCurrency_id());
                contextMap.put("available_quantity",item.getAvailable_quantity());
                contextMap.put("buying_mode",item.getBuying_mode());
                contextMap.put("condition",item.getCondition());
                contextMap.put("description",item.getDescription());
                contextMap.put("video_id",item.getVideo_id());
                contextMap.put("warranty",item.getWarranty());
                contextMap.put("pictures",item.getPictures());

                return new ModelAndView(contextMap, "templates/itemView.vtl");
            }, new VelocityTemplateEngine());

            get("/item/delete/:id", (request, response) -> {
                String id = request.params(":id");
                itemService.deleteItem(id);

                response.status(201);
                response.redirect("/list");

                return "";
            });

            post("/item/create", (request, response) -> {
                String id = request.queryParams("id");
                String title = request.queryParams("title");
                String categid = request.queryParams("category_id");
                String price = request.queryParams("price");
                String currency_id = request.queryParams("currency_id");
                String available_quantity = request.queryParams("available_quantity");
                String buying_mode = request.queryParams("buying_mode");
                String condition = request.queryParams("condition");
                String description = request.queryParams("description");
                String video_id = request.queryParams("video_id");
                String warranty = request.queryParams("warranty");

                Item item=new Item();
                item.setId(id);
                item.setTitle(title);
                item.setCategory_id(categid);
                item.setPrice(price);
                item.setCurrency_id(currency_id);
                item.setAvailable_quantity(available_quantity);
                item.setBuying_mode(buying_mode);
                item.setCondition(condition);
                item.setDescription(description);
                item.setVideo_id(video_id);
                item.setWarranty(warranty);

                Pictures[] pictures=new Pictures[1];
                Pictures pic=new Pictures();
                pic.setSource("img_url");
                pictures[0]=pic;
                item.setPictures(pictures);

                //if(item.checkNull())
                //    throw new ItemException("No se han completado todos los campos requeridos");
                itemService.addItem(item);

                response.status(201);
                response.redirect("/list");
                return "";
            });

            post("/item/edit/:id", (request, response) -> {
                String id = request.queryParams("id");
                String title = request.queryParams("title");
                String categid = request.queryParams("category_id");
                String price = request.queryParams("price");
                String currency_id = request.queryParams("currency_id");
                String available_quantity = request.queryParams("available_quantity");
                String buying_mode = request.queryParams("buying_mode");
                String condition = request.queryParams("condition");
                String description = request.queryParams("description");
                String video_id = request.queryParams("video_id");
                String warranty = request.queryParams("warranty");

                Item item=new Item();
                item.setId(id);
                item.setTitle(title);
                item.setCategory_id(categid);
                item.setPrice(price);
                item.setCurrency_id(currency_id);
                item.setAvailable_quantity(available_quantity);
                item.setBuying_mode(buying_mode);
                item.setCondition(condition);
                item.setDescription(description);
                item.setVideo_id(video_id);
                item.setWarranty(warranty);

                Pictures[] pictures=new Pictures[1];
                Pictures pic=new Pictures();
                pic.setSource("img_url");
                pictures[0]=pic;
                item.setPictures(pictures);

                //if(item.checkNull())
                //    throw new ItemException("No se han completado todos los campos requeridos");
                itemService.editUsuario(item);

                response.status(201);
                response.redirect("/list");

                return "";
            });
        }
        catch (Exception ex)
        {
            ex.toString();
        }

    }
}
