package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    Map<String, Student> studentDb;
    Map<String, Teacher> teacherDb;
    Map<String, LinkedHashSet<String>> studentTeacherDb;

    public StudentRepository() {
        studentDb = new HashMap<>();
        teacherDb = new HashMap<>();
        studentTeacherDb = new HashMap<>();
    }


    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {

        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {

        LinkedHashSet<String> students = studentTeacherDb.getOrDefault(teacher, new LinkedHashSet<>());
        students.add(student);
        studentTeacherDb.put(teacher, students);
    }

    public Student getStudentByName(String name) {

        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {

        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {

        return new ArrayList<>(studentTeacherDb.get(teacher));
    }

    public List<String> getAllStudents() {

        return new ArrayList<>(studentDb.keySet());
    }

    public void deleteTeacherByName(String teacher) {

        Set<String> students = studentTeacherDb.get(teacher);

        for(String student : students){
            studentDb.remove(student);
        }
        studentTeacherDb.remove(teacher);
    }

    public void deleteAllTeachers() {

        for(Set<String> ListOfStudents : studentTeacherDb.values()){
            for(String student : ListOfStudents){

                studentDb.remove(student);

            }
        }

        teacherDb.clear();
        studentTeacherDb.clear();
    }
}