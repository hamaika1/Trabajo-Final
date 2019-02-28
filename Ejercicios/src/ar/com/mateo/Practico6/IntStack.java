package ar.com.mateo.Practico6;

import java.util.EmptyStackException;

public class IntStack  {
	private int[] contents;
	private int tos; // Top of the stack

	public IntStack(int capacity) {
		contents = new int[capacity];
		tos = -1;
	}

	public void push(int element) throws FullStackException {
	if(isFull()== true){	
	contents[++tos] = element;
	}
	}
	public int pop() {
		try {
			if (isEmpty() == true) {
				return contents[tos--];
			}
		} catch (EmptyStackException e) {
			System.out.println("El metodo invocado esta vacio");
		}
		return tos;

	}

	public int peek() {
		try {
			if (isEmpty() == true) {
				return contents[tos];
			}
		} catch (EmptyStackException e) {
			System.out.println("El metodo invocado esta vacio");
		}
		return tos;
	}

	public boolean isEmpty() {
		return tos < 0;
	}

	public boolean isFull() {
		return tos == contents.length - 1;
	}

	public int size() {
		return this.tos + 1;
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "[ ]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.size(); i++) {
			sb.append(this.contents[i] + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "]");
		return sb.toString();
	}
}
