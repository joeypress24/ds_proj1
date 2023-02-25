/**
@author Press, Joseph
*/

public class ArrayQueue<T> implements Queue<T>, List<T>{
  private T[] elements;
  private int numElem;
  private int cap;

  @SuppressWarnings("unchecked")

  // constructor, defines the capacity of the list
  public ArrayQueue() {
    this.cap = 1000000;
    this.numElem = 0;
    elements = (T[]) new Object[cap];

  }

  public String toString(){
    String s = "[ ";
    for(int i=0;i<this.size();i++){
      s += "" + get(i) +" ";
    }
    s += "]";
    return s;
  }

  /**
  Return the element at the index in the list. If the list currently has k elements, the only valid indices are 0 through k-1, regardless of its capacity; any other index should result in an Exception.

  @param index the index to get
  @return the value of the element at the index
  **/
  public T get(int index) throws IndexOutOfBoundsException
  {
    if(index >= 0 && index < numElem)
      return elements[index];
    else
      throw new IndexOutOfBoundsException();
  }

  /**
  Set the value of the element at an existing, legal index to something new.  If the list currently has k elements, the only valid indices are 0 through k-1, regardless of its capacity; any other index should result in an Exception.

  @param index the index to set at
  @param element the value to set
  @return the original (pre-replacement) value of the element set
  **/
  public T set(int index, T element) throws IndexOutOfBoundsException
  {
    if(index >= numElem || index < 0) //check for errors
      throw new IndexOutOfBoundsException();

    T ret = elements[index]; // store the return value
    elements[index] = element; // set the desired index
    // no need to change numElem!
    return ret;
  }

  /**
  Add an element e to the list at the index i - all other elements shift up an index to make room.  If a list consists of [ A B C ], and add(1,D) is called, it should newly be [ A D B C ].  If there are k elements in the list, legal indices are 0-k.  All others should result in Exceptions.  Also, performing an add function when it would result in outgrowing the capacity should result in an exception.

  @param index the index to add to
  @param element the value to add
  **/
  public void add(int index, T element) throws IndexOutOfBoundsException
  {
    if(numElem == 0) // if adding the first element
    {
      elements[0] = element;
      numElem++;
    }
    else if(index < 0 || index > this.numElem) //check if desired index is out of bounds
    {
      throw new IndexOutOfBoundsException();
    }
    else if(numElem > cap) // check if numElem exceeds capacity
    {
      throw new IndexOutOfBoundsException();
    }
    else{ // else to ensure this only happens if NOT first element
      //shift up all of the elements
      for(int  i = numElem-1; i >= index; i--)
      {
        elements[i+1] = elements[i];
      }
      elements[index] = element;
      numElem++;
    }
  }

  /**
  Remove an element from the list at an index and return it - all following elements shift down an index to fill the resulting hole.  If a list consists of [ A B C D ], and remove(1) is called, this should return B, and result in [ A C D ].  If you try and remove something at a bad index, it should throw an Exception.

  @param index the index to be removed
  @return the element removed
  **/
  @SuppressWarnings("unchecked")
  public T remove(int index) throws IndexOutOfBoundsException
  {
    // check for valid index
    if(index < 0 || index >= numElem)
    {
      throw new IndexOutOfBoundsException();
    }

    T ret = null;
    // create another array, copy OG array to new array (everything except index)
    T[] tempArray = (T[]) new Object[this.cap];
    for(int i = 0, j = 0; i < this.numElem; i++)
    {
      if(index == i)
      {
        ret = elements[i]; // save return value
      }
      else // not removing this index
      {
        tempArray[j] = elements[i];
        j++;
      }
    }

    elements = tempArray; // reassign the array
    this.numElem--;

    return ret;
  }


  /**
  Returns the number of elements in the list

  @return the number of elements in the list
  **/
  public int size()
  {
    return this.numElem;
  }


  /**
  these are my methods
  */
  public T pop()
  {
    return remove(numElem-1);
  }

  public void push(T e)
  {
    add(numElem, e);
  }

  // queue
  public void enqueue(T e)
  {
    add(numElem, e);
  }

  public T dequeue() throws IndexOutOfBoundsException {
    return remove(0);
  }

  // shared peek
  public T peek() throws IndexOutOfBoundsException {
    return get(0);
  }
}
