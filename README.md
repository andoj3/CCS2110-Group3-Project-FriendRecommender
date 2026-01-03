# CCS2110-Group3-Project-FriendRecommender 
Module: CCS2110 - Data Structures
This is our Milestone 2 submission code . We implemented a Java backend console app that recommends friends based on mutual connections and shared interests.
Key Implementation Details:
We used custom ADTs for the SocialGraph (Adjacency List) and UserRegistry(HashMap)
Our FriendRecommender uses BFS to find friends of friends and ranks them using a weighted score.
How to Run:
Just compile all the files and run MainTester.java.
It automatically runs our sample scenario (Maria & Alice) and the performance tests required (for 100, 1000, and 5000 users).
