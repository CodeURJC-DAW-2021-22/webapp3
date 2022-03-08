package webapp3.webapp3.service;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import webapp3.webapp3.model.*;
import webapp3.webapp3.repository.ActivityRepository;
import webapp3.webapp3.repository.ExerciseRepository;
import webapp3.webapp3.repository.ExerciseTableRepository;
import webapp3.webapp3.repository.UserRepository;

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

    @PostConstruct
    public void init() throws IOException {

        DateType adminBirthday = new DateType("0000","00","00");
        User admin = new User("admin", "admin", "00000000A", "admin@admin.com", passwordEncoder.encode("admin"),"admin", "00000", adminBirthday,
                "123456789", "administrator", 11, 11, "123456789012345678901234", "soy admin");
        setUserImage(admin, "/sample_images/zumba.jpeg");
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

        DateType mon1B = new DateType("1992", "02", "28");
        DateType mon1H = new DateType("2018", "11", "04");
        User monitor1 = new User("Jose Luis", "García Hernández", "11111111A", "joselu@gmail.com", "C/ Alfonso Guerra, 2, 3ºA",
                "12121", "686666888", mon1B, mon1H,  "Me gusta el deporte desde que nací." +
                " Mi objetivo en la vida es zumbar como nadie");
        setUserImage(monitor1, "/sample_images/Monitor1.jpeg");
        monitor1.setACT1(act1);


        DateType mon2B = new DateType("1994", "12", "08");
        DateType mon2H = new DateType("2018", "11", "08");
        User monitor2 = new User("Óscar", "Tesonero Minero", "11111111B", "tesomin@gmail.com", "C/ Los Cantos Rodaos, 7, 1ºD",
                "12123", "711233233", mon2B, mon2H, "No me gusta el deporte, por eso hago yoga." +
                " Busco la paz mental a base del estiramiento de dedo meñique.");
        setUserImage(monitor2, "/sample_images/Monitor2.jpeg");
        monitor2.setACT1(act5);

        DateType mon3B = new DateType("1988", "03", "13");
        DateType mon3H = new DateType("2018", "10", "29");
        User monitor3 = new User("Sofía", "Borrón Hierro", "11111111C", "borronhierro@gmail.com", "C/ Los Azules, 12, Bajo D",
                "12129", "611956456", mon3B, mon3H, "Salta, salta conmigo." +
                " Digo salta, salta conmigo.");
        setUserImage(monitor3, "/sample_images/Monitor3.jpeg");
        monitor3.setACT1(act8);

        DateType mon4B = new DateType("1996", "06", "06");
        DateType mon4H = new DateType("2019", "01", "02");
        User monitor4 = new User("Laura", "García Rodríguez", "11111111D", "lagarroz@gmail.com", "C/ Guerra De La Independencia, 10, 3ºC",
                "12121", "689444555", mon4B, mon4H, "Me gusta montar en bici pero no moverme del sitio." +
                " ¡El spinning es mi pasión! ¡Es como el sofing pero dando pedales!");
        setUserImage(monitor4, "/sample_images/Monitor4.jpg");
        monitor4.setACT1(act4);

        DateType mon5B = new DateType("1991", "01", "07");
        DateType mon5H = new DateType("2018", "11", "09");
        User monitor5 = new User("Martín", "Martínez Martinez", "11111111E", "martinx3@gmail.com", "C/ Los Cantos Rodaos, 4, 2ºA",
                "12123", "792332313", mon5B, mon5H, "Realmente no sé qué es el body pump pero suena chulo." +
                " He oido que se hacen buenas sentadillas profundas.");
        setUserImage(monitor5, "/sample_images/Monitor5.jpeg");
        monitor5.setACT1(act2);

        DateType mon6B = new DateType("1987", "02", "17");
        DateType mon6H = new DateType("2018", "12", "19");
        User monitor6 = new User("Raffaella", "Carra Ca", "11111111F", "carraca@gmail.com", "C/ Las Baldosas Amarillas, 1",
                "12139", "611422422", mon6B, mon6H, "Te miro y te golpeo." +
                " Cuidado conmigo.");
        setUserImage(monitor6, "/sample_images/Monitor6.jpg");
        monitor6.setACT1(act3);

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

        userRep.save(monitor1);
        userRep.save(monitor2);
        userRep.save(monitor3);
        userRep.save(monitor4);
        userRep.save(monitor5);
        userRep.save(monitor6);

        DateType cl1 = new DateType("1987", "02", "02");
        DateType ed1 = new DateType("2021", "02", "08");
        User client1 = new User("Gerard", "Piqué Bernabéu", "22222222A", "odioAFlorentino@gmail.com", "C/ Alabama Dulce, 18",
                "33423", cl1, "724344154", ed1, 185, 85000, "IB12 3456 7890 0101",
                "No tengo ningún problema médico pero cuando veo una camiseta de Mbappé me entra depresión");
        setUserImage(client1, "/sample_images/Client1.jpeg");

        DateType cl2 = new DateType("1985", "02", "05");
        DateType ed2 = new DateType("2020", "07", "14");
        User client2 = new User("Cristiano", "Ronaldo Dos Santos Aveiro", "22222222B", "suuu@gmail.com", passwordEncoder.encode("password"),"C/ G.O.A.T. , 12",
                "33246", cl2, "621144674", "member", 184, 83000, "IB12 9876 1230 1212",
                "Tengo alergia a Messi");

        setUserImage(client2, "/sample_images/Client2.jpeg");

        DateType cl3 = new DateType("2000", "04", "12");
        DateType ed3 = new DateType("2019", "03", "01");
        User client3 = new User("Vinicius", "José Paixão de Oliveira Junior", "22222222C", "fastandfurious@gmail.com", "C/ Brazil de Pelé, 13",
                "33212", cl3, "686686686", ed3, 176, 73, "ES12 1443 7465 8252", "Alergia a meter gol.");
        setUserImage(client3, "/sample_images/Client3.jpeg");

        DateType cl4 = new DateType("2000", "05", "26");
        DateType ed4 = new DateType("2019", "04", "12");
        User client4= new User("Miquel", "Montoro Marquez", "22222222D", "mencanten@gmail.com", "C/ Los Pilotes, 12, 3ºC",
                "41221", cl4, "711771171", ed4, 179, 85, "ES99 9119 9119 9119", "Intolerancia a la lactosa severa");
        setUserImage(client4, "/sample_images/Client4.jpeg");
        client4.setACT1(act5);

        DateType cl5 = new DateType("1986", "05", "02");
        DateType ed5 = new DateType("2019", "04", "13");
        User client5= new User("Aitor", "Tilla Despinacas", "22222222E", "elespinacas@gmail.com", "C/ Popeye, 10, 5ºC",
                "41220", cl5, "716644172", ed5, 166, 50, "ES09 0019 2110 9317", "Intolerancia al brócoli");
        setUserImage(client5, "/sample_images/Client5.jpeg");
        client5.setACT1(act2);
        client5.setACT2(act3);
        client5.setACT3(act1);

        DateType cl6 = new DateType("1976", "09", "13");
        DateType ed6 = new DateType("2019", "04", "22");
        User client6= new User("Aitor", "Menta Irrállos", "22222222F", "tormentas@gmail.com", "C/ Tres Aguas, 12, 3ºC",
                "41210", cl6, "666666666", ed6, 196, 100, "ES10 1010 1010 1010", "Alergia al deporte");
        setUserImage(client6, "/sample_images/Client6.jpeg");
        client5.setACT1(act2);
        client5.setACT2(act5);

        DateType cl7 = new DateType("1986", "05", "02");
        DateType ed7 = new DateType("2019", "04", "13");
        User client7= new User("Nadie", "Tyson Manson", "22222222G", "n0b0dy@gmail.com", "C/ Tres Aguas, 2, 6ºD",
                "41210", cl7, "760000001", ed7, 76, 35, "ES59 5059 5150 5315", "Ninguna alergia conocida.");
        setUserImage(client7, "/sample_images/Client7.jpeg");
        client5.setACT1(act3);
        client5.setACT2(act1);

        Exercise ex1 = new Exercise("Curl de biceps alterno", "Coge una mancuerna en cada mano y dobla el brazo fijo 90 grados.", "Mancuernas.");
        Exercise ex2 = new Exercise("Biceps con barra Z", "Apoyate en el banco y haz 15 repeticiones agarrando la barra Z.", "Barra Z y discos.");
        exeRep.save(ex1);
        exeRep.save(ex2);

        ExerciseTable exerciseTable1 = new ExerciseTable("Tabla 1", "Tabla de entrenamiento de biceps.");
        exerciseTable1.getExercises().add(ex1);
        exerciseTable1.getExercises().add(ex2);

        exerciseTableRep.save(exerciseTable1);

        Exercise ex3 = new Exercise("Extensión de triceps", "Coge una mancuerna en una mano y dobla el brazo fijo 90 grados por detrás de la cabeza." +
                " Estira completamente el brazo sujetando el codo 15 veces.", "Mancuernas.");
        Exercise ex4 = new Exercise("Triceps con barra Z", "Apoya la espalda en el banco y haz 15 repeticiones de extensiones agarrando la barra Z.",
                "Barra Z y discos.");
        exeRep.save(ex3);
        exeRep.save(ex4);
        ExerciseTable exerciseTable2 = new ExerciseTable("Tabla 2", "Tabla de entrenamiento de brazo.");
        exerciseTable2.getExercises().add(ex1);
        exerciseTable2.getExercises().add(ex2);
        exerciseTable2.getExercises().add(ex3);
        exerciseTable2.getExercises().add(ex4);

        exerciseTableRep.save(exerciseTable2);

        client2.addExerciseTable(exerciseTable1);

        exerciseTableRep.save(exerciseTable1);
        userRep.save(client1);
        userRep.save(client2);
        userRep.save(client3);
        userRep.save(client4);
        userRep.save(client5);
        userRep.save(client6);
        userRep.save(client7);
    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setUserImage(User monitor, String classpathResource) throws IOException {

        Resource image = new ClassPathResource(classpathResource);
        monitor.setImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
