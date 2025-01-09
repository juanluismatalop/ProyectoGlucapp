# Proyecto: Aplicación con Firebase y RecyclerView

## Versión 1.3

**Autor:** Juan Luis Mata López  

### Repositorio
Este proyecto se encuentra alojado en un repositorio específico que contiene todas las versiones desarrolladas hasta ahora. Asegúrate de clonar o descargar la versión más reciente para acceder a las funcionalidades descritas.

---

### Cambios Realizados desde la Versión 1.3
En esta versión, hemos realizado mejoras significativas en el sistema de autenticación y en las funcionalidades de la aplicación:

- **Autenticación mejorada**:
  - **Registro de usuarios**: Ahora permite registrar un usuario con un correo electrónico y contraseña, almacenando los datos en Firebase.
  - **Inicio de sesión**: Los usuarios pueden iniciar sesión utilizando el correo electrónico y la contraseña registrada.
  - **Recuperación de contraseña**: Se incluye una funcionalidad para recuperar la contraseña a través de un enlace enviado por correo electrónico.

- **Inicio de sesión**:
  - El usuario podrá iniciar sesión con el correo y la contraseña registrada o restablecida.

- **MainActivity**:
  - **Cerrar Sesión**: Redirige al usuario al login.
  - **RecyclerView**: Se mantiene la funcionalidad para gestionar noticias:
    - **Scrollear**: Subir y bajar para navegar entre noticias.
    - **Eliminar**: Opción para borrar una noticia seleccionada.
    - **Añadir**: Permite incluir una nueva noticia o dato.
    - **Editar**: Facilita la modificación de una noticia, ya sea su título o el contenido completo.

---

### Funcionalidad General
La aplicación incluye las siguientes características principales:

#### Login:
- Inicio de sesión con usuario y contraseña registrados en Firebase.
- Recuperación de contraseña mediante enlace enviado al correo electrónico.
- Registro de nuevos usuarios con credenciales almacenadas en Firebase.

#### MainActivity:
- Redirección al login al cerrar sesión.
- Implementación de un **RecyclerView** para mostrar noticias:
  - Navegar entre noticias desplazándose verticalmente.
  - Botones:
    - **Eliminar**: Borra la noticia seleccionada.
    - **Añadir**: Crea una nueva noticia.
    - **Editar**: Permite modificar el título o contenido de una noticia.

---

## Cambios Anteriores

### Versión 1.2
- Se implementó el uso de un **RecyclerView** dentro de un fragmento en el menú de inicio.
- Se mantuvieron las funcionalidades básicas del login y cierre de sesión.
- Añadido un menú con botones principales para navegar entre diferentes apartados.

### Versión 1.1
- Reemplazo de botones del menú por **ImageButton** para mejorar la interfaz.
- Sustitución de **CardViews** por un **RecyclerView**, donde las noticias se presentan en fragmentos dentro de dicho RecyclerView.
- Introducción de funcionalidades básicas para:
  - Inicio de sesión con un usuario genérico.
  - Cierre de sesión que redirige al login.
  - Interacción con noticias mediante scroll, eliminación, adición y edición.

---

## Notas
- Debido al modo oscuro del dispositivo, es posible que las letras no se visualicen correctamente en el video demostrativo.

---

## Instalación
1. Clona este repositorio:  
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Abre el proyecto en tu entorno de desarrollo (Android Studio).
3. Configura las credenciales de Firebase en el archivo `google-services.json`.
4. Ejecuta la aplicación en un dispositivo físico o emulador.

---

## Tecnologías
- **Firebase**: Autenticación y base de datos en tiempo real.
- **Android**: Desarrollo nativo en Java/Kotlin.
- **RecyclerView**: Para mostrar y gestionar listas dinámicas de datos.

---

## Autor
Juan Luis Mata López
