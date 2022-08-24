package rikkei.academy.model;

import java.io.Serializable;

public class Singer implements Comparable<Singer>, Serializable  { //Interface doc ghi nhi phan
    private int id;
    private String name;
    private int age;

    public Singer() {
    }

    public Singer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Singer(String newName, String newAge) {
        this.name = newName;
        this.age = Integer.parseInt(newAge);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Singer o) {
        int temp = this.getName().compareTo(o.getName());
        if(temp!=0){
            return temp;
        }
        temp = this.getAge() - o.getAge();
        return temp;
    }
}
