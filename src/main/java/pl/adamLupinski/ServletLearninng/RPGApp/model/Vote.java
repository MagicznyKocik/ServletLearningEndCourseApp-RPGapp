package pl.adamLupinski.ServletLearninng.RPGApp.model;

import java.sql.Timestamp;

public class Vote {

    private long id;
    private long discoveryId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(long id, long discoveryId, long userId, Timestamp date, VoteType voteType) {
        this.id = id;
        this.discoveryId = discoveryId;
        this.userId = userId;
        this.date = date;
        this.voteType = voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDiscoveryId() {
        return discoveryId;
    }

    public void setDiscoveryId(long discoveryId) {
        this.discoveryId = discoveryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", discoveryId=" + discoveryId +
                ", userId=" + userId +
                ", date=" + date +
                ", voteType=" + voteType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (discoveryId != vote.discoveryId) return false;
        if (userId != vote.userId) return false;
        if (date != null ? !date.equals(vote.date) : vote.date != null) return false;
        return voteType != null ? voteType.equals(vote.voteType) : vote.voteType == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (discoveryId ^ (discoveryId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (voteType != null ? voteType.hashCode() : 0);
        return result;
    }
}
