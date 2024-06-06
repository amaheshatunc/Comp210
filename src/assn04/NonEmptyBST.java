package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}


	// TODO: insert
	@Override
	public BST<T> insert(T element) {

		int comparison = element.compareTo(_element);

		if (comparison > 0) {
			this._right = this.getRight().insert(element);
		}
		else if (comparison < 0)
		{
			this._left = this.getLeft().insert(element);
		}
		return this;
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		int comparison = element.compareTo(_element);

		// logic to get to deletion point
		if (comparison > 0) {
			this._right = this.getRight().remove(element);
		}
		else if (comparison < 0)
		{
			this._left = this.getLeft().remove(element);
		}

		if (comparison == 0) {
			// case with no child
			if (this._left.isEmpty() && this._right.isEmpty()) {
				return new EmptyBST<>();
			}
			// case of one child
			if (this._left.isEmpty()) {
				return this._right;
			}
			if (this._right.isEmpty()) {
				return this._left;
			}
			// final case of removing the node with two children
			this._element = this._right.findMin();
			_right = _right.remove(_element);
		}
		return this;
	}


	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		int compareStart = _element.compareTo(start);
		int compareEnd = _element.compareTo(end);

		// recursively decide whether to go right or left, so that you can get to the range.
		if (compareStart < 0) {
			this._right = _right.remove_range(start, end);
		}
		else if (compareEnd > 0) {
			this._left = _left.remove_range(start, end);
		}
		else {
			// once arrives in range, recursively calls remove method on every element in the range.
			return this.remove(_element).remove_range(start, end);
		}

		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		// Recursively prints left child till it cannot.
		if (isEmpty()) {
			return;
		}

		System.out.print(_element + " ");

		if (!_left.isEmpty()) {
			_left.printPreOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!_left.isEmpty()) {
			_left.printPostOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPostOrderTraversal();
		}
		System.out.print(_element + " ");

	}


	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}