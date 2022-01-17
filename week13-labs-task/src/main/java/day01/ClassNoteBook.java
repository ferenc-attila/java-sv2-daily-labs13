package day01;

import java.util.*;

public class ClassNoteBook {

    private Map<Student, List<Integer>> marks = new TreeMap<>();

    public void addStudent(Student student) {
        marks.put(student, new ArrayList<>());
    }

    public void addMark(int id, int mark) {
        validateParameters(id, mark);
        for (Map.Entry<Student, List<Integer>> entry : marks.entrySet()) {
            if (id == entry.getKey().getId()) {
                entry.getValue().add(mark);
            }
        }
    }

    private void validateParameters(int id, int mark) {
        if (mark < 1 || mark > 5) {
            throw new IllegalArgumentException("Invalid mark!");
        }
        Set<Integer> listOfStudentId = new TreeSet<>();
        for (Map.Entry<Student, List<Integer>> entry : marks.entrySet()) {
            listOfStudentId.add(entry.getKey().getId());
        }
        if (!listOfStudentId.contains(id)) {
            throw new IllegalArgumentException("No such student!");
        }
    }

    public Map<Student, List<Integer>> getMarks() {
        return Map.copyOf(marks);
    }
}
