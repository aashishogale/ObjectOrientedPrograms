package com.bridgelabz.Programs;

/***************************************************************************
* Purpose : To create class for Stack
*
* @author   Aashish
* @version  1.0
* @since    13-10-2017
****************************************************************************/

public class Stack<T> {
	LinkedList<T> list=new LinkedList<T>();
		public  void push(T element) {
			list.addFirst(element);
		}
		public T pop() {
			if(list.checkEmpty()==1) {
				return(list.deleteFirst());
			}
			else {
				return null;
			}
			
		}
		public T popElement(T element) {
			if(list.checkEmpty()==1) {
				return(list.deleteElement(element));
			}
			else {
				return null;
			}
			
		}
		public void view() {
			list.view();
		}
		public  int  isEmpty() {
			 return list.checkEmpty();
		}
		public void pushDistinct(T element) {
			if(list.traverse(element)!=0) {
				list.addFirst(element);
			}
			else {
				return;
			}
		}

}
