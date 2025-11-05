package ma.fstt.ws_rest_api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.fstt.ws_rest_api.entities.HistCarb;
import ma.fstt.ws_rest_api.services.HistCarbService;

import java.util.List;

@Path("/prix")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrixResource {

    private HistCarbService histCarbService = new HistCarbService();

    // Ajouter un prix
    @POST
    public Response ajouterPrix(HistCarb histCarb) {
        try {
            HistCarb saved = histCarbService.ajouterPrix(histCarb);
            return Response.status(Response.Status.CREATED).entity(saved).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // Obtenir l'historique des prix d'une station
    @GET
    @Path("/station/{stationId}")
    public Response getHistoriqueByStation(@PathParam("stationId") Long stationId) {
        try {
            List<HistCarb> historique = histCarbService.getHistoriqueByStation(stationId);
            return Response.ok(historique).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }

    // Obtenir le prix actuel d'un carburant dans une station
    @GET
    @Path("/station/{stationId}/carburant/{carburantId}")
    public Response getPrixActuel(
            @PathParam("stationId") Long stationId,
            @PathParam("carburantId") Long carburantId) {

        try {
            HistCarb prixActuel = histCarbService.getPrixActuel(stationId, carburantId);
            if (prixActuel != null) {
                return Response.ok(prixActuel).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prix non trouvé").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur: " + e.getMessage()).build();
        }
    }
}