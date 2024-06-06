package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        AVLTree<T> altTree = _right;
        this._right = altTree._left;

        this._size = this._right._size + this._left._size + 1;
        this._height = Math.max(this._left._height, this._right._height) +1;

        altTree._left = this;

        altTree._size = altTree._right._size + altTree._left._size + 1;
        altTree._height = Math.max(altTree._left._height, altTree._right._height) + 1;

        return altTree;

    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */


    /**
     * A
     *  \
     *   B
     *    \
     *     C
     *
     *   B
     *  / \
     * A   C
     *
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         // "this" is the root node.
         AVLTree<T> altTree = _left;
         this._left = altTree._right;

         this._size = this._left._size + this._right._size + 1;
         this._height = Math.max(this._right._height, this._left._height) +1;

         altTree._right = this;

         altTree._size = altTree._left._size + altTree._right._size + 1;
         altTree._height = Math.max(altTree._right._height, altTree._left._height) + 1;

         return altTree;


     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO
        AVLTree <T> altTree = new AVLTree<>();
        // Making a whole new tree.
        altTree._value = this._value;
        altTree._height = this._height;
        altTree._size = this._size;
        altTree._left = this._left;
        altTree._right = this._right;


        if (altTree._value ==  null) {
            altTree._value = element;
            altTree._height = 0;
            altTree._size = 1;
            altTree._right = new AVLTree<>();
            altTree._left = new AVLTree<>();

            return altTree;
        }

        if (element.compareTo(altTree._value) < 0) {
            altTree._left = (AVLTree<T>) altTree._left.insert(element);
        }

        if (element.compareTo(altTree._value) > 0) {
            altTree._right = (AVLTree<T>) altTree._right.insert(element);
        }

        altTree._height = Math.max(altTree._left._height, altTree._right._height) + 1;
        altTree._size = (altTree._left._size + altTree._right._size) + 1;

        int balanceFactor = altTree._left._height - altTree._right._height;

        if (balanceFactor < -1) {
            // detects what type of inbalance is in this subtree
            if (altTree._right._left._height > altTree._right._right._height) {
                altTree._right = altTree._right.rotateRight();
            }
            altTree = altTree.rotateLeft();
        }

        if (balanceFactor > 1) {
            // detects what type of inbalance is in this subtree
            if (altTree._left._right._height > altTree._left._left._height) {
                altTree._left = altTree._left.rotateLeft();
            }
            altTree = altTree.rotateRight();
        }

        return altTree;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO
        if (this._value == null) {
            return this;
        }
        //
        if (element.compareTo(this._value) > 0) {
            this._right = (AVLTree<T>) this._right.remove(element);
        }
        else if (element.compareTo(this._value) < 0) {
            this._left = (AVLTree<T>) this._left.remove(element);
        }
        else {

            this._size --;

            if (this._left.isEmpty() && this._right.isEmpty()) {
                return new AVLTree<>();
            }

            if (this._right.isEmpty()) {
                return this._left;
            }
            else {
                this._value = this._right.findMin();
                this._right = (AVLTree<T>) this._right.remove(this._value);
            }

        }
        AVLTree <T> altTree = this;

        altTree._height = Math.max(altTree._left._height , altTree._right._height) + 1;
        altTree._size = (altTree._left._size + altTree._right._size) + 1;

        int balanceFactor = altTree._left._height - altTree._right._height;

        if (balanceFactor > 1) {
            if (altTree._left._right._height > altTree._left._left._height) {
                altTree._left = altTree._left.rotateLeft();
            }
            altTree = altTree.rotateRight();
        }

        if (balanceFactor < -1) {
            if (altTree._right._left._height > altTree._right._right._height) {
                altTree._right = altTree._right.rotateRight();
            }
            altTree = altTree.rotateLeft();
        }
        return altTree;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if (this._left.isEmpty()) {
            return this._value;
        }
        else {
            return this._left.findMin();
        }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if (this._right.isEmpty()) {
            return this._value;
        }
        else {
            return this._right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        // Checks the entire tree for a value (element).
        if (this.isEmpty()) {
            return false;
        }
        // if value is less than 0 return right
        else if (_value.compareTo(element) < 0) {
            return _right.contains(element);
        }
        // if value is greater than 0 return left
        else if (_value.compareTo(element) > 0) {
            return _left.contains(element);
        }
        else {
            return true;
        }


    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        // Simply specifies if a value is in the range of (start, end).
        if (this.isEmpty()) {
            return false;
        }

        if (_value.compareTo(start) >= 0 && _value.compareTo(end) <= 0) {
            return true;
        }

        if (_value.compareTo(end) > 0) {
            return this._left.rangeContain(start, end);
        }
        else {
            return this._right.rangeContain(start, end);
        }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }

}

