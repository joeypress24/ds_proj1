import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Implement the remainder of this main function!
 */
public class DrawMaze{

  /** Clears the screen - you don't need to worry about this */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /** Prints the Maze so you can see progress */
  public static void progress(Maze maze){
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

    int height=0,width=0;
    Random rnd=null;
    String outputFileName = "";

    if(args.length < 3){
      System.out.println("ERROR: invalid option");
      System.out.println("java DrawMaze <out.ser> <height> <width> [seed]");
      System.out.println("out.ser  : seriliazed output file");
      System.out.println("height   : height of maze");
      System.out.println("width    : width of maze");
      System.out.println("seed     : Optional seed value for randomizing maze");
      System.exit(1);
    }else{
      outputFileName = args[0];
      height = Integer.parseInt(args[1]);
      width = Integer.parseInt(args[2]);
    }

    if(args.length > 3){
      rnd = new Random(Integer.parseInt(args[3]));
    }else{
      rnd = new Random(0);
    }



    //Create the Maze at the height and width
    Maze maze= new Maze(height,width);

    //initialize start, upper left, and finnish bottom right
    Coord start=new Coord(0,0);
    Coord end=new Coord(height-1,width-1);
    maze.removeWall(start,Maze.LEFT);
    maze.removeWall(end,Maze.RIGHT);

    //TODO: Complete the Draw Maze!
    //
    // Basic Algorithm:
    //   init: push the start index on a stack, mark it as visited
    //
    //   Loop: continue until the stack is empty
    //      0. print progress()
    //      1.  pop index off the stack
    //      2.  if any unvisited neighbors
    //          a. choose one at random
    //          b. mark it as visited
    //          c. remove the wall between the indexes
    //          d. push the current index on the stack
    //          e. push the next index on the stack
    //      3. continue loop


    ArrayList<Coord> l = new ArrayList<Coord>();
    l.push(start); // push start index on stack
    maze.visit(start); // mark as visited

    while(l.size() > 0) // while not empty
    {
      progress(maze); // print progress()

      Coord cur = l.pop(); // pop current index off the stack

      List<Coord> temp = maze.getUnvisitedNeighbors(cur);

      if(temp.size() > 0)
      {
        int r = rnd.nextInt(temp.size()); // generate random int
        Coord c = temp.get(r);

        maze.visit(c); // mark it as visited

        // remove the proper wall
        if(c.col > cur.col) // if to the right
          maze.removeWall(cur, Maze.RIGHT);
        else if(c.col < cur.col) // if to the left
          maze.removeWall(cur, Maze.LEFT);
        else if(c.row > cur.row) // if below
          maze.removeWall(cur, Maze.DOWN);
        else if(c.row < cur.row) // if above
          maze.removeWall(cur, Maze.UP);

        //push the current index on the stack
        l.push(cur);

        //push the next index on the stack
        l.push(c);
      }

    //  progress(maze); // print progress()

    }




    //Writes maze to output file once done
    clearScreen();
    maze.unvisitAll();
    System.out.println(maze);
    maze.toFile(outputFileName);

  }

}
