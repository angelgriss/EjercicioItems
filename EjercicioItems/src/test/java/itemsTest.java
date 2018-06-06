import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import model.Item;
import model.Pictures;
import org.junit.*;


public class itemsTest {

    //private static String uriTest="http://localhost:9393";
    private static String uriTest="http://localhost:1500";

    @Test
    public void getItem_Validar_Status_200OK()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.get("/items/ML0001");
        int statusCod=response.getStatusCode();
        Assert.assertEquals("200",String.valueOf(statusCod));
    }

    @Test
    public void getItem_Validar_Item_Inexistente()
    {
        RestAssured.baseURI =uriTest;

        String rtdo =RestAssured.
                given().
                contentType("application/json").
                when().
                get("/items/dsdsdsdasda").
                then().
                statusCode(200).
                extract().response().asString();

        Assert.assertEquals(true,rtdo.contains("El Item no existe"));
    }

    @Test
    public void getAll_Validar_Status_200OK()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.get("/items/all");
        int statusCode=response.getStatusCode();
        Assert.assertEquals("200",String.valueOf(statusCode));
    }

    @Test
    public void postItem_Validar_Request_mal_formado()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Response response=httpRequest.body("asdf").post("/items/ML0001");

        Assert.assertEquals("400",String.valueOf(response.getStatusCode()));
    }

    @Test
    public void postItem_Validar_Status_200OK()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Item item=new Item();

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

        String reqbody= new Gson().toJson(item);

        Response response=httpRequest.body(reqbody).post("/items/ML000M1");

        Assert.assertEquals("200",String.valueOf(response.getStatusCode()));
    }

    @Test
    public void postItem_Validar_Campos_Nulos()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Item item=new Item();

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

        String reqbody= new Gson().toJson(item);

        Response response=httpRequest.body(reqbody).post("/items/ML000M1");

        Assert.assertEquals(true,response.then().extract().body().asString().contains("No se han completado todos los campos requeridos"));
    }

    @Test
    public void putItem_Validar_Campos_Nulos()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Item item=new Item();

        item.setAvailable_quantity("2");
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

        String reqbody= new Gson().toJson(item);

        Response response=httpRequest.body(reqbody).put("/items/ML000M1");

        Assert.assertEquals(true,response.then().extract().body().asString().contains("No se han completado todos los campos requeridos"));
    }

    @Test
    public void putItem_Validar_Request_mal_formado()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Response response=httpRequest.body("asdf").put("/items/ML000M1");

        Assert.assertEquals("400",String.valueOf(response.getStatusCode()));
    }

    @Test
    public void putItem_Validar_Status_200OK()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();

        Item item=new Item();

        item.setTitle("Item prueba update");
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

        String reqbody= new Gson().toJson(item);

        Response response=httpRequest.body(reqbody).put("/items/ML000M1");

        Assert.assertEquals("200",String.valueOf(response.getStatusCode()));
    }

    @Test
    public void putItem_Validar_Item_Inexistente()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();
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

        String reqbody= new Gson().toJson(item);
        String rtdo=httpRequest.body(reqbody).put("/items/asdasada").then().extract().response().asString();

        Assert.assertEquals(true,rtdo.contains("document missing"));
    }

    @Test
    public void deleteItem_Validar_Item_Inexistente()
    {
        RestAssured.baseURI =uriTest;

        String rtdo =RestAssured.
                given().
                contentType("application/json").
                when().
                delete("/items/dsdsdsdasda").
                then().
                statusCode(200).
                extract().response().asString();

        Assert.assertEquals(true,rtdo.contains("El Item no existe"));
    }

    @Test
    public void deleteItem_Validar_Status_200OK()
    {
        RestAssured.baseURI =uriTest;
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.delete("/items/ML000M1");
        int statusCode=response.getStatusCode();
        Assert.assertEquals("200",String.valueOf(statusCode));
    }
}
