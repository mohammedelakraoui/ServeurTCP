/**
 * Created by server-pc on 31/03/14.
 */
@Path("/json/product")
public class JsonService {

    @GET
    @Path("/get")
    @Produces("application/json")
    public  Product getProductInJSON() {

        Product product = new Product();
        product.setName("iPad 3");
        product.setQty(999);

        return product;

    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(Product product) {

        String result = "Product created : " + product;
        return Response.status(201).entity(result).build();

    }

}

