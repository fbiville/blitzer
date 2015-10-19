package io.github.fbiville.blitzers.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Objects;

@QueryResult
public class BlitzerPopularity {

    private String login;
    private Long interactionCount;
    private Long reblitzCount;
    private Long blitzCount;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getInteractionCount() {
        return interactionCount;
    }

    public void setInteractionCount(Long interactionCount) {
        this.interactionCount = interactionCount;
    }

    public Long getReblitzCount() {
        return reblitzCount;
    }

    public void setReblitzCount(Long reblitzCount) {
        this.reblitzCount = reblitzCount;
    }

    public Long getBlitzCount() {
        return blitzCount;
    }

    public void setBlitzCount(Long blitzCount) {
        this.blitzCount = blitzCount;
    }

    public int getScore() {
        if (isInitialized()) {
            throw new IllegalStateException("Not initialized!");
        }
        return (int) (Math.log(1 + reblitzCount / (1+blitzCount)) * Math.pow(10, interactionCount));
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, interactionCount, reblitzCount, blitzCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final BlitzerPopularity other = (BlitzerPopularity) obj;
        return Objects.equals(this.login, other.login)
                && Objects.equals(this.interactionCount, other.interactionCount)
                && Objects.equals(this.reblitzCount, other.reblitzCount)
                && Objects.equals(this.blitzCount, other.blitzCount);
    }

    private boolean isInitialized() {
        return login != null
                && interactionCount != null
                && reblitzCount != null
                && blitzCount != null;
    }
}
