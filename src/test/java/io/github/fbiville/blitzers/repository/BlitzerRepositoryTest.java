package io.github.fbiville.blitzers.repository;

import io.github.fbiville.TestBlitzBlogging;
import io.github.fbiville.blitzers.domain.Blitzer;
import io.github.fbiville.blitzers.domain.BlitzerPopularity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestBlitzBlogging.class)
@TransactionConfiguration
@Transactional
public class BlitzerRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    Neo4jServer server;

    @Autowired
    BlitzerRepository blitzerRepository;

    @Before
    public void prepare_graph() throws Exception {
        Session session = sessionFactory.openSession(server.url());
        String query = "" +
                "CREATE (jb:Blitzer {login:'§james_bond'})-[:FOLLOWS {creation_date:2000}]->(:Blitzer {login:'§ed_snowden'})," +
                "       (jb)-[:FOLLOWS {creation_date:1000}]->(ap:Blitzer {login:'§a_powers'})," +
                "       (ap)-[:FOLLOWS {creation_date:3000}]->(de:Blitzer {login:'§dr_evil'})";
        new Neo4jTemplate(session).execute(query, new HashMap<>(0));
    }

    @Test
    public void finds_inserted_user() {
        Blitzer blitzer = blitzerRepository.findByLogin("§james_bond");

        assertThat(blitzer.getId()).isGreaterThanOrEqualTo(0);
        assertThat(blitzer.getLogin()).isEqualTo("§james_bond");
    }

    @Test
    public void finds_users_followed_by_a_specific_user() {
        Collection<Blitzer> followees = blitzerRepository.findFollowees("§james_bond");

        assertThat(followees)
                .extracting(Blitzer::getLogin)
                .containsExactly("§ed_snowden", "§a_powers");
    }

    @Test
    public void computes_popularity() {
        BlitzerPopularity popularity = blitzerRepository.findPopularityByLogin("§james_bond");

        assertThat(popularity.getScore()).isEqualTo(0);
    }
}