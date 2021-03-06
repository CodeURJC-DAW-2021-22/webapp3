package webapp3.webapp3.service;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import webapp3.webapp3.model.*;
import webapp3.webapp3.repository.*;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
public class DataSampleService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ActivityRepository actRep;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private ExerciseRepository exeRep;

    @Autowired
    private ExerciseTableRepository exerciseTableRep;

    @Autowired
    private UserExerciseTableRepository userExerciseTableRepository;

    @PostConstruct
    public void init() throws IOException {

        if (userRep.count() > 0) {
            return;
        }

        DateType adminBirthday = new DateType("2018","02","10");
        User admin = new User("admin", "admin", "00000000A", "admin@admin.com", "Gym Street, 1", "12300", adminBirthday,
                "123456789", passwordEncoder.encode("admin"));
        setUserImage(admin, "/sample_images/admin.jpeg");
        userRep.save(admin);

        Activity act1 = new Activity("Zumba", 2200, "Zumba es un programa de ejercicios que combina música latina e internacional con movimientos de baile." +
                " Las rutinas de Zumba incorporan el entrenamiento por intervalos en el que se alternan ritmos rápidos y lentos para ayudar a mejorar el estado cardiovascular.",
                "Sala Actividades 1", 12, "12:00", "11:00", "10:00", "9:00", "10:00");
        setActivityImage(act1, "/sample_images/zumba.jpeg");

        Activity act2 = new Activity("Body Pump", 1900, "El Body Pump es un programa de entrenamiento físico intenso que combina actividad aeróbica y trabajo" +
                " muscular mediante el levantamiento de pesas al ritmo de la música. Son sesiones dirigidas, divertidas y motivadoras, en las que se fortalece el sistema cardiovascular" +
                " y la gran mayoría de los músculos del cuerpo.", "Sala Baile", 23, "12:00", "11:00", "10:00",
                "9:00", "10:00");
        setActivityImage(act2, "/sample_images/body_pump.jpeg");

        Activity act3 = new Activity("Body Combat", 1200, "BodyCombat es un programa de entrenamiento grupal de tipo cardiovascular que, desde el año 1999, es" +
                " distribuido por Les Mills International. Consiste en realizar movimientos basados en distintas artes marciales siguiendo el ritmo de una base musical.",
                "Sala de Artes Marciales", 18, "12:00", "11:00", "10:00",
                "9:00", "10:00");
        setActivityImage(act3, "/sample_images/body_combat.jpeg");

        Activity act4 = new Activity("Spinning", 400, "Es un ejercicio aeróbico y cardiovascular que se realiza sobre una bicicleta estática en el que se trabaja" +
                " el tren inferior: las piernas y los glúteos. Su finalidad principal es perder peso y la tonificación de los músculos, además de mejorar la fuerza y la resistencia.",
                "Sala Bicicletas", 11, "12:00", "11:00", "10:00",
                "9:00", "10:00");
        setActivityImage(act4, "/sample_images/spinning.jpg");

        Activity act5 = new Activity("Yoga", 3500, "Posturitas incómodas.", "Sala 1", 12, "12:00", "11:00", "10:00",
                "9:00", "10:00");
        setActivityImage(act5, "/sample_images/yoga.jpeg");

        Activity act6 = new Activity("Body Balance", 2500, "Si lo que buscáis es fortalecer músculos y relajaros del estrés de la vida diaria, esta es una de " +
                "las actividades dirigidas que os permitirán conseguirlo. Se trata de un mix de técnicas del yoga, el pilates y el Tai Chi con las que relajar el cuerpo y la mente.\n" +
                "Los movimientos no suelen ser complicados y las clases tienen aforo limitado, lo que permite tener suficiente espacio para moverse. ¿Te animas a probarlo?",
                "Sala de Actividades 1", 12, "17:00", "17:00", "17:00", "17:00", "17:00");
        setActivityImage(act6, "/sample_images/body_balance.jpeg");

        Activity act7 = new Activity("Pilates", 800, "Aunque el pilates parezca una clase relajada, para nada lo es. Los niveles van subiendo poco a poco, según comienzas a realizar los " +
                "ejercicios correctamente. Se trabajan los músculos más internos del cuerpo y este se moldea con el paso del tiempo. ¡No lo subestimes!", "Sala de Actividades 2",
                25, "18:00", "18:00", "18:00", "18:00", "18:00");
        setActivityImage(act7, "/sample_images/pilates.jpeg");

        Activity act8 = new Activity("Body Jump", 1000, "Si quieres pasar un buen rato con tus amigos en una de nuestras actividades dirigidas a la vez que hacéis ejercicios y mejoráis" +
                " vuestra salud y vuestro físico, el body jump es un ejercicio perfecto para ello. Se realiza en una cama elástica sobre la que tendrás que saltar mientras haces movimientos " +
                "que te marca el monitor. ¡Os encantará!", "Sala de Artes Marciales", 18, "21:00", "21:00", "21:00", "21:00", "21:00");
        setActivityImage(act8, "/sample_images/body_jump.jpeg");

        Activity act9 = new Activity("Kick Boxing", 1000, "Esta modalidad de las artes marciales te hará viajar a la japón profunda. Un combae basado em patadas y puñetazos simepre desde la perspectiva de la auto" +
                "defensa. ¡Apuntate ya!", "Sala de Artes Marciales",12,"19:00","19:00","19:00","19:00","19:00");
        setActivityImage(act9,"/sample_images/kickboxing.jpg");

        Activity act10= new Activity("Mantente On",2000,"¿Qué es Mantete On? Mantente On es un programa de entrenamiento dirigido a personas mayores " +
                "con la intencion de mejorar el bienestar de usuario, mejorando su estado fisico ", "Sala de Actividades 1",20,"16:00","16:00","16:00","16:00","16:00");
        setActivityImage(act10,"/sample_images/MantenteOn.jpg");


        Activity act11= new Activity("Aqua Mamis", 1000, "Aqua Mamis es ejercicio acuático adecuado al embarazo, técnicas de respiración y relajación, además de presentar distintos temas sobre el parto." +
                " Utilizamos una piscina climatizada (34 grados) y el curso es siempre dirigido por una matrona con experiencia.","Piscina Climatizada", 10,"11:00","11:00","11:00","11:00","11:00");
        setActivityImage(act11,"/sample_images/aquamamis.jpg");


        DateType mon1B = new DateType("1992", "02", "28");
        DateType mon1H = new DateType("2018", "11", "04");
        User monitor1 = new User("Jose Luis", "García Hernández", "11111111A", "joselu@gmail.com", "C/ Alfonso Guerra, 2, 3ºA",
                "12121", "686666888", mon1B, mon1H,  "Me gusta el deporte desde que nací." +
                " Mi objetivo en la vida es zumbar como nadie", passwordEncoder.encode("monitor"));
        setUserImage(monitor1, "/sample_images/Monitor1.jpeg");
        monitor1.setACT1(act1);

        DateType mon2B = new DateType("1994", "12", "08");
        DateType mon2H = new DateType("2018", "11", "08");
        User monitor2 = new User("Óscar", "Tesonero Minero", "11111111B", "tesomin@gmail.com", "C/ Los Cantos Rodaos, 7, 1ºD",
                "12123", "711233233", mon2B, mon2H, "No me gusta el deporte, por eso hago yoga." +
                " Busco la paz mental a base del estiramiento de dedo meñique.", passwordEncoder.encode("monitor"));
        setUserImage(monitor2, "/sample_images/Monitor2.jpeg");
        monitor2.setACT1(act5);

        DateType mon3B = new DateType("1988", "03", "13");
        DateType mon3H = new DateType("2018", "10", "29");
        User monitor3 = new User("Sofía", "Borrón Hierro", "11111111C", "borronhierro@gmail.com", "C/ Los Azules, 12, Bajo D",
                "12129", "611956456", mon3B, mon3H, "Salta, salta conmigo." +
                " Digo salta, salta conmigo.", passwordEncoder.encode("monitor"));
        setUserImage(monitor3, "/sample_images/Monitor3.jpeg");
        monitor3.setACT1(act8);

        DateType mon4B = new DateType("1996", "06", "06");
        DateType mon4H = new DateType("2019", "01", "02");
        User monitor4 = new User("Laura", "García Rodríguez", "11111111D", "lagarroz@gmail.com", "C/ Guerra De La Independencia, 10, 3ºC",
                "12121", "689444555", mon4B, mon4H, "Me gusta montar en bici pero no moverme del sitio." +
                " ¡El spinning es mi pasión! ¡Es como el sofing pero dando pedales!", passwordEncoder.encode("monitor"));
        setUserImage(monitor4, "/sample_images/Monitor4.jpg");
        monitor4.setACT1(act4);

        DateType mon5B = new DateType("1991", "01", "07");
        DateType mon5H = new DateType("2018", "11", "09");
        User monitor5 = new User("Martín", "Martínez Martinez", "11111111E", "martinx3@gmail.com", "C/ Los Cantos Rodaos, 4, 2ºA",
                "12123", "792332313", mon5B, mon5H, "Realmente no sé qué es el body pump pero suena chulo." +
                " He oido que se hacen buenas sentadillas profundas.", passwordEncoder.encode("monitor"));
        setUserImage(monitor5, "/sample_images/Monitor5.jpeg");
        monitor5.setACT1(act2);

        DateType mon6B = new DateType("1987", "02", "17");
        DateType mon6H = new DateType("2018", "12", "19");
        User monitor6 = new User("Raffaella", "Carra Ca", "11111111F", "carraca@gmail.com", "C/ Las Baldosas Amarillas, 1",
                "12139", "611422422", mon6B, mon6H, "Te miro y te golpeo." +
                " Cuidado conmigo.", passwordEncoder.encode("monitor"));
        setUserImage(monitor6, "/sample_images/Monitor6.jpg");
        monitor6.setACT1(act3);

        Exercise exercise1 = new Exercise("Press De Banca Con Barra","Ejercicio de pectoral medio","banco, barra y discos");
        setExcerciseImage(exercise1,"/sample_images/pressBanca.png");

        exeRep.save(exercise1);

        Exercise exercise2 = new Exercise("Press Militar","Ejercicio de hombro ","Barra y discos");
        setExcerciseImage(exercise2,"/sample_images/pressMilitar.jpg");

        exeRep.save(exercise2);

        Exercise exercise3 = new Exercise("Sentadilla con barra","Ejercicio para cuadriceps y gluteos"," barra y discos");
        setExcerciseImage(exercise3,"/sample_images/sentadilla.jpg");

        exeRep.save(exercise3);

        Exercise exercise4 = new Exercise("Peso Muerto","Ejercicio de espalda media y baja","barra y discos");
        setExcerciseImage(exercise4,"/sample_images/peso-muerto-sumo-6244.jpeg");

        exeRep.save(exercise4);

        Exercise exercise5 = new Exercise("Cruces con poleas","Ejercicio de pectoral alto o bajo dependiendo del agarre","dos poleas altas o bajas");
        setExcerciseImage(exercise5,"/sample_images/crucesPoleas.jpg");

        exeRep.save(exercise5);

        Exercise exercise6 = new Exercise("Curl de biceps con barra Z","Ejercicio de biceps","barra Z y discos");
        setExcerciseImage(exercise6,"/sample_images/curlZ.png");

        exeRep.save(exercise6);

        Exercise exercise7 = new Exercise("Curl femoral tumbado","Ejercicio de femoral","maquina femoral");
        setExcerciseImage(exercise7,"/sample_images/curl-femoral-con-maquina-2790.png");

        exeRep.save(exercise7);

        Exercise exercise8 = new Exercise("Puente de gluteos","Ejercicio de gluteos","banco, barra y discos");
        setExcerciseImage(exercise8,"/sample_images/Hip-thrust-740x418.jpg");

        exeRep.save(exercise8);

        Exercise exercise9 = new Exercise("Fondos en paralelas","Ejercicio de pectoral bajo","paralelas");
        setExcerciseImage(exercise9,"/sample_images/fondosParalelas.jpg");

        exeRep.save(exercise9);

        Exercise exercise10 = new Exercise("Elevaciones laterales","Ejercicio de deltoides medio","macuernas");
        setExcerciseImage(exercise10,"/sample_images/elevacionesLaterales.jpg");

        exeRep.save(exercise10);

        Exercise exercise11 = new Exercise("Elevaciones frontales","Ejercicio de deltoides frontal","mancuernas");
        setExcerciseImage(exercise11,"/sample_images/elevacionesFrontales.jpg");

        exeRep.save(exercise11);

        Exercise exercise12 = new Exercise("Dominadas","Ejercicio de espalda media","barra alta");
        setExcerciseImage(exercise12,"/sample_images/dominadas.jpg");

        exeRep.save(exercise12);

        Exercise exercise13 = new Exercise("Jalon al pecho","Ejercicio de espalda media","poleas y banco");
        setExcerciseImage(exercise13,"/sample_images/jalonPecho.jpg");

        exeRep.save(exercise13);

        Exercise exercise14 = new Exercise("Curl martillo","Ejercicio de biceps externo","mancuernas");
        setExcerciseImage(exercise14,"/sample_images/culrMartillo.jpg");

        exeRep.save(exercise14);

        Exercise exercise15 = new Exercise("Press de banca con agarre semi-cerrado","Ejercicio de triceps","banco, barra y discos");
        setExcerciseImage(exercise15,"/sample_images/Press-de-banca-con-agarre-cerrado.jpg");

        exeRep.save(exercise15);

        Exercise exercise16 = new Exercise("Fondos en paralelas","Ejercicio de triceps","paralelas");
        setExcerciseImage(exercise16,"/sample_images/fondosParalelas.jpg");

        exeRep.save(exercise16);

        Exercise exercise17 = new Exercise("Extensiones con barra tumbado","Ejercicio de triceps","banco, barra Z y discos");
        setExcerciseImage(exercise17,"/sample_images/");

        exeRep.save(exercise17);

        Exercise exercise18 = new Exercise("Elevacion de gemelos sentado","Ejercicio de gemelos","banco, barra y discos");
        setExcerciseImage(exercise18,"/sample_images/gemelosSentado.jpg");

        exeRep.save(exercise18);

        Exercise exercise19 = new Exercise("Encogimientos en polea","Ejercicio de abdominal","polea alta");
        setExcerciseImage(exercise19,"/sample_images/encogimientos.jpg");

        exeRep.save(exercise19);

        Exercise exercise20 = new Exercise("Zancada con mancuernas","Ejercicio de femoral ","mancuernas");
        setExcerciseImage(exercise20,"/sample_images/zancada.jpg");

        exeRep.save(exercise20);

        Exercise exercise21 = new Exercise("Aperturas en banco","Ejercicio de extension de pectoral","banco y mancuernas");
        setExcerciseImage(exercise21,"/sample_images/aperturas.jpg");

        exeRep.save(exercise21);

        Exercise exercise22 = new Exercise("Flexiones","Ejercicio de pectoral ","ninguno");
        setExcerciseImage(exercise22,"/sample_images/flexiones.jpg");

        exeRep.save(exercise22);

        Exercise exercise23 = new Exercise("Oblicuos con poleas","Ejercicio de oblicuos","polea alta");
        setExcerciseImage(exercise23,"/sample_images/oblicuos.jpg");

        exeRep.save(exercise23);

        Exercise exercise24 = new Exercise("Curl en banco inclinado","Ejercicio de biceps","banco y mancuernas");
        setExcerciseImage(exercise24,"/sample_images/curl-biceps-alterno-con-mancuernas-banco-inclinado-6211.png");

        exeRep.save(exercise24);

        Exercise exercise25 = new Exercise("Curl de muñeca","Ejercicio de antebrazo","barra y discos");
        setExcerciseImage(exercise25, "/sample_images/wrist.jpg");

        exeRep.save(exercise25);

        Exercise exercise26 = new Exercise("Press Arnorld","Ejercicio de deltoides","banco y mancuernas");
        setExcerciseImage(exercise26,"/sample_images/arnold.jpg");

        exeRep.save(exercise26);

        Exercise exercise27 = new Exercise("Curl en polea","Ejercicio de biceps","polea baja");
        setExcerciseImage(exercise27,"/sample_images/curlPolea.jpg");

        exeRep.save(exercise27);

        Exercise exercise28 = new Exercise("Jalon de triceps","Ejercicio de triceps","polea alta");
        setExcerciseImage(exercise28,"/sample_images/jalonTriceps.jpg");

        exeRep.save(exercise28);

        Exercise exercise29 = new Exercise("Prensa","Ejercicio de cuadriceps y gluteo","prensa");
        setExcerciseImage(exercise29,"/sample_images/prensa.jpg");

        exeRep.save(exercise29);

        Exercise exercise30 = new Exercise("Encogimientos","Ejercicio de trapecio","mancuernas");
        setExcerciseImage(exercise30,"/sample_images/encogimientosTrap.jpg");

        exeRep.save(exercise30);

        act1.setMonitorName(monitor1.getName());
        act2.setMonitorName(monitor5.getName());
        act3.setMonitorName(monitor6.getName());
        act4.setMonitorName(monitor4.getName());
        act5.setMonitorName(monitor2.getName());
        act8.setMonitorName(monitor3.getName());

        actRep.save(act1);
        actRep.save(act2);
        actRep.save(act3);
        actRep.save(act4);
        actRep.save(act5);
        actRep.save(act6);
        actRep.save(act7);
        actRep.save(act8);
        actRep.save(act9);
        actRep.save(act10);
        actRep.save(act11);


        userRep.save(monitor1);
        userRep.save(monitor2);
        userRep.save(monitor3);
        userRep.save(monitor4);
        userRep.save(monitor5);
        userRep.save(monitor6);

        DateType cl1 = new DateType("1987", "02", "02");
        DateType ed1 = new DateType("2021", "02", "08");
        User client1 = new User("Gerard", "Piqué Bernabéu", "22222222A", "odioAFlorentino@gmail.com","C/ Alabama Dulce, 18",
                "33423", cl1, "724344154", ed1, 185, 85000,
                "No tengo ningún problema médico pero cuando veo una camiseta de Mbappé me entra depresión",  passwordEncoder.encode("password"));
        setUserImage(client1, "/sample_images/Client1.jpeg");

        DateType cl2 = new DateType("1985", "02", "05");
        DateType ed2 = new DateType("2020", "07", "14");
        User client2 = new User("Cristiano", "Ronaldo Dos Santos Aveiro", "22222222B", "suuu@gmail.com","C/ G.O.A.T. , 12",
                "33246", cl2, "621144674", ed2 , 184, 83000,
                "Tengo alergia a Messi", passwordEncoder.encode("password"));

        setUserImage(client2, "/sample_images/Client2.jpeg");

        DateType cl3 = new DateType("2000", "04", "12");
        DateType ed3 = new DateType("2019", "03", "01");
        User client3 = new User("Vinicius", "José Paixão de Oliveira Junior", "22222222C", "fastandfurious@gmail.com", "C/ Brazil de Pelé, 13",
                "33212", cl3, "686686686", ed3, 176, 73,  "Alergia a meter gol.", passwordEncoder.encode("password"));
        setUserImage(client3, "/sample_images/Client3.jpeg");

        DateType cl4 = new DateType("2000", "05", "26");
        DateType ed4 = new DateType("2019", "04", "12");
        User client4= new User("Miquel", "Montoro Marquez", "22222222D", "mencanten@gmail.com", "C/ Los Pilotes, 12, 3ºC",
                "41221", cl4, "711771171", ed4, 179, 85, "Intolerancia a la lactosa severa", passwordEncoder.encode("password"));
        setUserImage(client4, "/sample_images/Client4.jpeg");
        client4.setACT1(act5);

        DateType cl5 = new DateType("1986", "05", "02");
        DateType ed5 = new DateType("2019", "04", "13");
        User client5= new User("Aitor", "Tilla Despinacas", "22222222E", "elespinacas@gmail.com", "C/ Popeye, 10, 5ºC",
                "41220", cl5, "716644172", ed5, 166, 50,  "Intolerancia al brócoli", passwordEncoder.encode("password"));
        setUserImage(client5, "/sample_images/Client5.jpeg");
        client5.setACT1(act2);
        client5.setACT2(act3);
        client5.setACT3(act1);

        DateType cl6 = new DateType("1976", "09", "13");
        DateType ed6 = new DateType("2019", "04", "22");
        User client6= new User("Aitor", "Menta Irrállos", "22222222F", "tormentas@gmail.com", "C/ Tres Aguas, 12, 3ºC",
                "41210", cl6, "666666666", ed6, 196, 100,  "Alergia al deporte", passwordEncoder.encode("password"));
        setUserImage(client6, "/sample_images/Client6.jpeg");
        client6.setACT1(act2);
        client6.setACT2(act5);

        DateType cl7 = new DateType("1986", "05", "02");
        DateType ed7 = new DateType("2019", "04", "13");
        User client7= new User("Nadie", "Tyson Manson", "22222222G", "n0b0dy@gmail.com", "C/ Tres Aguas, 2, 6ºD",
                "41210", cl7, "760000001", ed7, 76, 35,  "Ninguna alergia conocida.", passwordEncoder.encode("password"));
        setUserImage(client7, "/sample_images/Client7.jpeg");
        client7.setACT1(act3);
        client7.setACT2(act1);

        ExerciseTable exerciseTable1 = new ExerciseTable("Tabla 1", "Tabla de entrenamiento de biceps.");
        exerciseTable1.getExercises().add(exercise1);
        exerciseTable1.getExercises().add(exercise2);

        setExerciseTableImage(exerciseTable1, "/sample_images/biceps.jpeg");
        exerciseTableRep.save(exerciseTable1);

        ExerciseTable exerciseTable2 = new ExerciseTable("Tabla 2", "Tabla de entrenamiento de brazo.");
        exerciseTable2.getExercises().add(exercise1);
        exerciseTable2.getExercises().add(exercise2);
        exerciseTable2.getExercises().add(exercise3);
        exerciseTable2.getExercises().add(exercise4);

        setExerciseTableImage(exerciseTable2, "/sample_images/brazo-1.jpeg");
        exerciseTableRep.save(exerciseTable2);

        ExerciseTable exerciseTable3 = new ExerciseTable("Tabla 3", "Tabla de entrenamiento mixta.");
        exerciseTable3.getExercises().add(exercise1);
        exerciseTable3.getExercises().add(exercise2);

        setExerciseTableImage(exerciseTable3, "/sample_images/mixta-1.jpeg");
        exerciseTableRep.save(exerciseTable3);

        ExerciseTable exerciseTable4 = new ExerciseTable("Tabla 4", "Tabla de entrenamiento abdominal y lumbar");

        setExerciseTableImage(exerciseTable4, "/sample_images/abdominal-1.jpeg");
        exerciseTableRep.save(exerciseTable4);

        ExerciseTable exerciseTable5 = new ExerciseTable("Tabla 5", "Tabla de entrenamiento tonificar");
        exerciseTable5.getExercises().add(exercise3);
        exerciseTable5.getExercises().add(exercise4);

        setExerciseTableImage(exerciseTable5, "/sample_images/tonificar1.jpeg");
        exerciseTableRep.save(exerciseTable5);

        ExerciseTable exerciseTable6 = new ExerciseTable("Tabla 6", "Tabla de entrenamiento de espalda.");
        exerciseTable6.getExercises().add(exercise13);
        exerciseTable6.getExercises().add(exercise12);
        exerciseTable6.getExercises().add(exercise4);

        setExerciseTableImage(exerciseTable6, "/sample_images/espalda1.jpeg");
        exerciseTableRep.save(exerciseTable6);

        ExerciseTable exerciseTable7 = new ExerciseTable("Tabla 7", "Tabla de entrenamiento de espalda y triceps.");
        exerciseTable7.getExercises().add(exercise13);
        exerciseTable7.getExercises().add(exercise12);
        exerciseTable7.getExercises().add(exercise4);
        exerciseTable7.getExercises().add(exercise15);
        exerciseTable7.getExercises().add(exercise16);
        exerciseTable7.getExercises().add(exercise17);

        setExerciseTableImage(exerciseTable7, "/sample_images/triceps-espalda1.png");
        exerciseTableRep.save(exerciseTable7);

        ExerciseTable exerciseTable8 = new ExerciseTable("Tabla 8", "Tabla de entrenamiento de triceps.");
        exerciseTable8.getExercises().add(exercise15);
        exerciseTable8.getExercises().add(exercise16);
        exerciseTable8.getExercises().add(exercise17);
        exerciseTable8.getExercises().add(exercise28);

        setExerciseTableImage(exerciseTable8, "/sample_images/triceps1.png");
        exerciseTableRep.save(exerciseTable8);

        ExerciseTable exerciseTable9 = new ExerciseTable("Tabla 9", "Tabla de entrenamiento de espalda y biceps.");
        exerciseTable9.getExercises().add(exercise6);
        exerciseTable9.getExercises().add(exercise14);
        exerciseTable9.getExercises().add(exercise24);
        exerciseTable9.getExercises().add(exercise4);
        exerciseTable9.getExercises().add(exercise12);
        exerciseTable9.getExercises().add(exercise13);

        setExerciseTableImage(exerciseTable9, "/sample_images/biceps-espalda1.jpeg");
        exerciseTableRep.save(exerciseTable9);

        ExerciseTable exerciseTable10 = new ExerciseTable("Tabla 10", "Tabla de entrenamiento de biceps (parte 2).");
        exerciseTable10.getExercises().add(exercise6);
        exerciseTable9.getExercises().add(exercise14);
        exerciseTable9.getExercises().add(exercise24);

        setExerciseTableImage(exerciseTable10, "/sample_images/biceps-Parte2.jpg");
        exerciseTableRep.save(exerciseTable10);

        ExerciseTable exerciseTable11 = new ExerciseTable("Tabla 11", "Tabla de entrenamiento de biceps y triceps.");
        exerciseTable11.getExercises().add(exercise6);
        exerciseTable11.getExercises().add(exercise14);
        exerciseTable11.getExercises().add(exercise24);
        exerciseTable11.getExercises().add(exercise15);
        exerciseTable11.getExercises().add(exercise16);
        exerciseTable11.getExercises().add(exercise17);

        setExerciseTableImage(exerciseTable11, "/sample_images/biceps-triceps1.jpeg");
        exerciseTableRep.save(exerciseTable11);


        ExerciseTable exerciseTable12 = new ExerciseTable("Tabla 12", "Tabla de entrenamiento de deltoides.");
        exerciseTable12.getExercises().add(exercise2);
        exerciseTable12.getExercises().add(exercise10);
        exerciseTable12.getExercises().add(exercise11);

        setExerciseTableImage(exerciseTable12, "/sample_images/deltoide1.png");
        exerciseTableRep.save(exerciseTable12);

        ExerciseTable exerciseTable13 = new ExerciseTable("Tabla 13", "Tabla de entrenamiento de biceps (parte 3).");
        exerciseTable13.getExercises().add(exercise1);
        exerciseTable13.getExercises().add(exercise5);
        exerciseTable13.getExercises().add(exercise9);
        exerciseTable13.getExercises().add(exercise21);

        setExerciseTableImage(exerciseTable13, "/sample_images/biceps-Parte3.jpeg");
        exerciseTableRep.save(exerciseTable13);

        ExerciseTable exerciseTable14 = new ExerciseTable("Tabla 14", "Tabla de entrenamiento de pecho y biceps.");
        exerciseTable14.getExercises().add(exercise1);
        exerciseTable14.getExercises().add(exercise5);
        exerciseTable14.getExercises().add(exercise9);
        exerciseTable14.getExercises().add(exercise21);
        exerciseTable14.getExercises().add(exercise6);
        exerciseTable14.getExercises().add(exercise14);

        setExerciseTableImage(exerciseTable14, "/sample_images/pectoral1.png");
        exerciseTableRep.save(exerciseTable14);

        ExerciseTable exerciseTable15 = new ExerciseTable("Tabla 15", "Tabla de entrenamiento de pierna.");
        exerciseTable15.getExercises().add(exercise3);
        exerciseTable15.getExercises().add(exercise29);
        exerciseTable15.getExercises().add(exercise7);
        exerciseTable15.getExercises().add(exercise20);

        setExerciseTableImage(exerciseTable15, "/sample_images/pierna1.jpeg");
        exerciseTableRep.save(exerciseTable15);

        userRep.save(client1);
        userRep.save(client2);
        userRep.save(client3);
        userRep.save(client4);
        userRep.save(client5);
        userRep.save(client6);
        userRep.save(client7);

        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable1));
        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable2));
        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable1));
        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable4));
        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable1));
        userExerciseTableRepository.save(new UserExerciseTable(client2, exerciseTable4));

        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable15));
        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable2));
        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable15));
        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable3));
        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable1));
        userExerciseTableRepository.save(new UserExerciseTable(client1, exerciseTable7));

    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setUserImage(User monitor, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        monitor.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setExcerciseImage(Exercise exercise, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        exercise.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setExerciseTableImage(ExerciseTable exerciseTab, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        exerciseTab.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
