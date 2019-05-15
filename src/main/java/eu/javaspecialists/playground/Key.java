package eu.javaspecialists.playground;

public class Key {
    private final int id;

    public Key(int id) {
        this.id = id;
    }

    public int hashCode() {
        try {
            Thread.sleep(100); // now THIS is slow
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Key)) return false;
        return id == ((Key) obj).id;
    }
}
