package edu.miracosta.cs113;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;          // the number of items in the list
  
  public void addFirst(E obj) 
  { // Fill Here 
	  add(0, obj);
  }
  
  public void addLast(E obj) 
  { // Fill Here
	  add(size, obj);
  }

  public E get(int index) 
  { 	
	  ListIterator<E> iter = listIterator(index);
      	return iter.next();
  }  
  
  public E getFirst() 
  { 
	  return head.data;  
  }
  
  public E getLast() 
  { 
	  return tail.data;  
  }
  public int size() 
  {  
	  return size;  
  }
  
  public E remove(int index)
  {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   
        	returnValue = iter.next();
            iter.remove();
        }
        else 
        {   
        	throw new IndexOutOfBoundsException();  
        }
        return returnValue;
  }

  public Iterator iterator() 
  { 
	  return new ListIter(0);  
  }
  
  public ListIterator listIterator() 
  { 
	  return new ListIter(0);  
  }
  
  public ListIterator listIterator(int index)
  {
	  return new ListIter(index);
  }
  
  public ListIterator listIterator(ListIterator iter)
  {    
	  return new ListIter( (ListIter) iter);  
  }

  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)
        {   
        	data = dataItem;   
        }
  }

  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;
        private Node<E> prevReturnedItem;
        private int index = 0;

    public ListIter(int i)
    {   
    	if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
        prevReturnedItem = null;
 
        if (i == size)
        {     
        	index = size;     nextItem = null;      
        }
        
        else
        {   nextItem = head;
            for (index = 0; index < i; index++)  nextItem = nextItem.next;   
        }
    }  

    public ListIter(ListIter other)
    {   
    	nextItem = other.nextItem;
        index = other.index;    
    }
    
    public boolean hasNext() 
    {   
    	return nextItem != null;
    }
    
    public boolean hasPrevious()
    {   
    	return(nextItem == null && size != 0) || nextItem.prev != null;
    }
    
    public int previousIndex() 
    { 
    	return index - 1; 
    }
    
    public int nextIndex() 
    {  
    	return index;
    }
    
    public boolean isEmpty() 
    {
  	  return size() == 0;
    }
    
    public int get(int pos)
    {
        Node current = null;
        for(int i = 0; i <= pos && current != null; i++)
        {
            if(pos == 0){
                current = head;
            }else{
                current = nextItem;
                break;
            }
        }
        return (int) current.data;
    }
    
    public void set(E obj)
    { 

        if (prevReturnedItem != null) 
        {
            prevReturnedItem.data = obj;
        } 
        else 
        {
            throw new IllegalStateException();
        }
    }  
    
    public void remove()
    {
    	if (prevReturnedItem != null) 
        {    
            if (prevReturnedItem.next != null) 
            { 
                prevReturnedItem.next.prev = prevReturnedItem.prev;
            } 
            else 
            { 
                tail = prevReturnedItem.prev;
                if (tail == null) 
                {
                    head = null;
                } 
                else 
                {
                    tail.next = null;
                }
            } 
            if (prevReturnedItem.prev != null) 
            { 
                prevReturnedItem.prev.next = prevReturnedItem.next;
            } 
            else 
            { 
                head = prevReturnedItem.next;
                if (head == null) 
                {
                    tail = null;
                } 
                else 
                {
                    head.prev = null;
                }
            }	                 
            prevReturnedItem = null;
            size--;
            index--;
        } 
        else 
        {
            throw new IllegalStateException();
        }
    }

    public E next()
    {  
    	if(!hasNext())
    	{
    		throw new NoSuchElementException();
    	}
    	prevReturnedItem = nextItem;
    	nextItem = nextItem.next;
    	index++;
        return prevReturnedItem.data;
    }

    public E previous() 
    {  
    	if(!hasPrevious()) 
    	{
    		throw new NoSuchElementException();
    	}
    	if(nextItem == null) 
    	{
    		nextItem = tail;
    	}
    	else
    	{
    		nextItem = nextItem.prev;
    	}
    	prevReturnedItem = nextItem;
    	index--;
    	return prevReturnedItem.data;
    }
  		  
    public void add(E obj)
    {
    	if (head == null) 
        {
            head = new Node<E>(obj);
            tail = head;
        } 
        else if (nextItem == head)
        { 
            Node<E> newNode = new Node<E>(obj);
            newNode.next = nextItem; 	
            nextItem.prev = newNode; 
            head = newNode;
        } 
        else if (nextItem == null)
        {
            Node<E> newNode = new Node<E>(obj);
            tail.next = newNode; 
            newNode.prev = tail; 
            tail = newNode; 
        }
        else 
        {
            Node<E> newNode = new Node<E>(obj);
            newNode.prev = nextItem.prev; 
            nextItem.prev.next = newNode; 
            newNode.next = nextItem;
            nextItem.prev = newNode; 
        }
        size++;
        index++;
        prevReturnedItem = null;
    }
  }// end of inner class ListIter
}// end of class DoubleLinkedList