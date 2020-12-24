# language: es
Característica: Verificar la funcionalidad de login

  Escenario: Login válido de administrador
    Dado que el usuario esta en la pagina de login
    Cuando ingresa el usuario y la clave
    Y presiona Enter
    Entonces sera redirigido a la pagina de seleccion de entidad

  Esquema del escenario: Login invalido de administrado
    Dado que el usuario esta en la pagina de login
    Cuando ingresa un "<usuario>" invalido y una "<clave>" erronea
    Y presiona Ingresar
    Entonces recibira un error "Usuario/Contraseña incorrectos !"

    Ejemplos: 
      | usuario | clave     |
      | admin   | admion123 |
      | armin   | admin     |
#
  #Escenario: Login valido de usuario
    #Dado que el usuario esta en la pagina de login
    #Cuando ingresa el usuario y la clave
    #Y presiona Ingresar
    #Entonces sera redirigido a la pagina principal
