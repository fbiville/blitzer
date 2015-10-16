package io.github.fbiville.blitzers.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class BlitzerPopularity {
    public String login;
    public Long number_of_mentions;
    public Long number_of_reblitzes;
    public Long number_of_blitzes;

    public int getScore() {
        if (isInitialized()) {
            throw new IllegalStateException("Not initialized!");
        }
        return (int) (Math.log(1 + number_of_reblitzes / number_of_blitzes) * number_of_mentions);
    }

    private boolean isInitialized() {
        return login != null
                && number_of_mentions != null
                && number_of_reblitzes != null
                && number_of_blitzes != null;
    }
}
