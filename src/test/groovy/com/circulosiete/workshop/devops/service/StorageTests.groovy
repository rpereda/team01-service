package com.circulosiete.workshop.devops.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.ui.Model
import spock.lang.Specification


class StorageTests extends Specification {

    def 'probando storage service con valor'() {
        given:

        ValueOperations<Object, Object> valueOperations = Stub()
        valueOperations.get("saludo") >> "Un saludo de prueba"

        StringRedisTemplate stringRedisTemplate = Stub()
        stringRedisTemplate.opsForValue() >> valueOperations

        Storage storage = new Storage(stringRedisTemplate)

        when:
        def resultado = storage.saludo()

        then:
        resultado == "Un saludo de prueba"
    }

    def 'probando storage service usando valor por defecto'() {
        given:

        ValueOperations<Object, Object> valueOperations = Stub()
        valueOperations.get("saludo") >> null

        StringRedisTemplate stringRedisTemplate = Stub()
        stringRedisTemplate.opsForValue() >> valueOperations

        Storage storage = new Storage(stringRedisTemplate)

        when:
        def resultado = storage.saludo()

        then:
        resultado == "Extraño"
    }
}
