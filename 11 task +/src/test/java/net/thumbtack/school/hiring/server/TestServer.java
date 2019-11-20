package net.thumbtack.school.hiring.server;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.File;

public class TestServer {

    private Server server = new Server();

    @Test
    public void testServer() {
        File file = new File("1.txt");
        server.startServer(file.getName());
        server.stopServer(file.getName());
        assertTrue(file.exists());
    }

}
