import java.util.HashSet;
import java.util.Set;

/*Represents a User in the social network , simple data container as per Design M1.*/
public class User {
    private String userId;
    private String name;
    private Set<String> interests;
    private Set<String> activities;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.interests = new HashSet<>();
        this.activities = new HashSet<>();
    }

    public void addInterest(String interest) {
        this.interests.add(interest);
    }

    public void addActivity(String activity) {
        this.activities.add(activity);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public Set<String> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return name + " (" + userId + ")";
    }
}