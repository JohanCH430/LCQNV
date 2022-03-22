package com.example.lascosasquenovemos.dal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.FirebaseListener;
import com.example.lascosasquenovemos.model.TextoModelo;
import com.example.lascosasquenovemos.view.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TextoDAL implements FirebaseListener {

    private DatabaseReference DataBase;
    private DatabaseReference refTextos;
    private DatabaseReference refTematicaTexto;
    private Context appContext;
    private TextoModelo texto;

    //Constructor con un parámetro para que se pase el contexto de la aplicación que es necesario para usar la base de datos
    public TextoDAL(TextoModelo texto, Context application_context){

        //Se inicializa las variables necesarias
        appContext = application_context;
        this.texto = texto;
        //Se obtiene la instancia de la base de datos con el link de firebase
        FirebaseDatabase DataBaseInstance = FirebaseDatabase.getInstance(appContext.getString(R.string.firebase_realtime_database_URL));

        DataBase = DataBaseInstance.getReference();
        refTextos = DataBase.child("Texto");
        refTematicaTexto = DataBase.child("TematicaTexto");

        //Se pone la persistencia a "true" para que los cambios se guarden

        //DataBaseInstance.setPersistenceEnabled(true);

    }

///////////////////////////////////////////////////////FUNCIONES PARA ESCRITURA DE UN NUEVO TEXTO EN BD////////////////////////////////////////////////////////////

    /*
    Debido a que las funciones de firebase se eejcutan en otro hilo, cada vez que se hace una lectura hay que llamar a la siguiente funcion solo cuando esta lectura
    se haya completado, de esta manera el código a continuación está ordenado en orden de ejecución para una escritura en la base de datos
     */

    //Esta función se llama desde negocio y lee el id correspondiente al texto que se va a insertar, una vez que se haya completado la lectura llama a la siguente función
    public void escribirTexto(){

        //Lectura del ultimo id usado para los textos
        DataBase.child("IDTexto").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        afterReadID(task);
                    }

                }
            }
        });

    }

    //Función que se llama después de la lectura del ID, crea el nuevo id (añadiendo 1 al anterior), escribe ese nuevo id en la BD y llama a la siguiente funcion con
    //el nuevo id generado
    @Override
    public void afterReadID(Task<DataSnapshot> task) {
        //Se crea un nuevo id añadiendole 1 al valor original
        String[] datos = String.valueOf(task.getResult().getValue()).split("-");
        int aux = Integer.parseInt(datos[1 ]) + 1;
        String nuevoID = datos[0] + "-" + String.valueOf(aux);

        //String nuevoID = "aaaa";
        //Se escribe el nuevo valor en la BD
        DataBase.child("IDTexto").setValue(nuevoID).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
            }
        });

        crearTexto(nuevoID);

    }

    //Función que recibiendo el id correspondiente al texto escribe el la BD el contenido y titulo del texto inicial y llama a la siguente función para asignar el
    //texto a una nueva temática
    public int crearTexto(String id){
        //Ha habido un error al generar el id y por tanto no se puede acceder a la base de datos
        if (id == "") return -1;
        texto.setIDTexto(id);

        //Este hijo es el que se va a usar en firebase para la creacion de textos ya que el conteindo cuelga del id
        DatabaseReference refId = refTextos.child(texto.getIDTexto());

        //Solo se hace la segunda escritura si la primera no ha dado fallos
        //Acceso a la base de datos para escribir el valor en la tabla de Texto
        refId.child("Contenido").setValue(texto.getTexto()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                }
        });
        refId.child("Titulo").setValue(texto.getTítulo()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                }
        });

        //Llamada a la funcion encargada de la generacion de los datos en la tabla TematicaTexto
        obtainList();

        return 0; //El método ha tenido éxito
    }

    //Lectura de los textos asociados a la temática especificada, tras hacer la lectura llama a la siguiente función
    public void obtainList(){

        //Lectura del ultimo id usado para los textos
        refTematicaTexto.child(texto.getTemática()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        afterReadList(task);
                    }

                }
            }
        });

    }

    //Última función del proceso de lectura que, tras la lectura correcta de la lista de textos asociadas a la temática, añade el texto que se ha creado anteriormente
    //terminando el proceso de escritura de un nuevo texto
    @Override
    public void afterReadList(Task<DataSnapshot> task) {
        String ArrayLeido = String.valueOf(task.getResult().getValue());
        String listaCompleta = "";

        //En caso de que se lea y no exista ningun valor
        if (ArrayLeido == "[]")
            listaCompleta = "[" + texto.getIDTexto() + "]";

            //En caso de que ya existiera algun valor asociado a la temática
        else {
            String[] ArrayTematicaTexto = ArrayLeido.split("]");
            //Se crea el array con el nuevo texto
            listaCompleta = ArrayTematicaTexto[0] + "," + texto.getIDTexto() + "]";
        }

        //Acceso a la base de datos para escribir el valor en la tabla de Tematicatexto
        refTematicaTexto.child(texto.getTemática()).setValue(listaCompleta).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
            }
        });
    }

/////////////////////////////////////////////////////////////////////// FIN DE ESCRITURA EN BD /////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////// LECTURA DE UN TEXTO EN BD /////////////////////////////////////////////////////////////////////

    public void leerTexto(){

        //Este hijo es el que se va a usar en firebase para la creacion de textos ya que el contenido cuelga del id
        DatabaseReference refId = refTextos.child(texto.getIDTexto());

        refId.child("Contenido").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        texto.setTexto(String.valueOf(task.getResult().getValue()));
                    }

                }
            }
        });

        refId.child("Titulo").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        texto.setTítulo(String.valueOf(task.getResult().getValue()));
                    }

                }
            }
        });
    }

    public TextoModelo obtenerLecturaBD(){
        return this.texto;
    }

/////////////////////////////////////////////////////////////////////// FIN LECTURA EN BD //////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////FUNCIONES NO NECESARIAS DE LA INTERFAZ DE FIREBASE ///////////////////////////////////////////////////////
    @Override
    public void onSucced(Task<DataSnapshot> task) {

    }


    @Override
    public void onFailure(Exception e) {

    }

}
