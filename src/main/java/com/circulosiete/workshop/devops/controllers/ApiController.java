package com.circulosiete.workshop.devops.controllers;

import com.circulosiete.workshop.devops.entities.Saludo;
import com.circulosiete.workshop.devops.service.Storage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/")
public class ApiController {
  private final Storage storage;

  public ApiController(Storage storage) {
    this.storage = storage;
  }

  @GetMapping("/v1/saludos")
  public Saludo saludar() {
    Saludo saludo = new Saludo();
    saludo.setNombre(storage.saludo());
    System.out.println("Realizando saludo a " + saludo.getNombre());
    return saludo;
  }

  @GetMapping("/v2/saludos/{word}")
  public Saludo guardarSaludo(@PathVariable String word) {
    Saludo saludoModificado = new Saludo();
    System.out.println("Realizando saludo modificado id" + word);
    saludoModificado.setNombre(storage.guardarSaludo(word));
    System.out.println("Realizando saludo modificado saludoModificado nombre" + saludoModificado.getNombre());
    return saludoModificado;
  }
}
