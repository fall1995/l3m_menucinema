package l3m; 

import javax.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * @author Groupe6
 * PizzaServer
 */
public class PizzaServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Server server;
        
    /**
     *Methode qui permet de demarrer le server
     * @throws Exception
     */

    void start() throws Exception {
        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] { connector });

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(BlockingServlet.class, "/status");
        servletHandler.addServletWithMapping(ClientAuthentificationServlet.class, "/api/authentification");
<<<<<<< HEAD
        servletHandler.addServletWithMapping(ClientEnregistreServlet.class, "/api/enregistreNouveauClient");
        servletHandler.addServletWithMapping(UpdateClientServlet.class, "/api/updateClient");
=======
        servletHandler.addServletWithMapping(ClientEnregistreServlet.class, "/api/client");
        //servletHandler.addServletWithMapping(ClientServelet.class,"/api/client");
          
>>>>>>> a5714f3a19a4822d9c76d840b75c094e8dcc47b4
        server.start();
    }

    /**
     *Methode  qui permet d' arreter le server
     * @throws Exception
     */
    void stop() throws Exception {
        System.out.println("Server stop");
    	server.stop();
    }
    /**
     *Methode main qui permet de demarrer le server
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
		PizzaServer server = new PizzaServer();
		server.start();
	}

}
