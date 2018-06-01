package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import model.Item;
import model.ItemException;
import model.StandarResponse;
import model.StatusResponse;
import org.eclipse.jetty.http.HttpStatus;
import service.ItemService;
import service.ItemServiceElasticSearch;

import java.util.List;

import static spark.Spark.*;

public class ItemController {

    public static void main(String [] args)
    {
        port(9393);

        try
        {
            //final ItemService itemService=new ItemServiceHashMap();
            final ItemService itemService=new ItemServiceElasticSearch();

            get("/items/:id",(request,response)->{
                response.type("application/json");
                String id = request.params(":id");

                if(!id.equals("all"))
                {
                    try
                    {
                        Item item=itemService.getItem(id);
                        return new Gson().toJson(item);
                    }
                    catch (Exception ex)
                    {
                        StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                        sr.setMessage(ex.getMessage());
                        return new Gson().toJson(sr);
                    }
                }
                else
                {
                    try
                    {
                        List<Item> items=itemService.getAll();
                        return new Gson().toJson(items);
                    }
                    catch (Exception ex)
                    {
                        StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                        sr.setMessage(ex.getMessage());
                        return new Gson().toJson(sr);
                    }
                }
            });

            post("/items/:id",(request,response)->{
                response.type("application/json");
                String id = request.params(":id");
                try
                {
                    Item item=new Gson().fromJson(request.body(),Item.class);
                    if(item.checkNull())
                        throw new ItemException("No se han completado todos los campos requeridos");
                    item.setId(id);
                    itemService.addItem(item);
                    return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS));
                }
                catch (JsonSyntaxException ex)
                {
                    StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                    sr.setMessage("Json mal formado");
                    response.status(HttpStatus.BAD_REQUEST_400);
                    return new Gson().toJson(sr);
                }
                catch (Exception ex)
                {
                    StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                    sr.setMessage(ex.getMessage());
                    return new Gson().toJson(sr);
                }
            });

            put("/items/:id",(request,response)->{
                response.type("application/json");
                String id = request.params(":id");
                try
                {
                    Item item=new Gson().fromJson(request.body(),Item.class);
                    if(item.checkNull())
                        throw new ItemException("No se han completado todos los campos requeridos");
                    item.setId(id);
                    itemService.editUsuario(item);
                    return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS));
                }
                catch (JsonSyntaxException ex)
                {
                    StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                    sr.setMessage("Json mal formado");
                    response.status(HttpStatus.BAD_REQUEST_400);
                    return new Gson().toJson(sr);
                }
                catch (Exception ex)
                {
                    StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                    sr.setMessage(ex.getMessage());
                    return new Gson().toJson(sr);
                }
            });

            delete("/items/:id",(request,response)->{
                response.type("application/json");
                String id = request.params(":id");
                try
                {
                    itemService.deleteItem(id);
                    return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS));
                }
                catch (Exception ex)
                {
                    StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
                    sr.setMessage(ex.getMessage());
                    return new Gson().toJson(sr);
                }
            });
        }
        catch (Exception ex)
        {
            StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
            sr.setMessage(ex.getMessage());
        }

    }
}
