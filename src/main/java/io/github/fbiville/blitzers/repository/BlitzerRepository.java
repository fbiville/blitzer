package io.github.fbiville.blitzers.repository;


import io.github.fbiville.blitzers.domain.Blitzer;
import io.github.fbiville.blitzers.domain.BlitzerPopularity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BlitzerRepository extends GraphRepository<Blitzer> {

    Blitzer findByLogin(String login);
    @Query("MATCH (u:Blitzer)<-[f:FOLLOWS]-(:Blitzer {login: {login}}) RETURN u ORDER BY f.creation_date DESC")
    Collection<Blitzer> findFollowees(@Param("login") String login);
    @Query("OPTIONAL MATCH (me:Blitzer {login:{blitzer}})-[:PUBLISHED {deleted:false}]->(blitz:Blitz)," +
            "               (blitz)<-[reblitz:REBLITZES]-(:Blitzer)," +
            "               (me)<-[interaction:MENTIONS]-(:Blitz)<-[:PUBLISHED {deleted:false}]-(:Blitzer) " +
            "RETURN me.login AS login, " +
            "       COUNT(interaction) AS interactionCount, " +
            "       COUNT(reblitz) AS reblitzCount, " +
            "       COUNT(blitz) AS blitzCount")
    BlitzerPopularity findPopularityByLogin(@Param("login") String login);
}
