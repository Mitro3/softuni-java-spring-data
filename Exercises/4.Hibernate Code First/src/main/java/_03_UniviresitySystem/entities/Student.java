package _03_UniviresitySystem.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students", schema = "code_first")
public class Student extends Person {

    @Column(name = "average_grade", nullable = false)
    private float averageGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id")
    )
    private Set<Course> courses;

    public Student() {
        super();
        this.courses = new HashSet<>();
    }

    public Student(String firstName, String lastName, String phoneNumber, float averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
