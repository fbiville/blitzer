package io.github.fbiville.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "io.github.fbiville", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class)
})
@EnableTransactionManagement
public class BlitzerDataModule extends Neo4jConfiguration {

    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474", "neo4j", "toto");
    }

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(
            "io.github.fbiville.blitzes",
            "io.github.fbiville.blitzers"
        );
    }

}
