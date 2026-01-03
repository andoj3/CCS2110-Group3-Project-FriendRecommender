import java.util.Set;

// Interface defining the Social Graph operations
public interface SocialGraphADT {
    /* Pre: userId is valid and not already in the graph.
       Post: Creates a new node for this user with no friends yet. */
    public void addUser(String userId);

    /* Pre: Both User IDs must already exist in the graph.
       Post: Adds both users to each other's friend lists (mutual). */
    public void addFriendship(String userId1, String userId2);

    /* Pre: userId exists in the graph.
       Post: Returns a Set of all their direct friends. */
    public Set<String> getFriends(String userId);

    // Helper methods for testing
    public boolean areFriends(String userId1, String userId2);
    public int getSize();

}