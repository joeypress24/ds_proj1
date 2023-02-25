public class LinkedList<T> implements List<T>,Queue<T>,Stack<T>{

  private class Node{
    private T data;
    private Node next;

    public Node(T d){
      data = d;
      next=null;
    }

    public T getData(){ return data;}
    public void setData(T d){ data = d;}
    public Node getNext(){ return next;}
    public void setNext(Node n){ next=n;}
  }

  private Node head;
  private Node tail;
  private int size;

  public LinkedList(){
    head=null;
    tail=null;
    size=0;
  }



  // Public facing list interface methods
  //
  // Each method calls a private method that is implemented
  // recursively. You should be able to follow this code.

  public T get(int i){
    return get(head,i);
  }
  public T set(int i, T e){
    return set(head,i,e);
  }
  public void add(int i, T e){
    head = add(head,i,e); //note that this returns a Node because
    //the head might have changed!
    if (tail==null)
      tail=head;
    size++;
  }

  public T remove(int i){

    T data = get(i);       //retrieve the data we are remove first!

    head = remove(head,i); //note that this returns a Node because
    //the head might have changed!
    if (head==null)
      tail=null;
    size--;

    return data;
  }

  public int size(){
    return size;
  }

  public String toString(){
    return "["+toString(head)+" ]";
  }

  /*************************************************************
   * Private recursive methods
   *
   * You get these for free --- good examples for later, maybe?
   **************************************************************/

  private T get(Node n, int i){
    if( i < 0){
      throw new IndexOutOfBoundsException();
    }


    if(n==null){
      throw new IndexOutOfBoundsException();
    }

    if(i==0){
      return n.getData();
    }

    return get(n.getNext(),i-1);

  }


  private T set(Node n, int i, T e){
    if( i < 0){
      throw new IndexOutOfBoundsException();
    }

    if(n==null){
      throw new IndexOutOfBoundsException();
    }

    if(i==0){
      n.setData(e);
      return e;
    }

    return set(n.getNext(),i-1,e);

  }



  private Node add(Node n, int i, T e){
    if( i < 0){
      throw new IndexOutOfBoundsException();
    }

    if(n==null && i > 0){
      throw new IndexOutOfBoundsException();
    }

    if(i==0){
      Node newNode = new Node(e);
      newNode.setNext(n);
      if (n==null)
        tail=newNode;
      return newNode;
    }

    n.setNext(add(n.getNext(),i-1,e));
    return n;
  }


  private Node remove(Node n, int i){
    if( i < 0){
      throw new IndexOutOfBoundsException();
    }


    if(n==null){
      throw new IndexOutOfBoundsException();
    }

    if(i==0){
      return n.getNext();
    }

    n.setNext(remove(n.getNext(),i-1));
    if (n.getNext()==null)
      tail=n;
    return n;
  }


  private String toString(Node n){
    if(n==null) return "";
    return " "+n.getData().toString()+toString(n.getNext());
  }

  // everything above is given, do these below
  //********* Stack Interface *********/

  /**
  The push method adds e to the top of the stack
  */
  public void push(T e){
    //TODO
    add(0, e); // add e at index 0
  }

  /**
  pop() removes and returns the top element on the stack
  */
  public T pop() throws IndexOutOfBoundsException{
    //TODO
    return remove(0);
  }

  //********* Queue Interface *********/
  /**
  enqueue() adds the element to the back of the queue, adjusting the tail
  */
  public void enqueue(T e){
    //TODO
    // if(size != 0)
    // {
    //   add(this.size-1, e); // add element to the top of the list
    // }
    // else
    //   add(0, e);
    add(this.size, e);
  }

  //not sure about this one
  public T dequeue() throws IndexOutOfBoundsException{
    //TODO
    return remove(0);
  }


  //********** Shared Peek ******/
  public T peek() throws IndexOutOfBoundsException{
    //TODO
    return get(0);
  }

}
