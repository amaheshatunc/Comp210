package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        MaxBinHeapER hospital = new MaxBinHeapER();
        hospital.dequeue();

        // this will not work anymore as it listed in another test

    }

    // test Part 2
    public static void testP2(){
       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MaxBinHeapER hospital = new MaxBinHeapER();

        hospital.enqueue(1, 27);
        hospital.enqueue(2, 33);
        hospital.enqueue(3, 32);
        hospital.enqueue(4, 17);
        hospital.enqueue(5, 45);


        System.out.println("This is the normal unaltered list.");
        for (int i = 0; i < hospital.size(); i++){
            Patient newPatient = (Patient) hospital.getAsArray()[i];
            System.out.println(newPatient.getValue() + " " + newPatient.getPriority());
        }


        // This is the task 1 task for testing if the dequeue would work.

        hospital.dequeue();
        System.out.print("This is the dequeued list.");
        System.out.println();

        for (int i = 0; i < hospital.size(); i++){
            Patient p = (Patient) hospital.getAsArray()[i];
            System.out.println(p.getValue() + " " + p.getPriority());
        }
        System.out.print("This is the dequeued list but organized in priority.");
        System.out.println();
        hospital.updatePriority(1, 34);
        hospital.updatePriority(2, 22);
        hospital.updatePriority(3, 12);

        for (int i = 0; i < hospital.size(); i++) {
            Patient p = (Patient) hospital.getAsArray()[i];
            System.out.println(p.getValue() + " " + p.getPriority());
        }

    }



    /*
    Part 3
     */
    public static void testP3(){

        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();

        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Val: " + arr[i].getValue() + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */
        double[] runTimes = compareRuntimes();
        for(int i = 0; i < 4; i++) {
            System.out.println(runTimes[i]);
        }

    }

    public static void fillER(MaxBinHeapER complexER) {

        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {

        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {

        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {

        // Array which you will populate as part of Part 4

        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here

        int i = 0;
        double time = System.nanoTime();

        while (i < simplePQ.size()) {
            simplePQ.dequeue();
            i++;
        }

        double versionTime = System.nanoTime();
        results[0] = versionTime - time;
        results[1] = (versionTime - time) / 100000;


        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here

        int index = 0;
        double time2 = System.nanoTime();
        while (index < binHeap.size()) {
            binHeap.dequeue();
            index++;
        }

        double time3 = System.nanoTime();
        results[2] = time3 - time2;
        results[3] = (time3 - time2) / 100000;

        return results;
    }

}

