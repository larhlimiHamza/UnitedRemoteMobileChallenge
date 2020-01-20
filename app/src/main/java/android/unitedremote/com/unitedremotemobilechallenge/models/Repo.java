package android.unitedremote.com.unitedremotemobilechallenge.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;



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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Repo(String name, String description, Owner owner, Integer stargazersCount) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stargazersCount = stargazersCount;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", stargazersCount=" + stargazersCount +
                '}';
    }
}