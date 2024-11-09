package Config;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.iwaconsolti.school.demo.model.Student;

@Configuration
public class AppConfig {

    @Bean
    public School school() {return new School();
    }

    @Bean
    public Student student() {return new Student();
    }

    @Bean
    public Course course() {return new Course();
    }

    @Bean
    public Grade grade() {return new Grade();
    }

}
