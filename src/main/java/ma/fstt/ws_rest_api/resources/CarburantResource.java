package ma.fstt.ws_rest_api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.fstt.ws_rest_api.entities.Carburant;
import ma.fstt.ws_rest_api.services.CarburantService;

import java.util.List;

@Path("/carburants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarburantResource {

    private CarburantService carburantService = new CarburantService();

    @GET
    public Response getAllCarburants() {
        try {
            List<Carburant> carburants = carburantService.getAllCarburants();
            return Response.ok(carburants).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getCarburantById(@PathParam("id") Long id) {
        try {
            Carburant carburant = carburantService.getCarburantById(id);
            if (carburant != null) {
                return Response.ok(carburant).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    @POST
    public Response createCarburant(Carburant carburant) {
        try {
            Carburant created = carburantService.createCarburant(carburant);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCarburant(@PathParam("id") Long id) {
        try {
            carburantService.deleteCarburant(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCarburant(@PathParam("id") Long id, Carburant carburant) {
        try {
            Carburant updated = carburantService.updateCarburant(id, carburant);
            if (updated != null) {
                return Response.ok(updated).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Carburant non trouvé").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }


}