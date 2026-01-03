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

    /* Pre: interest is a non-empty string.
       Post: the interest is added to the user's interest set and duplicates will
       be ignored */ 

    public void addInterest(String interest) {
        this.interests.add(interest);
    }

     /*Pre: activity is non-empty string.
       Post: the activity is added to the user's activity set and duplicates will of course
       be ignored */
    public void addActivity(String activity) {
        this.activities.add(activity);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    /*Pre: none
      Post: Returns the Set of interests for the user. */
    public Set<String> getInterests() {
        return interests;
    }
  
    
    /* Pre: none
       Post: Returns the Set of activities for the user */
    public Set<String> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return name + " (" + userId + ")";
    }
}
