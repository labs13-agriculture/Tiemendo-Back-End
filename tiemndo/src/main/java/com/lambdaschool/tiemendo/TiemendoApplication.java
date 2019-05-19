package com.lambdaschool.tiemendo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@EnableJpaAuditing
@SpringBootApplication
public class TiemendoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TiemendoApplication.class, args);
    }

}
