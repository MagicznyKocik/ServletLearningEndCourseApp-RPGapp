package pl.adamLupinski.ServletLearninng.RPGApp.model;

import java.sql.Timestamp;

public class Vote {

    private long id;
    private long IdeaId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(Vote vote) {
        this.id = vote.id;
        this.IdeaId = vote.IdeaId;
        this.userId = vote.userId;
        this.date = new Timestamp(vote.date.getTime());
        this.voteType= vote.voteType;
    }

    public Vote(long id, long IdeaId, long userId, Timestamp date, VoteType voteType) {
        this.id = id;
        this.IdeaId = IdeaId;
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

    public long getIdeaId() {
        return IdeaId;
    }

    public void setIdeaId(long ideaId) {
        this.IdeaId = ideaId;
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
                ", IdeaId=" + IdeaId +
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
        if (IdeaId != vote.IdeaId) return false;
        if (userId != vote.userId) return false;
        if (date != null ? !date.equals(vote.date) : vote.date != null) return false;
        return voteType != null ? voteType.equals(vote.voteType) : vote.voteType == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (IdeaId ^ (IdeaId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (voteType != null ? voteType.hashCode() : 0);
        return result;
    }
}
