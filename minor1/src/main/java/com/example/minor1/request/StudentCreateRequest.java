package com.example.minor1.request;

import com.example.minor1.Exception.ContactNumberConstraint;
import com.example.minor1.model.Student;
import com.example.minor1.model.StudentType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {


    @NotBlank(message = "contact Cannot Be blank")
    @ContactNumberConstraint
    private String phoneNo;

    @NotBlank(message = "email Cannot Be blank")
    private String email;

    private String name;

    private String address;


    public Student toStudent(){
        return Student.builder().name(this.getName()).email(this.getEmail()).phoneNo(this.getPhoneNo())
                .address(this.getAddress()).status(StudentType.ACTIVE).build();
    }


}
