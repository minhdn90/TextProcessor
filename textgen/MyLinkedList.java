package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		add(size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index >= 0 && index < size)
		{
			return getNode(index).data;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		else
		{
			LLNode<E> curr = getNode(index);
			LLNode<E> node = new LLNode<E>(element);
			if (curr == null && head == null)
			{
				head = node;
				tail = node;
			}
			else if (curr == null)
			{
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
			else
			{
				node.next = curr.next;
				node.prev = curr;
				curr.next.prev = node;
				curr.next = node;
			}
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index >= size || index < 0) throw new IndexOutOfBoundsException();
		else
		{
			LLNode<E> curr = getNode(index);
			E dataToBeRemoved = curr.data;
			if (index == 0)
			{
				if (size>1)
				{
					head = curr.next;
					curr.next.prev = null;
				}
				else
				{
					head = null;
					tail = null;
				}
			}
			else if (index == size - 1)
			{
				tail = curr.prev;
				curr.prev.next = null;
			}
			else
			{
				curr.next.prev = curr.prev;
				curr.prev.next = curr.next;
			}
			size--;
			return dataToBeRemoved;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index >= size || index <0) throw new IndexOutOfBoundsException();
		else
		{
			LLNode<E> newNode = new LLNode<E>(element);
			LLNode<E> curr = getNode(index);
			newNode.next = curr.next.next;
			newNode.prev = curr;
			curr.next = newNode;
		}
		
		return null;
	}
	private LLNode<E> getNode (int index)
	{
		if (index == size)  return null;
		int i = 0;
		LLNode<E> iterator = head;
		while (i<index-1)
		{
			iterator = iterator.next;
			i++;
		}
		return iterator;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
