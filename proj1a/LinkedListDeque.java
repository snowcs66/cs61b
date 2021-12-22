public class LinkedListDeque<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedListDeque() {
        first = last = null;
        size = 0;
    }

	private class Node<T> {
        private Node<T> previous;
        private T item;
        private Node<T> next;

        private Node(T item) {
        	this.item = item;
        }

        private T getItem() {
            return item;
        }
	}

	public void addFirst(T item) {
		Node<T> node = new Node<>(item);
		if (isEmpty()) {
			first = last = node;
		} else {
            node.next = first;
		    first.previous = node;
            first = node;
        }
        size++;
	}

	public void addLast(T item) {
		Node<T> node = new Node<>(item);
		if (isEmpty()) {
            first = last = node;
		} else {
		    last.next =  node;
            node.previous = last;
		    last = node;
	    }
		size++;
	} 

	public boolean isEmpty() {
		if (first == null)
			return true;
		return false;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		Node p = first;
		for (int i = 0; i < size(); i++) {
			System.out.println(p.getItem() + " ");
			p = p.next;
		}
    }

    public T removeFirst() {
        size--;
        if (isEmpty())
        	return null;
        if (first == last) {
            first = last = null;
            return null;
        }
        Node<T> remove = first;
        first = first.next;
        first.previous = null;
        remove.next = null;
        return remove.getItem();
    }

    public T removeLast() {
        size--;
        if (isEmpty())
        	return null;
        if (first == last) {
            first = last = null;
            return null;
        }
        Node<T> remove = last;
        last = last.previous;
        last.next = null;
        remove.previous = null;
        return remove.item;
        
    }

    public T get(int index) {
    	if (isEmpty() || index >= size)
        	return null;
        Node<T> p = first;
        if (index == 0)
        	return p.getItem();
        for (int i = 1; i <= index; i++)
        	p = p.next;
        return p.getItem();
    }

    public T getRecursive(int index) {
        if (isEmpty() || index >= size)
            return null;
        Node<T> p = first;
        if (index == 0)
            return p.getItem();
        p = p.next;
        return getRecursive(index - 1);
    }
}