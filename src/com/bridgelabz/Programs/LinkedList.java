package com.bridgelabz.Programs;



public class LinkedList<T> {
	Node<T> head;
	Node<T> tail;
	static int count;

	public void add(T element) {

		Node<T> nd = new Node<T>();
		nd.setValue(element);
		//System.out.println("Adding: " + element);

		if (head == null) {

			head = nd;
			tail = nd;
		} else {

			tail.setNextRef(nd);

			tail = nd;
		}
	}

	

	public void addFirst(T element) {

		Node<T> tmp = head;
		Node<T> refNode = new Node<T>();
		refNode.setValue(element);
		refNode.setNextRef(tmp);

		head = refNode;

	}

	public void view() {
		Node<T> tmp = new Node<T>();
		tmp = head;
		while (true) {
			if (tmp == null) {
				break;
			}
			System.out.println(tmp.getValue());

			if (tmp.getNextRef() == null) {
				System.out.println("reached the end of file");
				return;
			}
			tmp = tmp.getNextRef();

		}
		System.out.println("List empty");
	}

	public void viewShare() {
		Node<T> tmp = new Node<T>();
		tmp = head;
		while (true) {
			if (tmp == null) {
				break;
			}
			System.out.println(tmp.getsymbol());
			System.out.println(tmp.getValue());
			System.out.println(tmp.getdatetime());
		

			if (tmp.getNextRef() == null) {
				System.out.println("reached the end of file");
				return;
			}
			tmp = tmp.getNextRef();

		}
		System.out.println("List empty");
	}

	public int checkEmpty() {
		Node<T> tmp = head;
		if (tmp == null)
			return 0;
		else
			return 1;
	}

	public T deleteFirst() {

		Node<T> tmp = head;
		Node<T> refNode = new Node<T>();

		refNode = tmp;
		tmp = tmp.getNextRef();

		head = tmp;
		//System.out.println(refNode.getValue());
		return refNode.getValue();

	}

	public T deleteLast() {

		return deleteAt(this.size() - 1);

	}

	public T deleteAt(int pos) {
		Node<T> tmp = head;
		Node<T> refNode = head;
		Node<T> next = null;
		Node<T> prev = null;
		int count = 0;
		while (true) {
			if (tmp == null) {

				break;
			}

			if (count == pos && tmp != head) {

				refNode = tmp;

				break;
			}

			prev = tmp;

			tmp = tmp.getNextRef();

			count++;

		}

		if (refNode != null) {

			if (refNode == head) {
				T value = refNode.getValue();
				tmp = refNode.getNextRef();

				head = tmp;
				System.out.println("head" + value);
				return value;
			}

			else if (refNode.getNextRef() == null) {

				Node<T> tmp1 = new Node<T>();
				refNode = tail;
				T value = refNode.getValue();
				tmp1 = prev;
				tmp1.setNextRef(null);
				tail = tmp1;
				System.out.println("deleted tail value" + value);
				return value;
			} else {
				next = tmp.getNextRef();
				prev.setNextRef(next);

				return tmp.getValue();

			}
		}

		else {
			return null;
		}

	}

	public T deleteElement(T element) {
		Node<T> tmp = head;
		Node<T> refNode = head;
		Node<T> next = null;
		Node<T> prev = null;

		while (true) {
			if (tmp == null) {

				break;
			}

			if (element.equals(tmp.getValue())||element.equals(tmp.getsymbol()) && tmp != head) {

				refNode = tmp;

				break;
			}

			prev = tmp;

			tmp = tmp.getNextRef();

		}

		if (refNode != null) {

			if (refNode == head) {
				T value = refNode.getValue();
				tmp = refNode.getNextRef();

				head = tmp;
				System.out.println("head" + value);
				return value;
			}

			else if (refNode.getNextRef() == null) {

				Node<T> tmp1 = new Node<T>();
				refNode = tail;
				T value = refNode.getValue();
				tmp1 = prev;
				tmp1.setNextRef(null);
				tail = tmp1;
				System.out.println("deleted tail value" + value);
				return value;
			} else {
				next = tmp.getNextRef();
				prev.setNextRef(next);

				return tmp.getValue();

			}
		}

		else {
			return null;
		}

	}

