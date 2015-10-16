package io.github.fbiville;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.server.InProcessServer;
import org.springframework.data.neo4j.server.Neo4jServer;

@Configuration
@Import(BlitzBlogging.class)
public class TestBlitzBlogging {

    @Bean
    public Neo4jServer neo4jServer() {
        return new InProcessServer();
    }
}
