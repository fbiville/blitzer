package io.github.fbiville.blitzers.domain;

import io.github.fbiville.blitzes.domain.Published;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@NodeEntity(label = "Blitzer")
public class Blitzer {

    private Long id;
    private String login;
    private Collection<Published> posts = new ArrayList<>();
    private Collection<Follows> followers = new ArrayList<>();

    @GraphId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Property(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Relationship
    public Collection<Published> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Published> posts) {
        this.posts = posts;
    }

    @Relationship
    public Collection<Follows> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<Follows> posts) {
        this.followers = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blitzer that = (Blitzer) o;
        return Objects.equals(login, that.login)
                && Objects.equals(posts, that.posts)
                && Objects.equals(followers, that.followers);

    }

    @Override
    public int hashCode() {
        return Objects.hash(login, posts, followers);
    }
}
