// Interface for the User Lookup System
public interface UserRegistryADT {
    /* Pre: User object is valid and ID isn't already taken.
       Post: Adds the user to the registry map. */
    public void addUser(User user);

    /* Pre: userId is a non-null string.
       Post: Returns the User object if found, otherwise null. */
    public User getUser(String userId);

    /* Pre: userId is non-null.
       Post: Returns true if the ID exists, false otherwise. */
    public boolean containsUser(String userId);
}