package lambda.part1.exercise;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Exercise1 {

    @Test
    public void sortPersonsByAgeUsingArraysSortComparator() {
        Person[] persons = getPersons();

        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };

        Arrays.sort(persons, comparator);

        assertArrayEquals(new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        }, persons);
    }

    @Test
    public void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        assertArrayEquals(new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        }, persons);
    }

    @Test
    public void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int i = o1.getLastName().compareTo(o2.getLastName());

                if (i != 0) {
                    return i;
                }
                return o1.getFirstName().compareTo(o2.getFirstName());

            }
        });

        assertArrayEquals(new Person[]{
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45),
                new Person("Николай", "Зимов", 30),
                new Person("Иван", "Мельников", 20)
        }, persons);
    }

    @Test
    public void findFirstWithAge30UsingGuavaPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        Person person = null;

        Predicate<Person> predicate = new Predicate<Person>() {
            @Override
            public boolean apply(Person o) {
                return o.getAge() == 30;
            }
        };

        person = FluentIterable.from(persons)
                .filter(predicate)
                .first().get();

        assertEquals(new Person("Николай", "Зимов", 30), person);
    }

    @Test
    public void findFirstWithAge30UsingGuavaAnonymousPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        Person person = null;

        person = FluentIterable.from(persons)
                .filter(new Predicate<Person>() {
                    @Override
                    public boolean apply(Person person) {
                        return person.getAge() == 30;
                    }
                })
                .first().get();

        assertEquals(new Person("Николай", "Зимов", 30), person);
    }

    private Person[] getPersons() {
        return new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Алексей", "Доренко", 40),
                new Person("Николай", "Зимов", 30),
                new Person("Артем", "Зимов", 45)
        };
    }
}
