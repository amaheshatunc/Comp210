package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO (Task 1): dequeue
    public Patient dequeue() {
        int idx = 0;
        Patient highPriority = patients.get(0);

        if (patients.isEmpty()) {
            return null;
        }

        for (int i = 1; i < patients.size(); i++) {
            // Compares the priority of the patients.
            if (highPriority.getPriority().compareTo(patients.get(i).getPriority()) < 0 ) {
                highPriority = patients.get(i);
                idx = i;
            }
            // Comparison finishes and actually removes patients in lines below.
        }
        patients.remove(idx);
        return highPriority;
    }


    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
