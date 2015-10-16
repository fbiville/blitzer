package io.github.fbiville.blitzes.domain;

import io.github.fbiville.blitzers.domain.Blitzer;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;
import java.util.Objects;

@RelationshipEntity(type = "PUBLISHED")
public class Published {

    private Long id;
    private Blitzer author;
    private Blitz blitz;
    private Date creationDate = new Date();
    private boolean deleted = false;

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @StartNode
    public Blitzer getAuthor() {
        return author;
    }

    public void setAuthor(Blitzer author) {
        this.author = author;
    }

    @EndNode
    public Blitz getBlitz() {
        return blitz;
    }

    public void setBlitz(Blitz blitz) {
        this.blitz = blitz;
    }

    @DateLong
    @Property(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Property(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Published that = (Published) o;
        return Objects.equals(author, that.author)
                && Objects.equals(blitz, that.blitz)
                && Objects.equals(creationDate, that.creationDate)
                && Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, blitz, creationDate, deleted);
    }
}
