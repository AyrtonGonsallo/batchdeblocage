package com.processes.batchblocage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication(
        exclude = {
                BatchAutoConfiguration.class,
                JmxAutoConfiguration.class
        },
        excludeName = {
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        }
)
public class BatchblocageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchblocageApplication.class, args);
	}

}
