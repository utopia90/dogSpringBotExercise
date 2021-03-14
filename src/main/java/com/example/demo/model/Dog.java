package com.example.demo.model;


import javax.persistence.*;


@Entity
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "owner")
    private String owner;
    @Column(name = "race")
    private String race;
    @Column(name = "is_vaccinated")
    private boolean isVaccinated;
    @Column(name = "age")
    private Integer age;


    public Dog() {
    }

    public Dog( String name, String owner, String race, boolean isVaccinated, Integer age) {
        this.name = name;
        this.owner = owner;
        this.race = race;
        this.isVaccinated = isVaccinated;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "Dog{" +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", race='" + race + '\'' +
                ", isVaccinated=" + isVaccinated +
                ", age=" + age+
                '}';
    }


}
