package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class DatabaseCamelRouter extends RouteBuilder {

    @Override
    public void configure() {
        rest()
                .get("create").produces("text/plain").to("direct:create")
                .get("users").produces("text/plain").to("direct:users");

        from("direct:users")
                .setBody(constant("select * from users"))
                .to("jdbc:dataSource");

        from("direct:create")
                .setBody(constant("""
                        CREATE TABLE IF NOT EXISTS users (
                          name VARCHAR(255),
                          age INT
                        );
                        """))
                .to("jdbc:dataSource")
                .setBody(constant("""
                        INSERT INTO users (name, age)
                        VALUES ('Test', 100);
                        """))
                .to("jdbc:dataSource");
    }

}
