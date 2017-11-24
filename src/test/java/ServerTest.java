import org.academiadecodigo.hackathon.jesusfindrserver.server.Server;
import org.academiadecodigo.hackathon.jesusfindrserver.services.user.JdbcUserService;
import org.academiadecodigo.hackathon.jesusfindrserver.services.user.MockUserService;

public class ServerTest {

    public static void main(String[] args) {

        Server server = new Server();

        server.setUserService(new JdbcUserService());

        server.start();

    }
}
