package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {

    private static final String MASTER_PASSWORD = "password321";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // TODO: put
    /**
     * The 'put' method creates an Account object using the key value pair,
     * and inserts the object at the appropriate index
     * based on the hash of they keya
     * @param key: the website name
     * @param value: the password
     */
    @Override
    public void put(K key, V value) {
        Account<K, V> newAcnt = new Account<>(key, value);
        int hash_code = Math.abs(newAcnt.getWebsite().hashCode()%50);

        if(_passwords[hash_code] != null){

            if(_passwords[hash_code].getWebsite().equals(key)){
                _passwords[hash_code].setPassword(value);
                return;
            }

            Account current = _passwords[hash_code];

            while (current.getNext() != null){

                if(current.getWebsite().equals(key)){
                    current.setPassword(value);
                    return;
                }
                else{
                    current = current.getNext();
                }
            }

            if(current.getWebsite().equals(key)){
                current.setPassword(value); //update the value
                return;
            }
            current.setNext(newAcnt);
        }
        //If nothing is already there in the hashmap
        else{
            _passwords[hash_code] = newAcnt;
        }
    }


    /**
     * 'get' returns the value associated with the given key.
     * This operation should have O(1) runtime.
     * If the key is not in the array, return null.
     * @param key
     * @return the value (password) associated with that key
     */
    // TODO: get
    @Override
    public V get(K key) {

        int hash_code = Math.abs(key.hashCode() % 50);

        Account current = _passwords[hash_code];

        if (current == null){
            return null;
        }

        else if (current.getWebsite().equals(key)) {
            return ((V) current.getPassword());
        }

        else {

            while(current.getNext() != null && !(current.getWebsite().equals(key))){
                current = current.getNext();
            }

            if(current.getWebsite().equals(key)){
                return (V) current.getPassword();
            }

            return null;
        }

    }

    // TODO: size
    @Override
    public int size() {

        int k = 0;

        for (int i = 0; i < _passwords.length; i++){

            if (_passwords[i] != null){
                Account current = _passwords[i];

                while(current != null){
                    k++;
                    current = current.getNext();
                }
            }
        }
        return k;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < _passwords.length; i++){

            if (_passwords[i] != null){

                Account current = _passwords[i];
                while(current != null){

                    keys.add((K) current.getWebsite());
                    current = current.getNext();
                }
            }
        }
        return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {

        int hash_code = Math.abs(key.hashCode() % 50);
        Account current = _passwords[hash_code];

        if (this.get(key) == null){
            return null;
        }

        if (current.getWebsite().equals(key)){
            V result = (V) current.getPassword();
            current = current.getNext();
            _passwords[hash_code] = current;
            return result;

        }

        while(current.getNext() != null && !(current.getNext().getWebsite().equals(key))){
            current = current.getNext();
        }

        if(current.getNext().getWebsite().equals(key)){
            V result = (V) current.getNext().getPassword();

            if(current.getNext().getNext() != null) {
                current.setNext(current.getNext().getNext());
                return result;
            }

            else{
                current.setNext(null);
                return result;
            }
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicates = new ArrayList<>();

        for (int i = 0; i < _passwords.length; i++) {

            if (_passwords[i] != null) {
                Account current = _passwords[i];

                while (current != null) {

                    if (current.getPassword().equals(value)) {
                        duplicates.add((K) current.getWebsite());
                    }

                    current = current.getNext();
                }
            }
        }
        return duplicates;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4

        if(length<4){
            targetStringLength = 4;
        }


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
