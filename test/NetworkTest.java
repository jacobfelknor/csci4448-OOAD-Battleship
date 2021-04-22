import edu.colorado.fantasticfour.networking.Client;
import edu.colorado.fantasticfour.networking.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.fail;

public class NetworkTest {
    private Server server;
    private Client client;

    @Before
    public void setUp() throws IOException {
        server = new Server(5000);
        server.startConnectionThread();
        client = new Client("127.0.0.1", 5000);
    }

    @After
    public void tearDown() throws IOException {
        client.destroy();
        server.destroy();
    }

    @Test
    public void sendMsgOverSocket(){
        try {
            String msg = "Hello World! :)";
            String msg2 = "This is a test. Sending over socket...";
            client.writeToSocket(msg);
            Assert.assertEquals(msg, server.readLineFromSocket());
            client.writeToSocket(msg2);
            Assert.assertEquals(msg2, server.readLineFromSocket());
        }catch (IOException e){
            fail();
        }
    }
}
