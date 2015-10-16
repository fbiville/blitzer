package io.github.fbiville.blitzers.domain;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "FOLLOWS")
public class Follows {

    private Long id;
    private Blitzer follower;
    private Blitzer followee;
    private Date creationDate = new Date();

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @StartNode
    public Blitzer getFollower() {
        return follower;
    }

    public void setFollower(Blitzer follower) {
        this.follower = follower;
    }

    @EndNode
    public Blitzer getFollowee() {
        return followee;
    }

    public void setFollowee(Blitzer followee) {
        this.followee = followee;
    }

    @DateLong
    @Property(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
