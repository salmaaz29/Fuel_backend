package ma.fstt.ws_rest_api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.fstt.ws_rest_api.services.StationService;
import ma.fstt.ws_rest_api.entities.Station;

import java.util.List;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationResource {

    private StationService stationService = new StationService();

    // GET - Récupérer toutes les stations
    @GET
    public Response getAllStations() {
        try {
            List<Station> stations = stationService.getAllStations();
            return Response.ok(stations).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // GET - Récupérer une station par ID
    @GET
    @Path("/{id}")
    public Response getStationById(@PathParam("id") Long id) {
        try {
            Station station = stationService.getStationById(id);
            if (station != null) {
                return Response.ok(station).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Station non trouvée").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // POST - Créer une nouvelle station
    @POST
    public Response createStation(Station station) {
        try {
            Station createdStation = stationService.createStation(station);
            return Response.status(Response.Status.CREATED)
                    .entity(createdStation).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // PUT - Mettre à jour une station
    @PUT
    @Path("/{id}")
    public Response updateStation(@PathParam("id") Long id, Station station) {
        try {
            Station updatedStation = stationService.updateStation(id, station);
            if (updatedStation != null) {
                return Response.ok(updatedStation).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Station non trouvée").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // DELETE - Supprimer une station
    @DELETE
    @Path("/{id}")
    public Response deleteStation(@PathParam("id") Long id) {
        try {
            stationService.deleteStation(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }
}