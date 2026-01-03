import java.util.List;

/*Modified based on Professor's feedback:
We combined the logic into a single Recommender ADT rather than
exposing the CandidateQueue publicly.*/
public interface FriendRecommenderADT {
    /* Pre: currentID is valid in the graph, and limit (k) > 0.
       Post: Returns top k candidates sorted by highest score first. */
    public List<String> recommendFriends(String targetUserId, int k);/* Recommends top K friends for a user. 
    Uses BFS for candidate generation and Weighted Scoring for ranking.*/
}