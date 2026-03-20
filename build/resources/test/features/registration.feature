Feature: Registro de llegada de paciente

  Escenario: Registro de llegada exitoso
    Dado que el usuario está en la página de registro de llegada
    Cuando ingresa datos de llegada válidos
    Entonces debería ver un mensaje de éxito en el diario de recepción

  Escenario: Registro de llegada fallido
    Dado que el usuario está en la página de registro de llegada
    Cuando ingresa datos de llegada incompletos o inválidos
    Entonces debería ver mensajes de error de validación
