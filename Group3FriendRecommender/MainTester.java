import java.util.List;
import java.util.Random;

//The console Application Includs hardcoded scenarios and performance testing.
public class MainTester {

    public static void main(String[] args) {
        System.out.println("~Friend Recommender System~");
        
        // PART 1: FUNCTIONAL TESTING 
        System.out.println("\n1. Testing ADT Operations with Sample An Scenario....");

        // Fistly we Instantiate ADTs
        UserRegistryADT registry = new UserRegistry();
        SocialGraphADT graph = new SocialGraph();
        FriendRecommenderADT recommender = new FriendRecommender(graph, registry);

        // Them We Create Data Elements (Users)
        /*Scenario we have : Maria wants friends.
        Eleni and Sakis are her friends.
        Giwrgos knows Eleni. Panagiotis knows Sakis. Alice knows both Eleni and Sakis.*/
        
        // Target User: Maria
        User u1 = new User("U1", "Maria");
        u1.addInterest("Coding"); u1.addInterest("Hiking");
        
        // Direct Friends of Maria
        User u2 = new User("U2", "Eleni"); 
        User u3 = new User("U3", "Sakis"); 
        
        // Friends-of-Friends (Candidates)
        // Giwrgos: Knows Eleni. Interest: Coding (Match)
        User u4 = new User("U4", "Giwrgos"); 
        u4.addInterest("Coding"); 
        
        // Panagiotis: Knows Sakis. Interest: None
        User u5 = new User("U5", "Panagiotis"); 
        
        // Alice: Knows Both Eleni and Sakis. Interest: Hiking (Match)
        User u6 = new User("U6", "Alice"); 
        u6.addInterest("Hiking");

        // Add to Registry
        registry.addUser(u1); registry.addUser(u2); registry.addUser(u3);
        registry.addUser(u4); registry.addUser(u5); registry.addUser(u6);
        System.out.println("Users added to Registry.");

        // Subsequently We Create Connections (Graph)
        // Maria <-> Eleni, Maria <-> Sakis
        graph.addFriendship("U1", "U2");
        graph.addFriendship("U1", "U3");
        
        // Eleni <-> Giwrgos, Eleni <-> Alice
        graph.addFriendship("U2", "U4");
        graph.addFriendship("U2", "U6");
        
        // Sakis <-> Panagiotis, Sakis <-> Alice
        graph.addFriendship("U3", "U5");
        graph.addFriendship("U3", "U6");

        System.out.println("Friendships were created.");
        System.out.println("Maria's Direct Friends: " + graph.getFriends("U1"));

        // Now We Test Recommendations are correct (Significant Operation)
        /*Expectation:
        Alice (U6) : 2 Mutuals (Eleni, Sakis) + 1 Interest (Hiking) = Score is  20 + 5 = 25
        Giwrgos (U4) : 1 Mutual (Eleni) + 1 Interest (Coding) = Score is 10 + 5 = 15
        Panagiotis (U5) : 1 Mutual (Sakis) + 0 Interests = Score is  10 + 0 = 10*/
        
        System.out.println("\nRunning Recommendation for Maria (Top 3)...");
        List<String> recs = recommender.recommendFriends("U1", 3);
        System.out.println("Recommendations for Maria: " + recs);
        
        if (recs.size() > 0 && recs.get(0).equals("U6")) {
            System.out.println("Success Case : Alice (U6) is top recommendation as expected.");
        } else {
            System.out.println("Failure Case : Calculation mismatch.");
        }



        // PART 2: PERFORMANCE TESTING
        System.out.println("\n2. Performance Testing With The Significant Operation");
        /*Initially, we noticed the largest dataset was faster due to Java startup lag. We added a warmup phase to
          resolve this, and our final results correctly show time increasing with dataset size.*/
        System.out.println("~Initializing test environment~");
        runPerformanceTest(100); 

        System.out.println("\n~Starting Now With The Actual Measurements~ ");
        // Test with 3 different dataset sizes
        runPerformanceTest(100);
        runPerformanceTest(1000);
        runPerformanceTest(5000);
    }

    /*Helper method to generate random data and measure time
     AI Generated (Entry 9): We used AI to help write the random generation logic quickly.*/
    public static void runPerformanceTest(int n) {
        System.out.println("Testing with N = " + n + " users...");
        
        UserRegistryADT registry = new UserRegistry();
        SocialGraphADT graph = new SocialGraph();
        FriendRecommenderADT recommender = new FriendRecommender(graph, registry);
        Random rand = new Random();

        // Fistly We Generate N users
        for (int i = 0; i < n; i++) {
            String id = "User" + i;
            User u = new User(id, "User" + i);
            // AI Help (Entry 9): The AI provided the loop structure for generating users.
            // We extended it here to also assign Interests randomly.
            // We used a 50% probability check to ensure roughly half the users match.
            int randomNum = rand.nextInt(100);
            if (randomNum < 50) { 
                u.addInterest("Coding"); 
            }
            registry.addUser(u);
            graph.addUser(id);
        }

        /* Then We add random connections here to 
        ensure every user has roughly 10 friends */
        for (int i = 0; i < n; i++) {
            String id = "User" + i;
            for (int j = 0; j < 10; j++) {
                int friendIdx = rand.nextInt(n);
                if (friendIdx != i) {
                    graph.addFriendship(id, "User" + friendIdx);
                }
            }
        }

        // We Measure recommendFriends time
        long startTime = System.nanoTime();
        
        // Recommend for User0
        recommender.recommendFriends("User0", 5);
        
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1000000.0;
        
        System.out.println("Time taken was : " + durationMs + " ms");
    }
}