	public void insertAt(T number, int pos) {
		Node<T> tmp = head;
		Node<T> refNode = null;
		Node<T> prev = null;
		Node<T> next = null;

		int i = 0;

		while (true) {

			if (pos == 0) {
				addFirst(number);
				break;

			}

			prev = tmp;

			if (i == pos - 1 || tmp.getNextRef() == null) {

				refNode = tmp;

				next = tmp.getNextRef();

				break;
			}

			tmp = tmp.getNextRef();

			i++;
		}

		if (refNode != null) {

			if (next == null) {
				Node<T> tmp1 = new Node<T>();

				tmp1.setValue(number);
				refNode.setNextRef(tmp1);
				tmp1.setNextRef(null);

				tail = tmp1;

				System.out.println("added: " + tmp1.getValue());
			} else {

				Node<T> tmp1 = new Node<T>();

				tmp1.setValue(number);

				prev.setNextRef(tmp1);
				tmp1.setNextRef(next);

				System.out.println(prev.getValue());

				System.out.println(tmp1.getValue());

				System.out.println(next.getValue());

			}
		}

		else {
			return;
		}

	}

	public int traverse(T element) {
		Node<T> tmp = head;
		
		int flag = 0;

		while (true) {
			if (tmp == null) {
				flag = 1;
				break;

			}

			if (tmp.getValue().equals(element)) {
				return 0;
			}

			tmp = tmp.getNextRef();
		}
		if (flag == 1) {
			return 1;
		}

		return -1;
	}

	public void returnElement(String[] array, int count) {
		Node<T> tmp = head;

		int i = 0;

		while (true && i < count) {
			if (tmp == null) {
				break;
			}
			// System.out.println(tmp.getValue());

			array[i] = tmp.getValue().toString();
			i++;

			tmp = tmp.getNextRef();
		}
	}

	public T returnItem(int pos) {
		Node<T> tmp = head;
		int count = 0;
		while (true) {
			if (tmp == null) {
				return null;
			}
			if (count == pos) {
				System.out.println("value" + tmp.getValue());
				return tmp.getValue();
			}
			tmp = tmp.getNextRef();
			count++;
		}

	}


	public int returnPos(T element) {
		Node<T> tmp = head;

		int i = 0;
		int flag = 0;
		int currentNo;

		while (true) {
			if (tmp == null) {
				flag = 1;
				break;

			}

			currentNo = (Integer) tmp.getValue();
			if (tmp.getValue().equals(element) || ((Integer) element).compareTo(currentNo) < 0) {
				return i;

			}

			tmp = tmp.getNextRef();

			i++;
		}
		if (flag == 1) {
			return -1;
		}
		return -1;

	}

	
	
	public int findIndex(T element) {
		Node<T> tmp = head;

		int i = 0;
		int flag = 0;
	

		while (true) {
			if (tmp == null) {
				flag = 1;
				break;

			}

			
			if (tmp.getValue().equals(element)) {
				return i;

			}

			tmp = tmp.getNextRef();

			i++;
		}
		if (flag == 1) {
			return -1;
		}
		return -1;

	}

	public int size() {
		// TODO Auto-generated method stub

		Node<T> tmp = head;
		int count = 0;
		while (true) {
			if (tmp == null) {
				break;
			}

			if (tmp.getNextRef() == null) {
				count++;
				break;
			}

			else {

				count++;
				tmp = tmp.getNextRef();
				// System.out.println("currentNode value"+tmp.getValue());
			}

		}
		//System.out.println("size in method" + count);
		return count;
	}


public void addShare(T value,T datetime,T symbol) {
	Node<T> nd = new Node<T>();
	nd.setValue(value);
	nd.setdatetime(datetime);
	nd.setsymbol(symbol);
	System.out.println("Adding: " + symbol);

	if (head == null) {

		head = nd;
		tail = nd;
	} else {

		tail.setNextRef(nd);

		tail = nd;
	}
	
	

	
}

class Node<T> {

	T value;
	T datetime;
	T symbol;
	Node<T> nextRef;

	public T getValue() {
		return value;
	}
	public T getdatetime() {
		return datetime;
	}
	public T getsymbol() {
		return symbol;
	}

	public void setValue(T value) {
		this.value = value;
	}
	public void setdatetime(T value) {
		this.datetime = value;
	}
	public void setsymbol(T value) {
		this.symbol = value;
	}

	public Node<T> getNextRef() {
		return nextRef;
	}

	public void setNextRef(Node<T> ref) {
		this.nextRef = ref;
	}

}
}
