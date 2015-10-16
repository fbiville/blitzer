package io.github.fbiville.blitzes.domain;

import io.github.fbiville.blitzers.domain.Blitzer;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity(label = "Blitz")
public class Blitz {

    private Long id;
    private String contents;
    private Collection<Blitzer> reblitzers = new ArrayList<>();
    private Collection<Blitzer> interactions = new ArrayList<>();
    private Collection<HashTag> tags = new ArrayList<>();

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Property(name = "contents")
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Relationship(type = "REBLITZES", direction = INCOMING)
    public Collection<Blitzer> getReblitzers() {
        return reblitzers;
    }

    public void setReblitzers(Collection<Blitzer> reblitzers) {
        this.reblitzers = reblitzers;
    }

    @Relationship(type = "MENTIONS", direction = OUTGOING)
    public Collection<Blitzer> getInteractions() {
        return interactions;
    }

    public void setInteractions(Collection<Blitzer> interactions) {
        this.interactions = interactions;
    }

    @Relationship(type = "MENTIONS", direction = OUTGOING)
    public Collection<HashTag> getTags() {
        return tags;
    }

    public void setTags(Collection<HashTag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blitz that = (Blitz) o;

        return Objects.equals(contents, that.contents)
                && Objects.equals(reblitzers, that.reblitzers)
                && Objects.equals(interactions, that.interactions)
                && Objects.equals(tags, that.tags);

    }

    @Override
    public int hashCode() {
        return Objects.hash(contents, reblitzers, interactions, tags);
    }
}
