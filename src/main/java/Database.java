import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import entity.*;
import java.time.LocalDate;

public class Database {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Racer.class)
                .addAnnotatedClass(Club.class)
                .addAnnotatedClass(Protocol_21_02_2025.class)
                .addAnnotatedClass(Protocol_22_02_2025.class)
                .addAnnotatedClass(RaceResults_21_02_2025.class)
                .addAnnotatedClass(RaceResults_22_02_2025.class)
                .buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            // Create schemas and tables:
            session.createSQLQuery("CREATE DATABASE  IF NOT EXISTS `bmx`;").executeUpdate();
            session.createSQLQuery("USE `bmx`;").executeUpdate();
            session.createSQLQuery("DROP VIEW IF EXISTS `bmx`.`race_result_22_02_2025_view`;").executeUpdate();
            session.createSQLQuery("DROP VIEW IF EXISTS `bmx`.`race_result_21_02_2025_view`;").executeUpdate();
            session.createSQLQuery("DROP VIEW IF EXISTS `bmx`.`protocol_22_02_2025_view`;").executeUpdate();
            session.createSQLQuery("DROP VIEW IF EXISTS `bmx`.`protocol_21_02_2025_view`;").executeUpdate();
            session.createSQLQuery("DROP VIEW IF EXISTS `bmx`.`participants_view`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`race_results_22_02_2025`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`race_results_21_02_2025`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`protocol_22_02_2025`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`protocol_21_02_2025`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`racer`;").executeUpdate();
            session.createSQLQuery("DROP TABLE IF EXISTS `bmx`.`club`;").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`club` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `club_name` nvarchar(60) DEFAULT NULL,\n" +
                    "  `city` nvarchar(30) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY (`club_name`)\n" +
                    ");").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`racer` (\n" +
                    "`id` int NOT NULL AUTO_INCREMENT,\n" +
                    "`last_name` nvarchar(30) NOT NULL,\n" +
                    "`first_name` nvarchar(30) NOT NULL,\n" +
                    "`racer_number` int DEFAULT NULL,\n" +
                    "`birthday` date NOT NULL,\n" +
                    "`sports_category` enum ('WO', 'THIRD_CLASS_JUNIOR_SPORTSMAN', 'SECOND_CLASS_JUNIOR_SPORTSMAN', 'FIRST_CLASS_JUNIOR_SPORTSMAN', 'THIRD_CLASS_SPORTSMAN', 'SECOND_CLASS_SPORTSMAN', 'FIRST_CLASS_SPORTSMAN', 'CANDIDATE_FOR_MASTER_OF_SPORT', 'MASTER_OF_SPORT', 'MASTER_OF_SPORT_INTERNATIONAL_CLASS'),\n" +
                    "`club_id` int DEFAULT NULL,\n" +
                    "`sex` enum ('MALE', 'FEMALE'),\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "FOREIGN KEY (`club_id`) REFERENCES `bmx`.`club` (`id`)\n" +
                    ");").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`protocol_21_02_2025` (\n" +
                    "`id` int NOT NULL AUTO_INCREMENT,\n" +
                    "`run` int NOT NULL,\n" +
                    "`racer_id` int NOT NULL,\n" +
                    "`lane_1` int NOT NULL,\n" +
                    "`lane_2` int NOT NULL,\n" +
                    "`lane_3` int NOT NULL,\n" +
                    "FOREIGN KEY (`racer_id`) REFERENCES `bmx`.`racer` (`id`),\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "UNIQUE (`racer_id`),\n" +
                    "UNIQUE (`run`, `lane_1`),\n" +
                    "UNIQUE (`run`, `lane_2`),\n" +
                    "UNIQUE (`run`, `lane_3`)\n" +
                    ");").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`protocol_22_02_2025` (\n" +
                    "`id` int NOT NULL AUTO_INCREMENT,\n" +
                    "`run` int NOT NULL,\n" +
                    "`racer_id` int NOT NULL,\n" +
                    "`lane_1` int NOT NULL,\n" +
                    "`lane_2` int NOT NULL,\n" +
                    "`lane_3` int NOT NULL,\n" +
                    "FOREIGN KEY (`racer_id`) REFERENCES `bmx`.`racer` (`id`),\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "UNIQUE (`racer_id`),\n" +
                    "UNIQUE (`run`, `lane_1`),\n" +
                    "UNIQUE (`run`, `lane_2`),\n" +
                    "UNIQUE (`run`, `lane_3`)\n" +
                    ");").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`race_results_21_02_2025` (\n" +
                    "`id` int NOT NULL AUTO_INCREMENT,\n" +
                    "`category` enum('MALE_2019', 'MALE_2018', 'MALE_2017', 'FEMALE_2017_OR_YOUNGER'),\n" +
                    "`place` int NOT NULL,\n" +
                    "`racer_id` int NOT NULL,\n" +
                    "FOREIGN KEY (`racer_id`) REFERENCES `bmx`.`racer` (`id`),\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "UNIQUE (`racer_id`),\n" +
                    "UNIQUE (`category`, `place`)\n" +
                    ");").executeUpdate();
            session.createSQLQuery("CREATE TABLE `bmx`.`race_results_22_02_2025` (\n" +
                    "`id` int NOT NULL AUTO_INCREMENT,\n" +
                    "`category` enum('MALE_2019', 'MALE_2018', 'MALE_2017', 'FEMALE_2017_OR_YOUNGER'),\n" +
                    "`place` int NOT NULL,\n" +
                    "`racer_id` int NOT NULL,\n" +
                    "FOREIGN KEY (`racer_id`) REFERENCES `bmx`.`racer` (`id`),\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "UNIQUE (`racer_id`),\n" +
                    "UNIQUE (`category`, `place`)\n" +
                    ");").executeUpdate();

            // Create and map java objects:
            Club club1 = new Club("ГБУ ДО БО СШОР РУСЬ","Брянская обл.");
            Club club2 = new Club("ГБУ ДО РМ \"СШОР по велоспорту\"","Мордовия");
            Club club3 = new Club("СШ \"Победа\"","Московская обл.");
            Club club4 = new Club("МБУ ДО СШ №4 г.Пензы","Пензенская обл.");
            Club club5 = new Club("Спортивная школа гонок/SSR","Удмуртская Республика");

            Racer racer1 = new Racer("Тавынин","Петр", 129, LocalDate.of(2019, 2, 12), SportsCategory.WO, Sex.MALE, club1);
            Racer racer2 = new Racer("Галкин","Михаил",20, LocalDate.of(2019, 4, 29), SportsCategory.WO, Sex.MALE, club2);
            Racer racer3 = new Racer("Неяскин","Даниил",35, LocalDate.of(2019, 3, 8), SportsCategory.WO, Sex.MALE, club2);
            Racer racer4 = new Racer("Колотилов","Марк",493, LocalDate.of(2019, 2, 18), SportsCategory.WO, Sex.MALE, club3);
            Racer racer5 = new Racer("Мкртчян","Артак",11, LocalDate.of(2019, 11, 2), SportsCategory.WO, Sex.MALE, club4);
            Racer racer6 = new Racer("Капралов","Егор",11, LocalDate.of(2019, 8, 22), SportsCategory.WO, Sex.MALE, club5);
            Racer racer7 = new Racer("Касаткин","Роман",12, LocalDate.of(2019, 6, 12), SportsCategory.WO, Sex.MALE, club5);

            session.persist(new Protocol_21_02_2025(1, racer2, 8, 2, 3));
            session.persist(new Protocol_21_02_2025(1, racer6, 7, 6, 1));
            session.persist(new Protocol_21_02_2025(1, racer7, 6, 3, 5));
            session.persist(new Protocol_21_02_2025(1, racer4, 5, 1, 7));
            session.persist(new Protocol_21_02_2025(1, racer5, 4, 8, 2));
            session.persist(new Protocol_21_02_2025(1, racer3, 3, 5, 6));
            session.persist(new Protocol_21_02_2025(1, racer1, 2, 7, 4));

            session.persist(new Protocol_22_02_2025(1, racer7, 8, 2, 3));
            session.persist(new Protocol_22_02_2025(1, racer2, 7, 6, 1));
            session.persist(new Protocol_22_02_2025(1, racer3, 6, 3, 5));
            session.persist(new Protocol_22_02_2025(1, racer1, 5, 1, 7));
            session.persist(new Protocol_22_02_2025(1, racer4, 4, 8, 2));
            session.persist(new Protocol_22_02_2025(1, racer5, 3, 5, 6));
            session.persist(new Protocol_22_02_2025(1, racer6, 2, 7, 4));

            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 1, racer7));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 2, racer2));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 3, racer3));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 4, racer1));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 5, racer4));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 6, racer5));
            session.persist(new RaceResults_21_02_2025(AgeCategory.MALE_2019, 7, racer6));

            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 1, racer7));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 2, racer2));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 3, racer3));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 4, racer5));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 5, racer1));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 6, racer6));
            session.persist(new RaceResults_22_02_2025(AgeCategory.MALE_2019, 7, racer4));

            // Create views:
            session.createSQLQuery("CREATE VIEW `bmx`.`participants_view` AS SELECT \n" +
                    "YEAR(`bmx`.`racer`.`birthday`) AS `year`,\n" +
                    "`bmx`.`racer`.`sex` AS `sex`,\n" +
                    "`bmx`.`racer`.`racer_number` AS `racer_number`,\n" +
                    "`bmx`.`racer`.`birthday` AS `birthday`,\n" +
                    "`bmx`.`racer`.`last_name` AS `last_name`,\n" +
                    "`bmx`.`racer`.`first_name` AS `first_name`,\n" +
                    "`bmx`.`racer`.`sports_category` AS `sports_category`,\n" +
                    "`bmx`.`club`.`club_name` AS `club_name`,\n" +
                    "`bmx`.`club`.`city` AS `city` \n" +
                    "FROM `bmx`.`racer` \n" +
                    "JOIN `bmx`.`club` on(`bmx`.`racer`.`club_id` = `bmx`.`club`.`id`) \n" +
                    "ORDER BY YEAR(`bmx`.`racer`.`birthday`) DESC, `bmx`.`racer`.`sex` DESC, `bmx`.`racer`.`id`")
                    .executeUpdate();
            session.createSQLQuery("CREATE VIEW `bmx`.`protocol_21_02_2025_view` AS SELECT \n" +
                    "YEAR(`bmx`.`racer`.`birthday`) AS year,\n" +
                    "`bmx`.`protocol_21_02_2025`.`run` as run,\n" +
                    "`bmx`.`racer`.`racer_number` AS racer_number,\n" +
                    "`bmx`.`racer`.`last_name` AS last_name,\n" +
                    "`bmx`.`racer`.`first_name` AS first_name,\n" +
                    "`bmx`.`club`.`club_name` AS club_name,\n" +
                    "`bmx`.`club`.`city` AS city,\n" +
                    "`bmx`.`protocol_21_02_2025`.`lane_1` AS lane_1,\n" +
                    "`bmx`.`protocol_21_02_2025`.`lane_2` AS lane_2,\n" +
                    "`bmx`.`protocol_21_02_2025`.`lane_3` AS lane_3 \n" +
                    "FROM `bmx`.`protocol_21_02_2025`  \n" +
                    "JOIN `bmx`.`racer` ON `bmx`.`protocol_21_02_2025`.`racer_id` = `bmx`.`racer`.`id` \n" +
                    "JOIN `bmx`.`club` ON `bmx`.`club`.`id` = `bmx`.`racer`.`club_id` \n" +
                    "ORDER BY `bmx`.`protocol_21_02_2025`.`run` , `bmx`.`racer`.`last_name`, `bmx`.`racer`.`first_name`;")
                    .executeUpdate();
            session.createSQLQuery("CREATE VIEW `bmx`.`protocol_22_02_2025_view` AS SELECT \n" +
                    "YEAR(`bmx`.`racer`.`birthday`) AS year,\n" +
                    "`bmx`.`protocol_22_02_2025`.`run` AS run,\n" +
                    "`bmx`.`racer`.`racer_number` AS racer_number,\n" +
                    "`bmx`.`racer`.`last_name` AS last_name,\n" +
                    "`bmx`.`racer`.`first_name` AS first_name,\n" +
                    "`bmx`.`club`.`club_name` AS club_name,\n" +
                    "`bmx`.`club`.`city` AS city,\n" +
                    "`bmx`.`protocol_22_02_2025`.`lane_1` AS lane_1,\n" +
                    "`bmx`.`protocol_22_02_2025`.`lane_2` AS lane_2,\n" +
                    "`bmx`.`protocol_22_02_2025`.`lane_3` AS lane_3 \n" +
                    "FROM `bmx`.`protocol_22_02_2025` \n" +
                    "JOIN `bmx`.`racer` ON `bmx`.`protocol_22_02_2025`.`racer_id` = `bmx`.`racer`.`id` \n" +
                    "JOIN `bmx`.`club` ON `bmx`.`club`.`id` = `bmx`.`racer`.`club_id` \n" +
                    "ORDER BY `bmx`.`protocol_22_02_2025`.`run`, `bmx`.`racer`.`last_name`, `bmx`.`racer`.`first_name`;")
                    .executeUpdate();
            session.createSQLQuery("CREATE VIEW `bmx`.`race_result_21_02_2025_view` AS SELECT \n" +
                    "`bmx`.`race_results_21_02_2025`.`category` AS category,\n" +
                    "`bmx`.`race_results_21_02_2025`.`place` AS place,\n" +
                    "`bmx`.`racer`.`racer_number` AS racer_number,\n" +
                    "`bmx`.`racer`.`birthday` AS birthday,\n" +
                    "`bmx`.`racer`.`last_name` AS last_name,\n" +
                    "`bmx`.`racer`.`first_name` AS first_name,\n" +
                    "`bmx`.`racer`.`sports_category` AS sports_category,\n" +
                    "`bmx`.`club`.`city` as city,\n" +
                    "`bmx`.`club`.`club_name` as club_name \n" +
                    "FROM `bmx`.`race_results_21_02_2025` \n" +
                    "JOIN `bmx`.`racer` ON `bmx`.`race_results_21_02_2025`.`racer_id` = `bmx`.`racer`.`id` \n" +
                    "JOIN `bmx`.`club` ON `bmx`.`club`.`id` = `bmx`.`racer`.`club_id` \n" +
                    "ORDER BY `bmx`.`race_results_21_02_2025`.`category` , `bmx`.`race_results_21_02_2025`.`place`;")
                    .executeUpdate();
            session.createSQLQuery("CREATE VIEW `bmx`.`race_result_22_02_2025_view` AS SELECT \n" +
                    "`bmx`.`race_results_22_02_2025`.`category` AS category,\n" +
                    "`bmx`.`race_results_22_02_2025`.`place` AS place,\n" +
                    "`bmx`.`racer`.`racer_number` AS racer_number,\n" +
                    "`bmx`.`racer`.`birthday` AS birthday,\n" +
                    "`bmx`.`racer`.`last_name` AS last_name,\n" +
                    "`bmx`.`racer`.`first_name` AS first_name,\n" +
                    "`bmx`.`racer`.`sports_category` AS sports_category,\n" +
                    "`bmx`.`club`.`city` as city,\n" +
                    "`bmx`.`club`.`club_name` as club_name \n" +
                    "FROM `bmx`.`race_results_22_02_2025` \n" +
                    "JOIN `bmx`.`racer` ON `bmx`.`race_results_22_02_2025`.`racer_id` = `bmx`.`racer`.`id` \n" +
                    "JOIN `bmx`.`club` ON `bmx`.`club`.`id` = `bmx`.`racer`.`club_id` \n" +
                    "ORDER BY `bmx`.`race_results_22_02_2025`.`category` , `bmx`.`race_results_22_02_2025`.`place`;")
                    .executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
