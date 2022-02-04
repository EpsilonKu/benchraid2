package com.benchraid.benchraid.entities;

import com.benchraid.benchraid.enums.Position;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private Position position;
    private int salary;
    private static int staffUnit = 0;
    private int departmentCode;

}
