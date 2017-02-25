package com.hssh.spring.cloud;

import com.hssh.spring.cloud.persistent.entity.dto.UserDto;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by hssh on 2017/2/25.
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ExecutableValidator executableValidator = validator.forExecutables();
        Car car = new Car();
        Method method = Car.class.getMethod("run", UserDto.class, Integer.class);
        UserDto userDto = new UserDto();
        userDto.setPhone("11111111111111111111111111111111111111");
        Set<ConstraintViolation<Car>> methodValidators = executableValidator.validateParameters(car, method, new Object[]{userDto, 11});
        System.out.println(methodValidators);

    }

    static class Car {
        public Car(){};

        public int run(@Valid UserDto userDto, @Min(10) Integer a) {
            return a;
        }
    }

}
