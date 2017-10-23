package com.bridgelabz.Programs;

/***************************************************************************
 * Purpose : To create class for Queue
 *
 * @author Aashish
 * @version 1.0
 * @since 13-10-2017
 ****************************************************************************/

public class Queue<T> {
	LinkedList<T> list = new LinkedList<T>();

	/**
	 * purpose:enqueue the element
	 * 
	 * @param element
	 * @return
	 */
	public void enqueue(T element) {
		list.add(element);
	}

	/**
	 * purpose:enqueue the distinct element
	 * 
	 * @param element
	 * @return
	 */

	public void enqueueDistinct(T element) {
		if (list.traverse(element) != 0)
			list.add(element);
		else
			return;
	}

	public void enqueueSort(T element) {
		int index;

		index = list.returnPos(element);

		if (index != -1)
			list.insertAt(element, index);
		else
			list.add(element);

	}

	/**
	 * purpose:dequeue the queue
	 * 
	 * @param
	 * @return element
	 */
	public T dequeue() {
		return (list.deleteFirst());
	}

	public T removeRear() {

		return (list.deleteLast());
	}

	/**
	 * purpose:display the queue
	 * 
	 * @param
	 * @return
	 */
	public void display() {
		list.view();
	}

	public int size() {
		return (list.size());
	}

}
