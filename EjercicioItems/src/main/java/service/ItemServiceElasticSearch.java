package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.Item;
import model.ItemException;
import model.Pictures;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ItemServiceElasticSearch implements ItemService {

    private static RestHighLevelClient client=null;
    private static IndexRequest request;

    public ItemServiceElasticSearch()
    {

        try
        {
            //Iniciamos el cliente para comunicarnos con elasticsearch
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http"),
                            new HttpHost("localhost", 9201, "http")
                    )
            );

            Item item=new Item();
            item.setId("ML0001");
            item.setTitle("Item prueba.");
            item.setAvailable_quantity("2");
            item.setCurrency_id("ARS");
            item.setPrice("80");
            item.setCondition("new");
            item.setDescription("Producto desc");
            item.setListing_type_id("bronze");
            item.setBuying_mode("buy_it_now");
            item.setCategory_id("MLA5529");
            item.setVideo_id("youtube_ID");
            item.setWarranty("desc");

            Pictures[] pictures=new Pictures[1];
            Pictures pic=new Pictures();
            pic.setSource("img_url");
            pictures[0]=pic;
            item.setPictures(pictures);

             //Creamos un indice items vacio
            request = new IndexRequest(
                    "items",
                    "doc",
                    item.getId());

            Gson gson = new Gson();
            String jsonInString = gson.toJson(item);

            request.source(jsonInString, XContentType.JSON);
            //hacemos la llamada a ES
            IndexResponse indexResponse = client.index(request);

            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            DocWriteResponse.Result result=indexResponse.getResult();
            String resultado=result.toString();

        }
        catch (Exception ex)
        {
            String mess=ex.getMessage();
        }

    }

    @Override
    public void addItem(Item item)  throws ItemException{
        try
        {
            Gson gson = new Gson();

            request = new IndexRequest(
                    "items",
                    "doc",
                    item.getId());

            String jsonInString = gson.toJson(item);

            request.source(jsonInString, XContentType.JSON);
            //hacemos la llamada a ES
            IndexResponse indexResponse = client.index(request);

            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            DocWriteResponse.Result result=indexResponse.getResult();
            String resultado=result.toString();

        }
        catch (Exception ex)
        {
            throw  new ItemException(ex.getMessage());
        }
    }

    @Override
    public Item getItem(String id) throws ItemException{
        try
        {
            Gson objGson = new GsonBuilder().setPrettyPrinting().create();

            GetRequest getRequest = new GetRequest(
                "items",
                "doc",
                id);

            Item itget;

            GetResponse getResponse = client.get(getRequest);
            if(getResponse.isExists())
            {
                String mapToJson = objGson.toJson(getResponse.getSource());
                itget=objGson.fromJson(mapToJson,Item.class);
            }
            else
               throw  new ItemException("El Item no existe");

            return itget;
        }
        catch (Exception ex)
        {
            throw  new ItemException(ex.getMessage());
        }
    }

    public List<Item> getAll() throws ItemException//get
    {
        List<Item> items=new ArrayList<Item>();

        try
        {
            Gson objGson = new GsonBuilder().setPrettyPrinting().create();

            SearchRequest searchRequest = new SearchRequest("items");
            searchRequest.types("doc");

            SearchResponse searchResponse = client.search(searchRequest);
            SearchHits sh =searchResponse.getHits();

            for (SearchHit hit : sh.getHits()) {

                Map<String,Object> mapa=hit.getSourceAsMap();

                String mapToJson = objGson.toJson(mapa);
                Item it=objGson.fromJson(mapToJson,Item.class);
                items.add(it);
            }
        }
        catch (Exception ex)
        {
            throw  new ItemException(ex.getMessage());
        }

        return items;
    }

    @Override
    public void editUsuario(Item item)  throws ItemException{

        try
        {
            String jsonString=new Gson().toJson(item);
            UpdateRequest request = new UpdateRequest(
                    "items",
                    "doc",
                    item.getId()).doc(jsonString, XContentType.JSON);

            UpdateResponse updateResponse = client.update(request);
        }
        catch (Exception ex)
        {
            throw  new ItemException(ex.getMessage());
        }
    }

    @Override
    public void deleteItem(String id)  throws ItemException{

        try
        {
            DeleteRequest request = new DeleteRequest(
                    "items",
                    "doc",
                    id);

            DeleteResponse deleteResponse = client.delete(request);
            DocWriteResponse.Result rst=deleteResponse.getResult();

            if(rst.toString().equals("NOT_FOUND"))
                throw  new ItemException("El Item no existe");

        }
        catch (Exception ex)
        {
            throw  new ItemException(ex.getMessage());
        }

    }
}
