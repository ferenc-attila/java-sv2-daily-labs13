package day01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassNoteBookTest {

    ClassNoteBook classNoteBook = new ClassNoteBook();

    @BeforeEach
    void init() {
        Student firstStudent = new Student(1, "John Doe");
        Student secondStudent = new Student(2, "Jane Doe");
        Student thirdStudent = new Student(3, "Jack Doe");
        Student fourthStudent = new Student(4, "Jill Doe");
        Student fifthStudent = new Student(5, "Janet Doe");
        classNoteBook.addStudent(firstStudent);
        classNoteBook.addStudent(secondStudent);
        classNoteBook.addStudent(thirdStudent);
        classNoteBook.addStudent(fourthStudent);
        classNoteBook.addStudent(fifthStudent);
    }

    @Test
    void addStudentTest() {
        Student student = new Student(6, "Julia Doe");
        classNoteBook.addStudent(student);

        assertEquals(6, classNoteBook.getMarks().size());
        assertTrue(classNoteBook.getMarks().keySet().contains(new Student(6, "Julia Doe")));
        assertEquals(0, classNoteBook.getMarks().get(student).size());
    }

    @Test
    void addMarkTest() {
        classNoteBook.addMark(5, 5);
        assertEquals(1, classNoteBook.getMarks().get(new Student(5, "Janet Doe")).size());
        assertEquals(5, classNoteBook.getMarks().get(new Student(5, "Janet Doe")).get(0));
        classNoteBook.addMark(5, 4);
        classNoteBook.addMark(5, 5);
        classNoteBook.addMark(5, 3);
        assertEquals(4, classNoteBook.getMarks().get(new Student(5, "Janet Doe")).size());
        assertEquals(3, classNoteBook.getMarks().get(new Student(5, "Janet Doe")).get(3));
    }
}