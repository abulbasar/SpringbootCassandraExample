package com.example.configurations;


import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.mapping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.datastax.driver.mapping.NamingConventions.LOWER_CAMEL_CASE;
import static com.datastax.driver.mapping.NamingConventions.LOWER_SNAKE_CASE;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Autowired
    private AppProperties appProperties;

    @Override
    protected String getKeyspaceName() {
        return appProperties.getProperty("cassandra.keyspace");
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster(){
        super.cluster();

        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(appProperties.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.valueOf(appProperties.getProperty("cassandra.port")));
        cluster.setUsername(appProperties.getProperty("cassandra.username"));
        cluster.setPassword(appProperties.getProperty("cassandra.password"));
        cluster.setReconnectionPolicy(new ConstantReconnectionPolicy(10000));
        cluster.setJmxReportingEnabled(false);
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        cluster.setStartupScripts(getStartupScripts());
        cluster.setSocketOptions(getSocketOptions());
        //cluster.setClusterBuilderConfigurer(getClusterBuilderConfigurer());

        return cluster;
    }






    @Bean
    public CassandraSessionFactoryBean session(){

        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);



        return session;
    }



    @Bean
    public CassandraMappingContext mappingContext(){
        return new CassandraMappingContext();
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }






    @Bean
    public MappingManager mappingManager(Session session) {
        final PropertyMapper propertyMapper =
                new DefaultPropertyMapper()
                        .setNamingStrategy(new DefaultNamingStrategy(LOWER_CAMEL_CASE, LOWER_SNAKE_CASE));
        final MappingConfiguration configuration =
                MappingConfiguration.builder().withPropertyMapper(propertyMapper).build();
        return new MappingManager(session, configuration);
    }

    @Override
    public SocketOptions getSocketOptions(){
        SocketOptions options = new SocketOptions();
        options.setConnectTimeoutMillis(30000);
        options.setReadTimeoutMillis(300000);
        options.setTcpNoDelay(true);
        return options;
    }


}
