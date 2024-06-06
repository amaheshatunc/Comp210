package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient newPatient = new Patient(value);
        _heap.add(newPatient);
        int child = _heap.size() - 1;
        int parent = (child -1) / 2;

        while (parent >= 0 && _heap.get(child).compareTo(_heap.get(parent)) > 0) {
            _heap.set(child, _heap.get(parent));
            _heap.set(parent, newPatient);
            child = parent;
            parent = (parent - 1) / 2;
        }
    }



// DO THE REST OF THIS AND SEE WHAT'S WRONG WITH THE THING ABOVE>


    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient newPatient = new Patient(value, priority);
        _heap.add(newPatient);
        int child = _heap.size() - 1;
        int parent = (child - 1)/2;

        while (parent >= 0 && _heap.get(child).compareTo(_heap.get(parent)) > 0) {
            _heap.set(child, _heap.get(parent));
            _heap.set(parent, newPatient);
            child = parent;
            parent = (parent - 1)/2;
        }
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }

        V result = _heap.get(0).getValue();
        _heap.set(0,_heap.get(size()-1));
        _heap.remove(size()-1);

        int parent = 0;
        int childCounter;
        // While there is a left child

        while (parent * 2 + 1 < _heap.size()) {
            // If there is a right child

            if (parent * 2 + 2 < _heap.size()) {
                Patient parentPatient = (Patient) _heap.get(parent); // Parent patient
                Patient leftChild = (Patient) _heap.get(parent * 2 + 1); // LC patient
                Patient rightChild = (Patient) _heap.get(parent * 2 + 2);// RC patient
                // Initializes biggestChild to be left
                Patient biggest = leftChild;
                childCounter = parent * 2 + 1;

                if (rightChild.getPriority().compareTo(biggest.getPriority()) > 0) {
                    biggest = rightChild;
                    childCounter = parent * 2 + 2;
                }
                // Checks if biggest child has greater priority than parent.

                if (biggest.getPriority().compareTo(parentPatient.getPriority()) > 0) {
                    Patient temp = (Patient) _heap.get(parent);
                    _heap.set(parent, biggest);
                    _heap.set(childCounter, temp);
                    parent = childCounter;
                }

                else {
                    break;
                }
            }
            else {
                // One child, left
                childCounter = parent * 2 + 1;
                Patient leftChild = (Patient) _heap.get(parent * 2 + 1);
                Patient parentPatient = (Patient) _heap.get(parent);

                if (leftChild.getPriority().compareTo(parentPatient.getPriority()) > 0) {
                    Patient temp = (Patient) _heap.get(parent);
                    _heap.set(parent, leftChild);
                    _heap.set(childCounter, temp);
                    parent = childCounter;
                }

                else {
                    break;
                }
            }
        }
        return result;
    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {

    	 if (_heap.isEmpty()) {
             return null;
         }

         else {
             return (_heap.get(0).getValue());
         }
    }

    // TODO (part 2B) : updatePriority
    // ASK HELP FOR UPDATE PRIORITY AND WHY IT DOES NOT WORK.
    public void updatePriority(V value, P newPriority) {
        int i = 0;

        while (i <= size() && !_heap.get(i).getValue().equals(value)) {
            i++;
        }

        if (i == _heap.size()) {
            return;
        }

        Patient newPatient = new Patient(value, newPriority);
        int childCounter = i;
        int parent = (childCounter - 1) / 2;
        _heap.set(i, newPatient);

        while (parent >= 0 && _heap.get(childCounter).compareTo(_heap.get(parent)) > 0) {

            _heap.set(childCounter, _heap.get(parent));
            _heap.set(parent, newPatient);
            childCounter = parent;
            parent = (parent - 1 ) / 2;

        }

        while (parent * 2 + 1 < _heap.size()) {

            if (parent * 2 + 2 < _heap.size()) {

                Patient parentPatient = (Patient) _heap.get(parent);
                Patient leftChild = (Patient) _heap.get(parent * 2 + 1);
                Patient rightChild = (Patient) _heap.get(parent * 2 + 2);
                Patient biggest = leftChild;
                childCounter = parent * 2 + 1;

                if (rightChild.getPriority().compareTo(biggest.getPriority()) > 0) {

                    biggest = rightChild;
                    childCounter = parent * 2 + 2;

                }

                if (biggest.getPriority().compareTo(parentPatient.getPriority()) > 0) {

                    Patient temp = (Patient) _heap.get(parent);
                    _heap.set(parent, biggest);
                    _heap.set(childCounter, temp);
                    parent = childCounter;

                }

                else {
                    break;
                }
            }
            // FINISH THIS AND WRITE THE TEST CASES IN THE MAIN.
            else {

                childCounter = parent * 2 + 1;
                Patient leftChild = (Patient) _heap.get(parent * 2 + 1);
                Patient parentPatient = (Patient) _heap.get(parent);

                if (leftChild.getPriority().compareTo(parentPatient.getPriority()) > 0) {

                    Patient temp = (Patient) _heap.get(parent);
                    _heap.set(parent, leftChild);
                    _heap.set(childCounter, temp);
                    parent = childCounter;

                }

                else {
                    break;
                }
            }
        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor

    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        int i = 0;

        while (i < initialEntries.length) {
            Patient newPatient = (Patient) initialEntries[i];
            _heap.add(newPatient);
            int child = _heap.size() - 1;
            int parent = (child - 1)/2;

            while (parent >= 0 && _heap.get(child).compareTo(_heap.get(parent)) > 0) {
                _heap.set(child, _heap.get(parent));
                _heap.set(parent, newPatient);
                child = parent;
                parent = (parent - 1)/2;
            }

            i++;
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

}
