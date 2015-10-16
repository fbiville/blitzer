package io.github.fbiville.blitzes.repository;

import io.github.fbiville.TestBlitzBlogging;
import io.github.fbiville.blitzes.domain.Blitz;
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
public class BlitzRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    Neo4jServer server;

    @Autowired
    BlitzRepository blitzRepository;

    @Before
    public void prepare_graph() throws Exception {
        Session session = sessionFactory.openSession(server.url());
        String query = "" +
                "CREATE (a:Blitz {contents:'I <3 #Neo4j!'})-[:MENTIONS]->(t:Tag {name: '#Neo4j'})," +
                "       (b:Blitz {contents:'I <3 $NEOT!'})-[:MENTIONS]->(:Tag {name: '$NEOT'})," +
                "       (c:Blitz {contents:'I really <3 #Neo4j!!'})-[:MENTIONS]->(t)," +
                "       (mhunger:Blitzer {login:'§mesirii'})," +
                "       (d:Blitz {contents:'Nice work §mesirii!'})-[:MENTIONS]->(mhunger)," +
                "       (a)<-[:PUBLISHED {deleted:false, creation_date:1000}]-(mhunger)," +
                "       (b)<-[:PUBLISHED {deleted:true,  creation_date:2000}]-(mhunger)," +
                "       (c)<-[:PUBLISHED {deleted:false, creation_date:3000}]-(mhunger)";
        new Neo4jTemplate(session).execute(query, new HashMap<>(0));
    }

    @Test
    public void finds_blitzes_by_tag() {
        Collection<Blitz> blitzes = blitzRepository.findByHashTag("#Neo4j");

        assertThat(blitzes)
                .extracting(Blitz::getContents)
                .containsOnlyOnce("I <3 #Neo4j!", "I really <3 #Neo4j!!");
    }

    @Test
    public void finds_blitzes_by_interactions() {
        Collection<Blitz> blitzes = blitzRepository.findByInteractions("§mesirii");

        assertThat(blitzes)
                .extracting(Blitz::getContents)
                .containsExactly("Nice work §mesirii!");
    }

    @Test
    public void find_blitzes_by_blitzers() {
        Collection<Blitz> blitzes = blitzRepository.findByAuthorLoginAndDeletedStatus("§mesirii", false);

        assertThat(blitzes)
                .extracting(Blitz::getContents)
                .containsExactly("I really <3 #Neo4j!!", "I <3 #Neo4j!");
    }
}