package ru.gorbach.additional.hw14.jaxb.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class ttt {


    @XmlType(propOrder = {"place", "animals"}, name = "zoo")
    @XmlRootElement
    public static class Zoo {
        private String place;
        private List<Cat> animals = new ArrayList<>();

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        @XmlElement(name = "animal")
        @XmlElementWrapper
        public List<Cat> getAnimals() {
            return animals;
        }

        public void setAnimals(List<Cat> animals) {
            this.animals = animals;
        }
    }

    @XmlType(propOrder = {"name", "age", "weight"})
    public static class Cat {
        private String name;
        private int age;
        private int weight;

        public Cat() {
        }

        public Cat(String name, int age, int weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
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

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }


    }

    private Zoo createZoo() {
        Zoo zoo = new Zoo();
        zoo.setPlace("usa");
        zoo.setAnimals(createCats());
        return zoo;
    }

    private List<Cat> createCats() {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat("Ivan", 11, 4);
        Cat cat2 = new Cat("Vasya", 4, 1);
        Cat cat3 = new Cat("Petya", 15, 7);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        return cats;
    }

    public static void main(String[] args) throws JAXBException {
        ttt ttt = new ttt();

        JAXBContext context = JAXBContext.newInstance(Zoo.class, Cat.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(ttt.createZoo(), System.out);

    }
}
