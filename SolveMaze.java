import java.util.concurrent.TimeUnit;

/**
 * TODO: Implement the remainder of this main function!
 */
public class SolveMaze{

  /** Clears the screen - you don't need to worry about this */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /** Prints the Maze so you can see progress */
  public static void progress(Maze maze){
    //Prints the Maze so you can see progress
    clearScreen();
    System.out.println(maze);
    //Slows it down so you can track
    try{
      TimeUnit.MILLISECONDS.sleep(100);
    }catch (InterruptedException e) {
      System.exit(1);
    }
  }

  public static void main(String args[]){

    String inputFileName="";

    if(args.length < 1 ||  args[0].equals("-h")){
      System.out.println("java PrintMaze <out.ser>");
      System.out.println("out.ser   : serilized maze stored in output");
      System.exit(1);
    }else{
      inputFileName=args[0];
    }

    //Create the maze by reading the input file
    Maze maze = Maze.fromFile(inputFileName);
    System.out.println(maze);

    Coord start=new Coord(0,0);
    maze.visit(start);

    //TODO: Complete the Solve Maze!
    //
    // Basic Algorithm:
    //    init: enqueue the start index on a queue, and mark it as visited
    //
    //    Loop: continue unitl queue empty or finished reached
    //      0. print progress()
    //      1. dequeue current index
    //      2. Check if we've solved the maze, if so break!
    //      3. if any reachable neighbors from the current index
    //         a. enqueue the the neighbor's index
    //         b. mark the neighbor as visited
    //      4. continue loop
    //
    //

  // queue implementation
    ArrayQueue<Coord> l = new ArrayQueue<Coord>();
    l.enqueue(start); // enqueue start

  /**
  loop until queue is empty or the coordinates are at the end
  */
    while(l.peek() != null)
    {
      // dequeue current index
      Coord cur = l.dequeue();

      //check if at end, break if so
      if(cur.row == maze.getHeight()-1 && cur.col == maze.getWidth()-1)
      {
        break;
      }

      // print progress
      progress(maze);


      // if any reachable neighbors, enqueue the index and mark as visited
      // use getReachableUnvisitedNeighbors() function
      List<Coord> temp = maze.getReachableUnvisitedNeighbors(cur);
      for(int i = 0; i < temp.size(); i++)
      {
        maze.visit(temp.get(i));
        l.enqueue(temp.get(i));
      }

    }

    //print final result after complete!
    progress(maze);


  }

}
