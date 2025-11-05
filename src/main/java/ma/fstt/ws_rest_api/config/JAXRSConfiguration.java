package ma.fstt.ws_rest_api.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class JAXRSConfiguration extends Application {
    // Cette classe active JAX-RS avec le chemin de base "/api"
}