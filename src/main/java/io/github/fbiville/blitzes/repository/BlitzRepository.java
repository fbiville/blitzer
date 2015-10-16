package io.github.fbiville.blitzes.repository;

import io.github.fbiville.blitzes.domain.Blitz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BlitzRepository extends GraphRepository<Blitz> {

    @Query("MATCH (b:Blitz)-[:MENTIONS]->(:Tag {name: {tag}}) RETURN b ORDER BY ID(b) DESC")
    Collection<Blitz> findByHashTag(@Param("tag") String name);
    @Query("MATCH (b:Blitz)-[:MENTIONS]->(:Blitzer {login: {login}}) RETURN b ORDER BY ID(b) DESC")
    Collection<Blitz> findByInteractions(@Param("login") String login);
    @Query("MATCH (:Blitzer {login:{blitzer}})-[p:PUBLISHED {deleted:{deleted}}]->(b:Blitz) RETURN b ORDER BY p.creation_date DESC")
    Collection<Blitz> findByAuthorLoginAndDeletedStatus(@Param("blitzer") String login, @Param("deleted") boolean delete);
}
