package pl.adamLupinski.ServletLearninng.RPGApp.model;

import java.sql.Timestamp;

public class Idea {

    private long id;
    private String name;
    private String description;
    private String url;
    private Timestamp timestamp;
    private User user;
    private int upVote;
    private int downVote;


    public Idea() {
    }

    public Idea(long id, String name, String description, String url, Timestamp timestamp, User user, int upVote, int downVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.timestamp = timestamp;
        this.user = user;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public Idea(Idea idea){
        this.id = idea.id;
        this.name = idea.name;
        this.description = idea.description;
        this.url = idea.url;
        this.timestamp = idea.timestamp;
        this.user = idea.user;
        this.upVote = idea.upVote;
        this.downVote = idea.downVote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        if (id != idea.id) return false;
        if (upVote != idea.upVote) return false;
        if (downVote != idea.downVote) return false;
        if (name != null ? !name.equals(idea.name) : idea.name != null) return false;
        if (description != null ? !description.equals(idea.description) : idea.description != null) return false;
        if (url != null ? !url.equals(idea.url) : idea.url != null) return false;
        if (timestamp != null ? !timestamp.equals(idea.timestamp) : idea.timestamp != null) return false;
        return user != null ? user.equals(idea.user) : idea.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + upVote;
        result = 31 * result + downVote;
        return result;
    }
}
