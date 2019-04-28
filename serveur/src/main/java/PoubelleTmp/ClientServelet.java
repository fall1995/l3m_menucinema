package PoubelleTmp;

import javax.servlet.http.HttpServlet;

/**
import database.GestionnaireClient;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import l3m.ClientEnregistreServlet;

/**
 * @author Groupe6 
 * ClientUpdateServelet permet de mettre a jour les informations
 * consernant un client
 */

//@Path("api/client")
public class ClientServelet extends HttpServlet {

//    /**
//     *
//     * @param idClient
//     * @param nom
//     * @param prenom
//     * @param servletResponse
//     * @throws IOException
//     */
//    @POST
//    public void createClient(@FormParam("idClient") String idClient,
//            @FormParam("nom") String nom,
//            @FormParam("prenom") String prenom,
//            @Context HttpServletResponse servletResponse) throws IOException {
//        try {
//            GestionnaireClient gestionClient = new GestionnaireClient(idClient, nom, prenom);
//            System.out.print(gestionClient.getNom());
//            gestionClient.enregistreClientDB();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     *
//     * @param idClient
//     * @param nom
//     * @param prenom
//     * @param photo
//     * @param email
//     * @param tel
//     * @param adresse
//     * @return
//     */
//    @PUT
//    public Response putClient(@PathParam("idClient") String idClient,@PathParam("nom") 
//            String nom,@PathParam("prenom") String prenom,@PathParam("photo")
//            String photo,@PathParam("email") String email,@PathParam("tel") 
//            String tel,@PathParam("adresse") String adresse) {
//        Response response = null;
//        try {
//            GestionnaireClient gestionClient = new GestionnaireClient(idClient,
//                    nom, prenom);
//            gestionClient.editClientDB();
//        } catch (SQLException ex) {
//            Logger.getLogger(ClientEnregistreServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return response;
//    }
//
//    /**
//     *
//     * @param idClient
//     * @throws SQLException
//     */
//    @DELETE
//    @Path("/{idClient}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public void deleteClient(@PathParam("idClient") String idClient) throws SQLException {
//        GestionnaireClient gestionclientsup = new GestionnaireClient(idClient);
//        gestionclientsup.deleteClientId(idClient);
//    }
}
