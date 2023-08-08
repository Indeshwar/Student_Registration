package com.web.student_register.Service;

import com.web.student_register.Dto.StudentDto;
import com.web.student_register.entity.Month;
import com.web.student_register.entity.Student;
import com.web.student_register.entity.Week;
import com.web.student_register.entity.Year;
import com.web.student_register.exception.StudentNotFoundException;
import com.web.student_register.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student saveStudent(StudentDto studentDto){
        Student student = new Student();
        student.setStudentName(studentDto.getStudentName());
        //add week to student
        Week week = new Week();
        week.setWeekNum(studentDto.getWeekNum());
        student.getWeeks().add(week);

        //add Week to Month
        Month month = new Month();
        month.setMonthName(studentDto.getMonthName());
        month.getWeeks().add(week);
        student.getMonths().add(month);


        //add Month to Year
        Year year = new Year();
        year.setYear(studentDto.getYear());
        year.getMonths().add(month);
        student.getYears().add(year);

        System.out.println("Query fired ------------>");
        //save data to the database
        return studentRepo.save(student);

    }

    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(Long studentId){
        return studentRepo.findById(studentId).orElseThrow(()-> new StudentNotFoundException(studentId + " not found"));
    }

    public Week updateWeek(Week w, StudentDto s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sunday", w.getSunday());
        map.put("monday", w.getMonday());
        map.put("tuesday", w.getTuesday());
        map.put("wednesday", w.getWednesday());
        map.put("thursday", w.getThursday());
        map.put("friday", w.getFriday());
        map.put("saturday", w.getSaturday());
        String dayName = s.getDayName();
//            if student is present add 1, otherwise add -1
        if (s.isAttendance()) {
            if (map.get(dayName) == null) { //set initial case
                w.setDay(1, dayName);
                w.setTotalPresentDay(w.getTotalPresentDay() + 1);
                w.setConsecutiveDay(0);
            } else if (map.get(dayName) == -1) { //update the previous day
                w.setDay(1, dayName); //update present
                w.setTotalPresentDay(w.getTotalPresentDay() + 1); // update the presentDay
                w.setTotalAbsentDay(w.getTotalAbsentDay() - 1); //update the absentDay
                w.setConsecutiveDay(0);

            }

        } else if (!s.isAttendance() && !s.isHoliday()) {
            Integer totalConsecutiveDays = w.getConsecutiveDay() + 1;
            if (map.get(dayName) == null) {
                w.setDay(-1, dayName);
                w.setTotalAbsentDay(w.getTotalAbsentDay() + 1);
                w.setConsecutiveDay(totalConsecutiveDays);
            } else if (map.get(dayName) == 1) {
                w.setDay(-1, dayName);
                  // update the presentDay
                w.setTotalAbsentDay(w.getTotalAbsentDay() + 1); //update the absentDay
                w.setTotalPresentDay(w.getTotalPresentDay() - 1); // update the presentDay
                w.setConsecutiveDay(totalConsecutiveDays);  //update the consecutive day
            }

        } else if (s.isHoliday()) {
            w.setDay(0, dayName);
            Integer totalHolidays = w.getTotalHoliday() + 1;
            w.setTotalHoliday(totalHolidays);

        }
        return w;


    }


    public Student updateStudent(StudentDto studentDto){
        Student student = getStudentById(studentDto.getStudentId());

        Collection<Year> years = student.getYears();
        Optional<Year> optionalYear = years.stream().filter(y-> y.getYear().equals(studentDto.getYear())).findFirst();
        Year year;
        Month month;
        Week week;
        Week updatedWeek;
        if(optionalYear.isPresent()){
            year = optionalYear.get();
            Collection<Month> months = year.getMonths();

            Optional<Month> monthOptional = months.stream()
                    .filter(m -> m.getMonthName().equalsIgnoreCase(studentDto.getMonthName()))
                    .findFirst();

            if(monthOptional.isPresent()){
                month = monthOptional.get();
                Collection<Week> weeks = month.getWeeks();
                Optional<Week> weekOptional = weeks.stream().filter(w-> w.getWeekNum().equals(studentDto.getWeekNum())).findFirst();
                if(weekOptional.isPresent()) {
                    week = weekOptional.get();
                    updatedWeek = updateWeek(week, studentDto);

                }else {
                    week = new Week();
                    week.setWeekNum(studentDto.getWeekNum());
                    updatedWeek = updateWeek(week, studentDto);
                    month.getWeeks().add(updatedWeek);

                }

            }else{
                week = new Week();
                week.setWeekNum(studentDto.getWeekNum());
                updatedWeek = updateWeek(week, studentDto);

                month = new Month();
                month.setMonthName(studentDto.getMonthName());
                month.getWeeks().add(updatedWeek);
                year.getMonths().add(month);
               // student.getMonths().add(month);

            }

        }else{ //if year is not present, do the following
            week = new Week();
            week.setWeekNum(studentDto.getWeekNum());
            updatedWeek = updateWeek(week, studentDto);

            month = new Month();
            month.setMonthName(studentDto.getMonthName());
            month.getWeeks().add(updatedWeek);

            year = new Year();
            year.setYear(studentDto.getYear());
            year.getMonths().add(month);

            student.getYears().add(year);
        }

            System.out.println("update query fired --------------->");
            return studentRepo.save(student);
    }

    public void deleteStudent(Long Id){
        Student student = getStudentById(Id);
        studentRepo.delete(student);

    }

    public Integer getTotalAbsentDaysInMonth(StudentDto studentDto){
        Student s = getStudentById(studentDto.getStudentId());
        Collection<Year> years = s.getYears();
        Optional<Year> year = years.stream().filter(y-> y.getYear().equals(studentDto.getYear())).findFirst();
        Integer totalAbsentDays = 0;

        if(year.isPresent()){
            Collection<Month> months = year.get().getMonths();
            Optional<Month> month = months.stream().filter(m-> m.getMonthName().equalsIgnoreCase(studentDto.getMonthName())).findFirst();
            if(month.isPresent()){
                Collection<Week> weeks = month.get().getWeeks();
                for(Week w : weeks){
                    totalAbsentDays += w.getTotalAbsentDay();
                }

            }else{
                return 0;
            }

        }else{
            return 0;
        }
        return totalAbsentDays;
    }

}
