package com.emythmakers.ashik.icare.database;


public class ContactModel {

    private String id;
    private String name;
    private String age;
    private String height;
    private String weight;
    private static String positionID;

    public static String getPositionID() {
        return positionID;
    }

    public static void setPositionID(String positionID) {
        ContactModel.positionID = positionID;
    }


    public ContactModel(String id, String name, String age, String height, String weight) {
        this.id = id;
        setName(name);
        setAge(age);
        setHeight(height);
        setWeight(weight);
    }

    public ContactModel(String name, String age, String height, String weight) {
        setName(name);
        setAge(age);
        setHeight(height);
        setWeight(weight);
    }
    public ContactModel(String name) {
        setName(name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
