package cz.mendelu;

public class Properties {
    private String name;
    private int length;
    private int volume;

    public Properties() {
        setName("");
        setLength(0);
        setVolume(0);
    }

    public Properties(String name, int length, int volume) {
        setName(name);
        setLength(length);
        setVolume(volume);
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setLength(int length) {
        this.length = length;
    }
}