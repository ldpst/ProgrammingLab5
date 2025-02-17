package ru.itmo.prog.lab5.object;

import ru.itmo.prog.lab5.utils.Validatable;
import ru.itmo.prog.lab5.utils.ValidationError;

import java.util.Objects;

/**
 * Класс человек
 *
 * @author ldpst
 */
public class Person implements Validatable, Comparable<Person> {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final java.util.Date birthday; //Поле может быть null
    private final Long weight; //Значение поля должно быть больше 0
    private final String passportID; //Длина строки не должна быть больше 25, Строка не может быть пустой, Поле не может быть null

    public Person(String name, java.util.Date birthday, Long weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
        if (!isValid()) {
            throw new ValidationError("Person");
        }
    }

    @Override
    public boolean isValid() {
        if (name == null) return false;
        if (weight <= 0) return false;
        return passportID != null && !passportID.isEmpty() && passportID.length() <= 25;
    }

    /**
     * Метод, возвращающий поле name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий поле birthday
     *
     * @return birthday
     */
    public java.util.Date getBirthday() {
        return birthday;
    }

    /**
     * Метод, возвращающий поле weight
     *
     * @return weight
     */
    public long getWeight() {
        return weight;
    }

    /**
     * Метод, возвращающий поле passportID
     *
     * @return passportID
     */
    public String getPassportID() {
        return passportID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return (Objects.equals(name, person.getName()) &&
                Objects.equals(birthday, person.getBirthday()) &&
                Objects.equals(weight, person.getWeight()) &&
                Objects.equals(passportID, person.getPassportID()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, weight, passportID);
    }

    @Override
    public String toString() {
        return "Имя: " + toNotNullSting(this.name)  + "\nДень рождения: " + toNotNullSting(this.birthday) + "\nВес: " + toNotNullSting(this.weight) + "\nАйди паспорта: " + toNotNullSting(this.passportID);

    }

    /**
     * Метод возвращающий поле в виде строки
     * @param object конвертирующийся объект
     * @return строка
     */
    private String toNotNullSting(Object object) {
        return ((object == null) ? "null" : object.toString());
    }

    /**
     * Метод для сравнения с другим объектом Person
     *
     * @param o объект типа Person для сравнения
     * @return Результат меньше нуля, если other больше данного объекта. Результат равен нулю, если элементы равны. Результат больше нуля, если данный объект больше other
     */
    @Override
    public int compareTo(Person o) {
        return birthday.compareTo(o.getBirthday());
    }

    /**
     * Метод для сравнения двух Person
     *
     * @param x объект 1
     * @param y объект 2
     * @return Результат меньше нуля, если y больше данного x. Результат равен нулю, если элементы равны. Результат больше нуля, если данный x больше y
     */
    public int compare(Person x, Person y) {
        return Long.compare(x.getBirthday().getTime(), y.getBirthday().getTime());
    }
}