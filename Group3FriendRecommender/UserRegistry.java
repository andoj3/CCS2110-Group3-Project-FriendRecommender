import java.util.HashMap;
import java.util.Map;

/*UserRegistry implementation using a HashMap for fast O(1) lookups.Stores the actual User objects.*/
public class UserRegistry implements UserRegistryADT {
    
    private Map<String, User> userMap;

    public UserRegistry() {
        this.userMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    @Override
    public User getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public boolean containsUser(String userId) {
        return userMap.containsKey(userId);
    }
}