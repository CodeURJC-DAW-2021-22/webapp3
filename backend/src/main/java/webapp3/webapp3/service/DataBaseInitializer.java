package webapp3.webapp3.service;

public class DataBaseInitializer {

    @Autowired
    private ActivityRepository actRep;

    @Autowired
    private MonitorRepository monRep;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {

        Activity act1 = new Activity("Zumba","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act1, "/sample_images/gallery-8.jpg");
        actRep.save(act1);

        Activity act2 = new Activity("BodyPump","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act2, "/sample_images/gallery-3.jpg");
        actRep.save(act2);

        Activity act3 = new Activity("BodyCombat","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act3, "/sample_images/gallery-2.jpg");
        actRep.save(act3);

        Activity act4 = new Activity("Spinning","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act4, "/sample_images/gallery-4.jpg");
        actRep.save(act4);

        Activity act5 = new Activity("Yoga","21.00", "Posturitas", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act5, "/sample_images/gallery-5.jpg");
        actRep.save(act5);


        DateType d = new DateType("1993", "12", "03");

        Monitor mon1 = new Monitor("Jose Luis","Martinez Munuera",
                "11111111A", "mm@gmail.com","PRUEBA", "PRUEBA", "21131",
                "43534534", d, false, d, "Spinning", "Larga trayectoria profesional en el mundo fitness.");
        setMonitorImage(mon1, "/static/img/team/team-1.jpg");
        monRep.save(mon1);

        Monitor mon2 = new Monitor("Jose David","Fernandez Dgank",
                "11111111B", "am@gmail.com","PRUEBA", "PRUEBA", "21344",
                "43532344", d, false,d, "Yoga", "Yogui como el oso.");
        setMonitorImage(mon2, "/static/img/team/team-2.jpg");
        monRep.save(mon2);

        Monitor mon3 = new Monitor("Maria Luisa","Rodriguez Carajote",
                "11111111C", "rc@gmail.com","PRUEBA", "PRUEBA", "74553",
                "423489789", d, false,d, "Body Pump", "Te miro y te golpeo.");
        setMonitorImage(mon3, "/static/img/team/team-3.jpg");
        monRep.save(mon3);
    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {
        activity.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setMonitorImage(Monitor monitor, String classpathResource) throws IOException {
        monitor.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        monitor.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
