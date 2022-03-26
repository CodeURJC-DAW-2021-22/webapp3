package webapp3.webapp3;

@SpringBootConfiguration
public class DatabaseWaitConfigurator {
    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        DatabaseStartupValidator dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
// Configuration delay and timeout
        dsv.setInterval(5);
        dsv.setTimeout(120);
        return dsv;
    }
    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf ->
        {
// Let beans that need the database depend on the DatabaseStartupValidator
// like the JPA EntityManagerFactory
            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("databaseStartupValidator"));
        };
    }
}