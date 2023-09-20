package jpa.web.part.first.component;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig {

    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

}
