import java.util.*;

public class FriendRecommender implements FriendRecommenderADT {

    private SocialGraphADT graph;
    private UserRegistryADT registry;

    // Scoring Weight
    private static final int W_MUTUAL = 10;
    private static final int W_INTEREST = 5;
    private static final int W_ACTIVITY = 2;

    public FriendRecommender(SocialGraphADT graph, UserRegistryADT registry) {
        this.graph = graph;
        this.registry = registry;
    }

    // Helper class for Priority Queue (Internal use only, as per feedback)
    private class Candidate {
        String userId;
        int score;

        public Candidate(String userId, int score) {
            this.userId = userId;
            this.score = score;
        }
    }

    @Override
    public List<String> recommendFriends(String targetUserId, int k) {
        /* AI Generated Logic Ref: M1 Journal Entry 2 (BFS) & Entry 5 (Scoring)
        We merged the "CandidateQueue" ADT logic here to avoid overengineering. */
        
        Set<String> visited = new HashSet<>();
        Set<String> directFriends = graph.getFriends(targetUserId);
        
        /* Map to store candidates and their mutual friend count
        Key: CandidateID and Value: Mutual Friend Count */
        Map<String, Integer> candidates = new HashMap<>();

        /* 1. BFS-like traversal to find Friends-of-Friends 
        We don't need a full Queue BFS because we only care about Level 2.
        We can just iterate the direct friends' friends.*/
        
        visited.add(targetUserId);
        visited.addAll(directFriends); // Mark friends as visited so we don't recommend them

        for (String friendId : directFriends) {
            Set<String> fofs = graph.getFriends(friendId);
            for (String fofId : fofs) {
                if (!visited.contains(fofId)) {
                    // This is a valid candidate (Level 2)
                    candidates.put(fofId, candidates.getOrDefault(fofId, 0) + 1);
                }
            }
        }

        // AI Help: Used the syntax from Journal Entry 8 for the Min-Heap comparator
        PriorityQueue<Candidate> minHeap = new PriorityQueue<>(k, (c1, c2) -> {
        // We want a Min-Heap, so smallest score is at the top (to be removed first)
        return Integer.compare(c1.score, c2.score); 
});

        User targetUser = registry.getUser(targetUserId);
        
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            String candidateId = entry.getKey();
            int mutualCount = entry.getValue();
            User candidateUser = registry.getUser(candidateId);

            if (candidateUser == null) continue; // Safety check

            // Calculate Score
            int interestScore = getIntersectionSize(targetUser.getInterests(), candidateUser.getInterests());
            int activityScore = getIntersectionSize(targetUser.getActivities(), candidateUser.getActivities());
             
            int mutualPoints = mutualCount * W_MUTUAL;
            int interestPoints = interestScore * W_INTEREST;
            int activityPoints = activityScore * W_ACTIVITY;
            int totalScore = mutualPoints + interestPoints + activityPoints;
            
            // 3. Update Heap (Keep only top K)
            Candidate c = new Candidate(candidateId, totalScore);
            
            if (minHeap.size() < k) {
                minHeap.add(c);
            } else if (totalScore > minHeap.peek().score) {
                minHeap.poll(); // Remove lowest scorer
                minHeap.add(c);
            }
        }

        /* 4. Construct Result List
        Heap comes out smallest first, so we use a stack or insert at 0 to reverse */
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll().userId); // Add to front to reverse order
        }
        
        return result;
    }

    /*AI Help (Entry 3): We asked how to find common elements efficiently,
      the AI suggested retainAll() but warned us to create a copy first,
      if we use retainAll on the original set1, it would delete the user's actual interests*/
    private int getIntersectionSize(Set<String> set1, Set<String> set2) {
    Set<String> copy = new HashSet<>(set1);
    copy.retainAll(set2);
    return copy.size();
    }
}
