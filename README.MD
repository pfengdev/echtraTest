To fetch project:
git clone git@github.com:pfengdev/echtraTest.git

To run:
Using Java 11
Maven 3.6.0
mvn clean compile exec:java


About the project:
I used this tic tac toe algorithm:
https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

I spent 5 1/2 hours on this. I made a commit at 4 hours. You can look at the 2nd to last commit to see the project with only
4 hours of work. The main difference is that the 4 hr project has a bug in the algorithm and I fixed it in the final commit.
I got stuck debugging the algorithm to solve tic tac toe, but it turns out it was just a one line bug in AIService. I'm not familiar with
these kinds of algorithms. It was hard to step through and see any meaningful debugging info and I didn't fully understand the implementation
at first, so it took me a bit longer to find the bug.

The application starts from the Main function. I follow a basic game loop pattern. I decoupled the game state manager, input handler,
and renderer. If I had more time I'd add unit tests. I'd make inputservice and rendererservice interfaces b/c there could be different
types of inputs (i.e. controller, keyboard) or different ways to render. I considered adding an "Action" model so that an Input could
correlate to an "Action". An action would be something like jumping or moving in something like Mario, but in this Tic Tac Toe game it could be
selecting a cell. The AI service could accept a parameter for how deep it looks down the "tree", which would correlate to the AI difficulty level.