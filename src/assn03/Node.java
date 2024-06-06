package assn03;



public interface Node<T> {

    T getValue(); // returns a value

    void setValue(T value); // sets a value

    Node<T> getNext(); // returns a value

    void setNext(Node<T> next);


    default boolean hasNext() {
        return (getNext() != null);
    }
}