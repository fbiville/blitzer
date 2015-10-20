package io.github.fbiville;

import io.github.fbiville.configuration.BlitzerDataModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.server.InProcessServer;
import org.springframework.data.neo4j.server.Neo4jServer;

@Configuration
@Import(BlitzerDataModule.class)
public class TestBlitzBlogging {

    @Bean
    public Neo4jServer neo4jServer() {
        return new InProcessServer();
    }
}
