package ch.epfl.javass.jass;

import java.util.Map;

/**
 * Represents a player that will wait a while before playing.
 *
 * @author Lucas Meier (283726)
 * @author Maxime Laval (287323)
 */
public final class PacedPlayer implements Player {

    private static final int NB_OF_MILLISEC_IN_ONE_SEC = 1_000;

    private final long minTime;
    private final Player underlyingPlayer;

    /**
     * Constructs a paced player with the minimum time that he / she will have to wait.
     * @param underlyingPlayer the underlying player.
     * @param minTime          the minimum time the player will have to wait before playing.
     */
    public PacedPlayer(Player underlyingPlayer, double minTime) {
        if (minTime < 0)
            throw new IllegalArgumentException();
        this.underlyingPlayer = underlyingPlayer;
        this.minTime = (long) (minTime * NB_OF_MILLISEC_IN_ONE_SEC);
    }

    @Override
    public Card cardToPlay(TurnState state, CardSet hand) {
        long startTime = System.currentTimeMillis();
        Card card = underlyingPlayer.cardToPlay(state, hand);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        if (elapsedTime < minTime) {
            try {
                Thread.sleep(minTime - (elapsedTime));
            } catch (InterruptedException e) {/* ignore */}
        }
        return card;
    }

    @Override
    public void setPlayers(PlayerId ownId, Map<PlayerId, String> playerNames) {
        underlyingPlayer.setPlayers(ownId, playerNames);
    }

    @Override
    public void updateHand(CardSet newHand) {
        underlyingPlayer.updateHand(newHand);
    }

    @Override
    public void setTrump(Card.Color trump) {
        underlyingPlayer.setTrump(trump);
    }

    @Override
    public void updateTrick(Trick newTrick) {
        underlyingPlayer.updateTrick(newTrick);
    }

    @Override
    public void updateScore(Score score) {
        underlyingPlayer.updateScore(score);
    }

    @Override
    public void setWinningTeam(TeamId winningTeam) {
        underlyingPlayer.setWinningTeam(winningTeam);
    }
}
