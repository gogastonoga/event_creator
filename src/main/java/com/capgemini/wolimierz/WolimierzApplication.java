package com.capgemini.wolimierz;

import com.capgemini.wolimierz.utils.LocalDateDeserializer;
import com.capgemini.wolimierz.utils.LocalDateSerializer;
import com.capgemini.wolimierz.utils.LocalDateTimeDeserializer;
import com.capgemini.wolimierz.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class WolimierzApplication {

    public static void main(String[] args) {
        SpringApplication.run(WolimierzApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
       /* EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder
                .addScript("sql-scripts/schema.sql")
                .addScript("sql-scripts/data.sql")
                .build();

        return dataSource;*/
    }

    //TODO remove
   /* @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }*/

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule javaTimeModule = new SimpleModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        mapper.registerModule(javaTimeModule);
        return mapper;
    }
}
