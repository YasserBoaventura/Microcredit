package com.Microcredito.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Serialization {
	
	@Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        

        // Evita problemas com datas
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Ignora beans vazios (reduz erros de lazy loading)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 🔥 MUITO IMPORTANTE: evita loops infinitos de entidades JPA
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        return mapper;
    }

}
