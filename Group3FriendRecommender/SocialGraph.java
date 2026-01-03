import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*Implementation of SocialGraphADT using an Adjacency List.
We use a HashMap<String, Set<String>> for O(1) average access.*/
public class SocialGraph implements SocialGraphADT {
    
    // Key: UserID and  Value: Set of Friend UserIDs
    private Map<String, Set<String>> adjList;

    public SocialGraph() {
        this.adjList = new HashMap<>();
    }

    @Override
    public void addUser(String userId) {
        if (!adjList.containsKey(userId)) {
            adjList.put(userId, new HashSet<>());
        }
    }

    @Override
    public void addFriendship(String userId1, String userId2) {
        // Ensure both users exist in the graph first
        addUser(userId1);
        addUser(userId2);

        // Undirected graph: Add edge both ways
        adjList.get(userId1).add(userId2);
        adjList.get(userId2).add(userId1);
    }

    @Override
    public Set<String> getFriends(String userId) {
        // Return defensive copy or empty set if user not found
        return adjList.getOrDefault(userId, new HashSet<>());
    }

    @Override
    public boolean areFriends(String userId1, String userId2) {
        if (!adjList.containsKey(userId1)) return false;
        return adjList.get(userId1).contains(userId2);
    }

    @Override
    public int getSize() {
        return adjList.size();
    }
}