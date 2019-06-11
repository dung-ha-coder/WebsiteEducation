-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: website_education
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer` (
  `id_answer` int(11) NOT NULL AUTO_INCREMENT,
  `id_subject` int(11) DEFAULT NULL,
  `id_exam` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_answer`),
  KEY `fk_asdfdfsdfas_idx` (`id_subject`),
  CONSTRAINT `fk_asdfdfsdfas` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,2,3),(2,2,3),(3,2,3),(4,2,3),(5,2,1),(6,2,2),(7,2,4),(8,2,4),(9,2,4),(10,2,4),(11,2,4),(12,1,2),(13,1,2),(14,1,3),(15,1,3),(16,1,4),(17,1,4),(18,1,4);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_coursesgoal`
--

DROP TABLE IF EXISTS `answer_coursesgoal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer_coursesgoal` (
  `id_answer` int(11) NOT NULL,
  `id_coursesgoal` int(11) NOT NULL,
  PRIMARY KEY (`id_answer`,`id_coursesgoal`),
  KEY `fk_asfwerwerdsfs` (`id_coursesgoal`),
  CONSTRAINT `fk_asdfasdfsadf` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id_answer`),
  CONSTRAINT `fk_asfwerwerdsfs` FOREIGN KEY (`id_coursesgoal`) REFERENCES `coursegoal` (`id_course_goal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_coursesgoal`
--

LOCK TABLES `answer_coursesgoal` WRITE;
/*!40000 ALTER TABLE `answer_coursesgoal` DISABLE KEYS */;
INSERT INTO `answer_coursesgoal` VALUES (1,1),(3,1),(5,1),(7,1),(1,2),(3,2),(8,2),(2,3),(3,3),(9,3),(3,4),(10,4),(4,5),(6,5),(11,5),(12,14),(13,14),(14,14),(14,15),(15,15),(16,16),(17,17),(18,18),(18,19),(4,20);
/*!40000 ALTER TABLE `answer_coursesgoal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursegoal`
--

DROP TABLE IF EXISTS `coursegoal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coursegoal` (
  `id_course_goal` int(11) NOT NULL AUTO_INCREMENT,
  `sign` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_course_goal` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `id_subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_course_goal`),
  KEY `fk_chuanmh_mh_idx` (`id_subject`),
  CONSTRAINT `fk_cmh_mh` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursegoal`
--

LOCK TABLES `coursegoal` WRITE;
/*!40000 ALTER TABLE `coursegoal` DISABLE KEYS */;
INSERT INTO `coursegoal` VALUES (1,'G1','Kết hợp làm việc cá nhân và nhóm để thảo luận và thực hiện đề tài theo nội dung môn học',5),(2,'G2','Trình bày được các khái niệm cơ bản liên quan đến nguyên lý lập trình, cách trao đổi thông tin giữa các cửa sổ, form trong ứng dụng. Khảo sát một số ứng dụng viết  bằng java. Hướng dẫn cài đặt các phần mềm liên quan đến môn học. Tổng quan về java',5),(3,'G3','Hiểu và sử dụng cú pháp lệnh trong Java. Lập trình hướng đối tượng trong Java.',5),(4,'G4','Hiểu và sử dụng thành thạo các thành phần: AWT (Abstract Windowing Toolkit), Swing và thiết kế giao diện dùng thư viện JFC (Java Foundation Class)',5),(5,'G5','Hiểu và xử lý sự kiện (Listeners and Events) và phương pháp quản lý giao diện (Layout Manager).',5),(6,'G6','Hiểu và sử dụng kiến thức xử lý luồng và tập tin trong java.',5),(7,'G7','Dùng Java JDBC API để truy xuất dữ liệu từ trình quản trị CSDL SQL Server, MySQL . Sử dụng thành thạo các câu lệnh truy vấn trong ứng dụng',5),(8,'G8','Hiểu cách tổ chức code bằng mô hình MVC, hiểu Framework là gì. Cách tổ chức code bằng Framework Hibernate. Cài đặt, kiểm chứng ứng dụng đã đăng ký.',5),(9,'G9','Đóng gói và triển khai ứng dụng viết bằng ngôn ngữ lập trình java.',5),(11,'G2','Hiểu, phân tích, thiết kế và vận dụng được công nghệ J2SE: lập trình giao diện và cơ sở dữ liệu trên Java vào việc xây dựng một ứng dụng trên máy tính.',2),(12,'G3','Hiểu, phân tích, thiết kế và vận dụng được công nghệ J2EE: lập trình Web với Servlet và JSP vào việc xây dựng một ứng dụng web.',2),(13,'G4','Có kỹ năng làm việc nhóm.',2),(14,'G1','Khối kiến thức nâng cao của ngành Khoa học máy tính',1),(15,'G2','Xác định và hình thành vấn đề. Suy luận và giải quyết',1),(16,'G3','Suy nghĩ toàn cục',1),(17,'G4','Cách nghĩ sáng tạo',1),(18,'G5','Thành lập nhóm, xác định vai trò của thành viên và lãnh đạo nhóm',1),(19,'G6','Kĩ năng trình bày',1),(20,'G7','Kỹ năng nghe, đọc tiếng Anh',1),(21,'G1','Nắm được khái niệm, vai trò của cấu trúc dữ liệu và giải thuật trong chương trình, các tiêu chuẩn đánh giá cấu trúc dữ liệu, các tiêu chuẩn đánh giá giải thuật',20),(22,'G2','Xác định và phát biểu bài toán tìm kiếm, các yếu tố có liên quan, các yếu tố ràng buộc trong bài toán, mô hình hóa bài toán,xác định các phương pháp giải quyết bài toán, phân tích các ưu điểm và hạn chế của các phương pháp giải quyết bài toán',20),(24,'G4','Nắm vững các cấu trúc dữ liệu cơ bản và\r\ncó khả năng ứng dụng các cấu trúc dữ liệu\r\ncơ bản',20),(25,'G5','Nắm vững cách thức cài đặt các cấu trúc dữ liệu, thuật toán và có thể áp dụng để giải quyết các bài toán đơn giản',20),(26,'G6','Đọc hiểu tài liệu tiếng Anh liên quan đến\r\ncác bài giảng.',20),(27,'G7','Biết, hiểu thuật ngữ tiếng Anh chuyên ngành của môn học.',20),(30,NULL,'',NULL),(31,'G9','Biết, hiểu thuật ngữ tiếng Anh chuyên ngành của môn học.',NULL),(32,'G9','abc',NULL),(33,'G9',NULL,NULL),(34,'G9',NULL,NULL),(35,'G9',NULL,NULL),(36,'G9',NULL,NULL),(37,'G9',NULL,NULL),(38,'G9',NULL,NULL),(39,'G9',NULL,NULL),(40,'G9',NULL,NULL),(41,'G9',NULL,NULL),(42,'G9',NULL,NULL),(43,'G9',NULL,NULL),(44,'G9',NULL,NULL),(45,'G9',NULL,NULL),(46,'G9',NULL,NULL),(47,'G9',NULL,NULL),(48,'G9',NULL,NULL),(49,'G9',NULL,NULL),(50,'G9',NULL,NULL),(51,'G9',NULL,NULL),(52,'G9','Hình thành ý thức trong việc tham khảo và trích dẫn các tài liệu tham khảo, các đoạn code mẫu,...',NULL),(53,'G9','Biết, hiểu thuật ngữ tiếng Anh chuyên ngành của môn học.',20),(56,'G5','Sử dụng một số công cụ phần mềm hỗ trợ',2),(57,'G6','Kiến thức nền tảng Java cơ bản và hướng đối tượng.',2),(61,'G8','Hình thành ý thức trong việc tham khảo và trích dẫn các tài liệu tham khảo, các đoạn code mẫu,...',20);
/*!40000 ALTER TABLE `coursegoal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesgoal_learningoutcome`
--

DROP TABLE IF EXISTS `coursesgoal_learningoutcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coursesgoal_learningoutcome` (
  `id_courses_goal` int(11) NOT NULL,
  `id_learning_outcome` int(11) NOT NULL,
  PRIMARY KEY (`id_courses_goal`,`id_learning_outcome`),
  KEY `fk_idcdr_idx` (`id_learning_outcome`),
  CONSTRAINT `fk_idcdr` FOREIGN KEY (`id_learning_outcome`) REFERENCES `learningoutcome` (`id_learningoutcome`),
  CONSTRAINT `fk_idcnh_cmh` FOREIGN KEY (`id_courses_goal`) REFERENCES `coursegoal` (`id_course_goal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesgoal_learningoutcome`
--

LOCK TABLES `coursesgoal_learningoutcome` WRITE;
/*!40000 ALTER TABLE `coursesgoal_learningoutcome` DISABLE KEYS */;
INSERT INTO `coursesgoal_learningoutcome` VALUES (53,1),(1,2),(53,2),(53,3),(53,4),(2,5),(3,5),(53,5),(4,7),(5,10),(20,10);
/*!40000 ALTER TABLE `coursesgoal_learningoutcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `id_department` int(11) NOT NULL AUTO_INCREMENT,
  `name_department` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_user_head_department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_department`),
  KEY `fk_truong_khoa_idx` (`id_user_head_department`),
  CONSTRAINT `fk_truong_khoa` FOREIGN KEY (`id_user_head_department`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Công nghệ thông tin',11),(2,'Kĩ thuật phần mềm',1035),(3,'Khoa học máy tính',1036),(4,'Hệ thống thông tin',1037),(5,'Mạng máy tính và truyền thông',1038),(6,'Kĩ thuật máy tính',1039);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learningoutcome`
--

DROP TABLE IF EXISTS `learningoutcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `learningoutcome` (
  `id_learningoutcome` int(11) NOT NULL AUTO_INCREMENT,
  `sign` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `namelearningoutcome` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `id_department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_learningoutcome`),
  KEY `fk_chuandaura_khoa_idx` (`id_department`),
  CONSTRAINT `fk_chuandaura_khoa` FOREIGN KEY (`id_department`) REFERENCES `department` (`id_department`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learningoutcome`
--

LOCK TABLES `learningoutcome` WRITE;
/*!40000 ALTER TABLE `learningoutcome` DISABLE KEYS */;
INSERT INTO `learningoutcome` VALUES (1,'L1','Kiến thức ngành',1),(2,'L2','Lập luận phân tích và giải quyết vấn đề',1),(3,'L3','Thử nghiệm, khảo sát và khám phá tri thức',1),(4,'L4','Tư duy hệ thống',1),(5,'L5','Kỹ năng và thái độ cá nhân',1),(6,'L6','Đạo đức, trung thực và trách nhiệm',1),(7,'L7','Kỹ năng làm việc nhóm',1),(8,'L8','Kỹ năng giao tiếp',1),(9,'L9','Kỹ năng ngoại ngữ',1),(10,'L10','Hình thành ý tưởng, thiết kế, triển khai và vận hành trong bối cảnh doanh nghiệp và xã hội',1),(11,'L1','Nắm vững kiến thức khoa học cơ bản và kiến thức nền tảng nhóm ngành',2),(12,'L2','Nắm vững kiến thức ngành kỹ thuật nâng cao',2),(13,'L3','Kỹ năng lập luận phân tích và giải quyết vấn đề',2),(14,'L4','Kỹ năng thử nghiệm, khảo sát và khám phá tri thức',2),(15,'L5','Kỹ năng tư duy hệ thống',2),(16,'L6','Kỹ năng và thái độ cá nhân',2),(17,'L7','Kỹ năng giao tiếp và làm việc nhóm',2),(18,'L8','Kỹ năng học ngoại ngữ',2),(19,'L9','Hình thành ý tưởng, thiết kế, triển khai và vận hành trong bối cảnh doanh nghiệp và xã hội',2),(20,'L1','Kiến thức nền tảng của ngành KHMT',3),(21,'L2','Kiến thức chuyên ngành và nâng cao của ngành KHMT',3),(22,'L3','Kỹ năng lập luận tư duy và giải quyết vấn đề',3),(23,'L4','Kỹ năng nghiên cứu và khám phá tri thức',3),(24,'L5','Kỹ năng tư duy theo hệ thống',3),(25,'L6','Kỹ năng tự học và học suốt đời',3),(26,'L7','Các kỹ năng cá nhân và kỹ năng nhóm',3),(27,'L8','Các kỹ năng về ngoại ngữ',3),(28,'L9','Đạo đức, trách nhiệm và các giá trị cá nhân cốt lõi',3),(35,'L1','Nắm vững kiến thức khoa học cơ bản và có khả năng vận dụng vào chuyên ngành.',4),(36,'L2','Nắm vững kiến thức kiến thức nền tảng, nâng cao ngành hệ thống thông tin, các kỹ năng phân tích, thiết kế, lập trình, tổ chức và khám phá tri thức vận dụng vào thực tiễn nhằm hỗ trợ các hoạt động tác nghiệp và quản lý. Đề xuất giải pháp cho các vấn đề trong lĩnh vực CNTT để nâng cao sức cạnh tranh, phát triển của các tổ chức, doanh nghiệp, thiết lập các mục tiêu khả thi, chứng tỏ được sự hiểu biết phù hợp với đương thời. ',4),(37,'L3','Có khả năng làm việc trực tiếp bằng tiếng Anh trong lĩnh vực công nghệ thông tin, trình độ Anh văn đạt từ TOEIC 450, TOEFL iBT 45, TOEFL ITP 430, IELTS 4.5, BULATS 47 hoặc tương đương.',4),(38,'L4','Có kỹ năng tự học các kiến thức bổ trợ để phục vụ cho hướng công việc tương  lai.',4),(39,'L5','Có khả năng giao tiếp xã hội, kỹ năng làm việc nhóm với tác phong chuyên nghiệp và đạo đức nghề nghiệp tốt.',4),(40,'L6','Ý thức được vai trò, trách nhiệm, đạo đức nghề nghiệp trong xã hội, có thế giới  quan, nhân sinh quan đúng đắn và có khả năng nhận thức, đánh giá các hiện tượng một cách logic và tích cực.',4),(41,'L7','Có nhận thức được sự cần thiết của việc học tập suốt đời, có kiến thức chuyên môn rộng, hiểu được tác động của các công nghệ mới trong bối cảnh xã hội, kinh tế toàn cầu.',4),(42,'L1','Kiến thức về khoa học cơ bản',5),(43,'L2','Kiến thức của lĩnh vực Công nghệ Thông tin',5),(44,'L3','Kiến thức cơ sở ngành Mạng máy tính và Truyền thông',5),(45,'L4','Kiến thức về xã hội',5),(46,'L5','Kỹ năng lập luận phân tích và giải quyết vấn đề',5),(47,'L6','Kỹ năng thử nghiệm, nghiên cứu và khám phá tri thức',5),(48,'L7','Kỹ năng tư duy hệ thống',5),(49,'L8','Kỹ năng hình thành ý tưởng, kỹ thuật hệ thống',5),(50,'L9','Kỹ năng thiết kế',5),(51,'L10','Kỹ năng triển khai',5),(52,'L11','Kỹ năng vận hành, đánh giá, kiểm chứng và cải tiến.',5),(53,'L12','Kỹ năng làm việc nhóm, giao tiếp',5),(54,'L13','Kỹ năng ngoại ngữ',5),(55,'L14','Thái độ, tư tưởng và học tập',5),(56,'L15','Đạo đức, công bằng và các trách nhiệm xã hội',5),(57,'L1','Có kiến thức cơ bản và khả năng vận dụng vào chuyên ngành, nắm vững kiến thức kỹ thuật máy tính và ứng dụng vào thực tiễn.',6),(58,'L2','Có kiến thức rộng để từ đó hiểu được tác động của các công nghệ mới trong bối cảnh xã hội, kinh tế toàn cầu.',6),(59,'L3','Có khả năng nghiên cứu và thiết kế các bộ phận chức năng của máy tính hoặc các bộ phận số trong các thiết bị điện tử.',6),(60,'L4','Có khả năng về phân tích, thiết kế, xây dựng các phần cứng và lập trình các phần mềm chuyên dụng; nắm vững các kiến thức lập trình bao gồm cả lập trình cấp cao và cấp thấp, có khả năng lập trình cho các thiết bị ngoại vi, cho các hệ thống nhúng, firmware, driver, thiết bị di động.',6),(61,'L5','Có khả năng làm việc chuyên môn độc lập và phối hợp làm việc với các ngành liên quan để giải quyết các vấn đề phức tạp nảy sinh trong quá trình làm việc.',6),(62,'L6','Khả năng thiết lập các mục tiêu khả thi, lập kế hoạch phù hợp với điều kiện thực tế, giải quyết các vấn đề liên quan đến kỹ thuật máy tính.',6),(63,'L7','Khả năng giao tiếp xã hội, hợp tác và làm việc nhóm với tác phong và đạo đức nghề nghiệp tốt.',6),(64,'L8','Khả năng vận dụng các kiến thức, kỹ năng và kinh nghiệm để giải quyết các tình huống nghề nghiệp khác nhau, trong đó coi trọng kỹ năng giao tiếp.',6),(65,'L9','Khả năng ứng dụng các cơ sở toán học, nguyên lý giải thuật, và lý thuyết kỹ thuật máy tính trong việc mô hình và thiết kế các hệ thống sao cho chứng tỏ được sự hiểu biết về cách lựa chọn đó là phù hợp với đương thời.',6),(66,'L10','Có khả năng làm việc trực tiếp bằng tiếng Anh trong lĩnh vực công nghệ thông tin. Có trình độ Anh văn đạt từ VNU-ETP 8 (TOEFL iBT 35-45 điểm, PBT 417-450 điểm) hoặc tương đương.',6),(67,'L11','Có nhận thức về sự cần thiết và khả năng tham gia vào việc học tập suốt đời để có thể làm việc hiệu quả trong bối cảnh những công nghệ mới liên tục xuất hiện.',6),(68,'L12','Ý thức được vai trò, trách nhiệm, đạo đức nghề nghiệp trong xã hội. Có thế giới quan, nhân sinh quan đúng đắn và có khả năng nhận thức, đánh giá các hiện tượng một cách logic và tích cực. ',6);
/*!40000 ALTER TABLE `learningoutcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `living_class`
--

DROP TABLE IF EXISTS `living_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `living_class` (
  `id_living_class` int(11) NOT NULL AUTO_INCREMENT,
  `name_living_class` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_user_adviser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_living_class`),
  KEY `fk_covanhoctap_idx` (`id_user_adviser`),
  CONSTRAINT `fk_covanhoctap` FOREIGN KEY (`id_user_adviser`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `living_class`
--

LOCK TABLES `living_class` WRITE;
/*!40000 ALTER TABLE `living_class` DISABLE KEYS */;
INSERT INTO `living_class` VALUES (1,'CNTT2016',11),(2,'CNTT2017',1035),(3,'CNTT2018',1036),(4,'KTPM2014',1037),(5,'KTPM2015',1038),(6,'KTPM2018',1039),(7,'HTTT2015',12),(8,'HTTT2016',11);
/*!40000 ALTER TABLE `living_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_STUDENT'),(2,'ROLE_LECTURER'),(3,'ROLE_MANAGE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scores_table`
--

DROP TABLE IF EXISTS `scores_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `scores_table` (
  `id_score_table` int(11) NOT NULL AUTO_INCREMENT,
  `score_process` float DEFAULT '0',
  `score_practice` float DEFAULT '0',
  `score_mid_term` float DEFAULT '0',
  `score_end_term` float DEFAULT '0',
  `score_average` float DEFAULT '0',
  `id_user` int(11) DEFAULT NULL,
  `id_subject` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_score_table`),
  KEY `fk_diem_monhoc_idx` (`id_subject`),
  KEY `fk_user_idx` (`id_user`),
  CONSTRAINT `fk_bangdiem_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `fk_diem_monhoc` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scores_table`
--

LOCK TABLES `scores_table` WRITE;
/*!40000 ALTER TABLE `scores_table` DISABLE KEYS */;
INSERT INTO `scores_table` VALUES (1,5,10,10,10,10,10,5),(2,6,10,10,10,10,10,6),(3,1,10,10,10,10,10,1),(4,2,10,10,10,10,10,2),(5,3,10,10,10,10,10,3),(6,8,9,9,9,1,10,8),(195,8,8,10,4,7,14,2),(196,10,10,7,4,6.7,15,2),(197,6,6,10,4,6.4,16,2),(198,9,9,10,4,7.3,17,2),(199,7,7,6,5,5.9,18,2),(200,8,8,9,5,7.1,19,2),(201,6,6,10,5,6.8,20,2),(202,8,8,10,5,7.4,21,2),(203,3,3,10,9,7.5,22,2),(204,10,10,10,8,9.2,23,2),(205,8,8,5,8,7.1,24,2),(206,8,8,10,8,8.6,25,2),(207,2,2,10,10,7.6,26,2),(208,5,5,10,10,8.5,27,2),(209,8,8,10,10,9.4,28,2),(210,1,1,10,7,6.1,29,2),(211,4,4,10,7,7,30,2),(212,8,8,10,7,8.2,31,2),(213,0,0,10,3,4.2,32,2),(214,4,4,10,3,5.4,33,2),(217,NULL,8,10,4,6.6,14,1),(218,NULL,10,7,4,6.1,15,1),(219,NULL,6,10,4,6.2,16,1),(220,NULL,9,10,4,6.8,17,1),(221,NULL,7,6,5,5.7,18,1),(222,NULL,8,9,5,6.8,19,1),(223,NULL,6,10,5,6.7,20,1),(224,NULL,8,10,5,7.1,21,1),(225,NULL,3,10,9,8.1,22,1),(226,NULL,10,10,8,9,23,1),(227,NULL,8,5,8,7.1,24,1),(228,NULL,8,10,8,8.6,25,1),(229,NULL,2,10,10,8.4,26,1),(230,NULL,5,10,10,9,27,1),(231,NULL,8,10,10,9.6,28,1),(232,NULL,1,10,7,6.7,29,1),(233,NULL,4,10,7,7.3,30,1),(234,NULL,8,10,7,8.1,31,1),(235,NULL,0,10,3,4.5,32,1),(236,NULL,4,10,3,5.3,33,1);
/*!40000 ALTER TABLE `scores_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subject` (
  `id_subject` int(11) NOT NULL AUTO_INCREMENT,
  `name_subject` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_subject` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_time` date DEFAULT '0000-00-00',
  `end_time` date DEFAULT '0000-00-00',
  `number_of_credit` int(2) DEFAULT '0',
  `rate_process` float DEFAULT '0',
  `rate_practice` float DEFAULT '0',
  `rate_mid_term` float DEFAULT '0',
  `reate_end_term` float DEFAULT '0',
  `id_teacher` int(11) DEFAULT NULL,
  `id_practice_teacher` int(11) DEFAULT NULL,
  `id_department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_subject`),
  KEY `fdsfk_idx` (`id_teacher`),
  KEY `dsafsdafsdf_idx` (`id_department`),
  KEY `sdfsadf_idx` (`id_practice_teacher`),
  CONSTRAINT `dsafsdafsdf` FOREIGN KEY (`id_department`) REFERENCES `department` (`id_department`),
  CONSTRAINT `fdsfk` FOREIGN KEY (`id_teacher`) REFERENCES `user` (`id_user`),
  CONSTRAINT `sdafsdfsdfsdf` FOREIGN KEY (`id_practice_teacher`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Tối ưu hóa công cụ tìm kiếm','tuhcctk2019','2019-10-10','2019-10-10',4,NULL,20,30,50,11,NULL,1),(2,'Công nghệ Java','cnjava2019','2019-10-10','2019-10-10',4,10,20,30,40,11,NULL,1),(3,'Phát triển ứng dụng web','ptudw2019','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(4,'Quản lý thông tin','qltt2018','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(5,'Lập trình Java','ltjava2019','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,2),(6,'Internet và công nghệ Web','ivcnw2018','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(7,'Hệ điều hành','hdh2018','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(8,'Tư tưởng Hồ Chí Minh','tthcm2018','2019-10-10','2019-10-10',2,NULL,NULL,NULL,NULL,1042,NULL,1),(9,'Thiết kế và lập trình Web	','tkltw2019','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(10,'Xử lý tín hiệu số','xlths2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(11,'An toàn mạng máy tính','atmmt2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(12,'Thiết kế hệ thống số với HDL','tkhtts2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(13,'Tương tác người - máy','2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(14,'Trình biên dịch','2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(15,'Logic mờ và ứng dụng','2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(16,'Kỹ thuật chế tạo vi mạch','2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(17,'Thiết kế vi mạch hỗn hợp','2019','2019-10-10','2019-10-10',0,NULL,NULL,NULL,NULL,11,NULL,1),(18,'Kĩ thuật thiết kế mạch in','kttkmi2019','2019-10-10','2019-10-10',4,NULL,NULL,NULL,NULL,11,NULL,1),(19,'Cấu trúc dữ liệu và giải thuật nâng cao','ctdlgtnc','2019-05-01','2019-05-18',4,NULL,NULL,NULL,NULL,11,NULL,1),(20,'Cấu trúc dữ liệu và giải thuật',NULL,'2019-05-01','2019-05-01',4,NULL,NULL,NULL,NULL,11,NULL,1),(21,'Cơ sở dữ liệu',NULL,'2019-05-01','2019-05-01',4,NULL,NULL,NULL,NULL,11,NULL,1),(22,'Đồ họa máy tính',NULL,'2019-05-01','2019-05-01',4,NULL,NULL,NULL,NULL,11,NULL,1),(23,'Hệ quản trị Cơ sở dữ liệu',NULL,'2019-05-01','2019-05-01',4,NULL,NULL,NULL,NULL,11,NULL,1),(24,'Kỹ năng nghề nghiệp',NULL,'2019-05-01','2019-05-01',2,NULL,NULL,NULL,NULL,11,NULL,1),(25,'Lập trình hướng đối tượng',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(26,'Lập trình Java',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(27,'Logic mờ và ứng dụng',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(28,'Lý thuyết thông tin',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(29,'Ngôn ngữ lập trình C#',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(30,'Nguyên lý và Phương pháp lập trình',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(31,'Nhập môn Công nghệ tri thức và Máy học',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(32,'Nhập môn lập trình',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(33,'Nhập môn mạch số',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(34,'Phân tích và thiết kế hướng đối tượng với UML',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(35,'Phân tích và thiết kế thuật toán',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(36,'Toán rời rạc nâng cao',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(37,'Trí tuệ Nhân tạo',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(38,'Xử lý ảnh và ứng dụng',NULL,'2019-05-01','2019-05-01',0,NULL,NULL,NULL,NULL,11,NULL,1),(39,'mon hoc moi','mamh','2019-06-10','2019-06-12',4,10,10,30,50,13,13,1);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `day_of_birth` date DEFAULT NULL,
  `id_department` int(11) DEFAULT NULL,
  `id_living_class` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fsdfsdf_idx` (`id_living_class`),
  KEY `wfsadf_idx` (`id_department`),
  CONSTRAINT `fsdfsdf` FOREIGN KEY (`id_living_class`) REFERENCES `living_class` (`id_living_class`),
  CONSTRAINT `wfsadf` FOREIGN KEY (`id_department`) REFERENCES `department` (`id_department`)
) ENGINE=InnoDB AUTO_INCREMENT=1045 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'student','$2a$10$cHlUA/XniX6YWLdJvcKb/u9vdn9IMjGhWOcAO32nsjMXtm3lnP5Aq','Hà Tiến Dũng','1969-10-30',1,1),(11,'lecturer','$2a$10$ff3hPk1isW8hdhaAqQ8Eje37vbSNSJ5XjbnFhFJD8Yrli4kagHcQq','lecturer','1969-10-30',1,NULL),(12,'manage1','$2a$10$cSBYRKQGbjjVn.6gW//8XuzUGItXyWVatSxVfFYKkKM244K7x51zu','manage1','1969-10-30',1,NULL),(13,'manage2','$2a$10$8/OvXxypYQBtv1yJpqn6VOA7VduNN6LZG5hFsg5nymXJSjnKLsU4K','manage2','1969-10-30',1,NULL),(14,'student 1','$2a$10$6nl0sQsXHD9Te9GHe2xKv.CPgxHMUa3khxtgD9XYbAD5WAn.RlkfW','student  1','1969-10-30',1,1),(15,'student 2','$2a$10$pBetJALm.sXUm0P3ZMKmJuuOYM3CsjKbeQezIBnEqd3VwVIOhLJvC','student 2','1969-10-30',1,1),(16,'student 3','$2a$10$Pm5nhF9o/B1RsldVvjKCyuEHEsaPKT1xXmJ.nXExQMidvToO95O3O','student 3','1969-10-30',2,1),(17,'student 4','$2a$10$wno.s/kk7W8cIcJgI.qXUuCLLET4MmjyZQ2ks2o4hW..Vrm9k7Mky','student 4','1969-10-30',3,1),(18,'student 5','$2a$10$7m2jIVHH8JNhi2n4et8BBeUkoBwElXn3i1d3jwjXBS15AX0401Tj.',NULL,'1969-10-30',4,1),(19,'student 6','$2a$10$tAw4V8mRMEp5X.kJk2n.3.Y8w.pckMNaSFqT8cDiSLlQk5YHre.qi',NULL,'1969-10-30',5,1),(20,'student 7','$2a$10$z2LzCvD0qlmYiMPb5PLFFeJWuYGanbCuxXH4bZe3gPsfmKwjC5ESO',NULL,'1969-10-30',5,1),(21,'student 8','$2a$10$QSiM71rtvTKmRQPyz/XouOnGc9MA4cX4b5UVuMGO4DWBIOXnemPIC',NULL,'1969-10-30',4,1),(22,'student 9','$2a$10$N3xeUA6THDxcvch/QCfSz.FTgp9U9VdOGQL9U2ugvmx9A.fumNgFy',NULL,'1969-10-30',3,1),(23,'student 10','$2a$10$Iqc7P24O55YrZxCs2Wo6V.i439bcK1b3ndvxkSUXK9mzc7NTgybfu',NULL,'1969-10-30',3,1),(24,'student 11','$2a$10$yjQxbHeLq4gqYu3thL6ys.13.c92HI8OMqyeBjFx8FbqgUmRVfuEq',NULL,'1969-10-30',2,1),(25,'student 12','$2a$10$A/HCucrJtXrSgt8gRKb9y.Uo2XmuQZz.SwBmtIQCoZcSbItGli5LO',NULL,'1969-10-30',2,1),(26,'student 13','$2a$10$KNMQRhlD3n863WrfV3tUXODKUZNVae4kD8rp7jY94EG0rHdV8heqe',NULL,'1969-10-30',1,1),(27,'student 14','$2a$10$428WoCZv7Ht2uQBJbae7Du/leWkPC7TOYQiA3FBMLcr889Dn5xzOu',NULL,'1969-10-30',1,1),(28,'student 15','$2a$10$OQi/U0MKa.G.Pm0l67.b9ux0D4GTa1UJuoJ5UsPJmeX4Re.h8rew.',NULL,'1969-10-30',1,1),(29,'student 16','$2a$10$aPNs2KIV6cWbTPXRRO26kO04tY6wxBUh2C0zNC3nOXSWQs1t0611G',NULL,'1969-10-30',1,1),(30,'student 17','$2a$10$ZMWiZrOaUXYGCyuMlc5LDuuElJo1xxN16p7N.Tydlf35xqTWxay/u',NULL,'1969-10-30',1,NULL),(31,'student 18','$2a$10$USBvigc11/7oO9nvtgud1O8qzfijX7FWTFbDWecJf0um5PxDQHLf2',NULL,'1969-10-30',1,NULL),(32,'student 19','$2a$10$EIRQXY/lR62MforJruCvVuF.1QtHvHcTJaJFhRIJzph1OYtXJWARC',NULL,'1969-10-30',2,NULL),(33,'student 20','$2a$10$R54YQ0M1hHnORLpOMadIIOKoP52.2K5QheO4zvi8byCBDix4TGxAi',NULL,'1969-10-30',3,NULL),(34,'student 21','$2a$10$rZM5PqFX0pw.YLXWv3nwiecV.qaGaAW9Ifb22EpccQBgCKvlRuQAq',NULL,'1969-10-30',3,NULL),(35,'student 22','$2a$10$djkJOmcsBCTslAuEg7AOguHWMmdhFUUSNCZ5Af1QNsPuRBldVjG4.',NULL,'1969-10-30',3,NULL),(36,'student 23','$2a$10$6KIBnXwwgCJpLNFgPOZcI.rvUQOm2bs9ULAzEQBlTp9rJi4jjJvRu',NULL,'1969-10-30',2,NULL),(37,'student 24','$2a$10$aIOK.sqtO2hODiU5ETZ64.rGlfewxKjIHpeRtbwaFRSWD0ACkh5Ty',NULL,'1969-10-30',2,NULL),(38,'student 25','$2a$10$sTdo/0E3KcF6qGIS9RkS9OMQfUXv9x0V/fXsFgHHpFG9dktl3303.',NULL,'1969-10-30',2,NULL),(39,'student 26','$2a$10$VeKO5CXDuFRwuy41xYa1iuDQU8tfUGfvK7P0A4a2oBukvs7OwNjpe',NULL,'1969-10-30',2,NULL),(40,'student 27','$2a$10$2bV/HKXwOG3VZnfazcVseOoeZLx5SrYpHdYN6.0pa1v5Kb9tK68OW',NULL,'1969-10-30',2,NULL),(41,'student 28','$2a$10$r3QgqIM/pP8Tj6405kECWelcn/8hGuv6flet0TSQhCOIHJpmWvRIW',NULL,'1969-10-30',4,NULL),(42,'student 29','$2a$10$2b2mMw/1Sn4HGdvXE.p.o.uQ3TDDUdHJ1Qm2z7edAr4l7Cr0RkeKe',NULL,'1969-10-30',4,NULL),(43,'student 30','$2a$10$.qHU8ydH6Kh3e7jT09eV8Oj0bfuw3sBCuuivOw/YpcEddQkumKR5G',NULL,'1969-10-30',4,NULL),(44,'student 31','$2a$10$vsQChvy/Gq4.JQSyLpY7yORAE5mYCS49ynwOhzL7CE.ESz1iWYwVe',NULL,'1969-10-30',4,NULL),(45,'student 32','$2a$10$gtJ2BHAJcztcbYLvk1nVO.5qPrKxTEX9V0vqOZCIh92a3XVeqp0Li',NULL,'1969-10-30',4,NULL),(46,'student 33','$2a$10$0FomaMAkE/ht5NsyF/CyHOJThZxR5gbSfLz7x.sHpPGIkpASIUloy',NULL,'1969-10-30',4,NULL),(47,'student 34','$2a$10$.3jbyRzN5NTUPavJOwbJget6CXnkpLyzdWadOk14zqle241Rf4CIa',NULL,'1969-10-30',4,NULL),(48,'student 35','$2a$10$nC/GpPYY34JrgxuT2snE7ORIRW9PTlX3lGvDCEltfOKd7ojRqUbQu',NULL,'1969-10-30',4,NULL),(49,'student 36','$2a$10$4iJnwgcQuK.w8zYN4RP0Vu7vkndZw1mHXRCuw7u.zUN3f3sucHDnO',NULL,'1969-10-30',4,NULL),(50,'student 37','$2a$10$sivYAXncMVj2Oou3xQF4cOz09G2pQSMxtMnAI4qjZIrcwD4D4Ykdq',NULL,'1969-10-30',4,NULL),(1035,'lecturer2','$2a$10$sll.q9UL0Qbi.sJ3hL5Tz.ccqG0kEO7tCAsb66up4kXB6dI/hDsAC','lecturer2','1969-10-30',4,NULL),(1036,'lecturer3','$2a$10$AJEQ3cFkYzDs.NsPGpyBzub5rMudVVe7mH6XYj4IfrAFeNH7QxdGa',NULL,'1969-10-30',4,NULL),(1037,'lecturer4','$2a$10$FZp9mqBtApgdyDxlq0pvkufx5DTgKFbSnUnDb7NahjQjMW3CEcyoW',NULL,'1969-10-30',4,NULL),(1038,'lecturer5','$2a$10$OAkqlK06SOuzv39GfmaQ..j82HBTN4343YB2K/3MnXsR4x/Sc5XFi',NULL,'1969-10-30',4,NULL),(1039,'lecturer6','$2a$10$esZVyGrdeSu51035AsoNyOiAyAYRnvAy7QkzfFV7HmpAz53g0bskS',NULL,'1969-10-30',4,NULL),(1040,'lecturer7','$2a$10$fZA9Dkd5XjFBX5JN.vckZer/LAKqjPSHMRLbVbknfHkJlPTBXMc7C',NULL,'1969-10-30',4,NULL),(1041,'lecturer8','$2a$10$7B5uaQgd8AEeYcH9POncm.UehmEZ59psIySYx/tMCYY54z3FJ6mBi',NULL,'1969-10-30',4,NULL),(1042,'lecturer9','$2a$10$vt9sJBzKKLplqrIcf/ZksusSmafMSnzjiAKAIuPaibCZa81Hy/6ii',NULL,'1969-10-30',4,NULL),(1043,'lecturer10','$2a$10$O62qnh4rLsINGgvknh7IYeCAwxGROCUxyw40g42jLSdVYCOsViQo.',NULL,'1969-10-30',4,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_learningoutcome`
--

DROP TABLE IF EXISTS `user_learningoutcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_learningoutcome` (
  `id_user_learning_outcome` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_learningoutcome` int(11) DEFAULT NULL,
  `percent` float DEFAULT NULL,
  PRIMARY KEY (`id_user_learning_outcome`),
  KEY `fk_asdfasdfasdf_idx` (`id_learningoutcome`),
  KEY `fk_asdfsadf_idx` (`id_user`),
  CONSTRAINT `fk_asdfsadf` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `sdfsadf` FOREIGN KEY (`id_learningoutcome`) REFERENCES `learningoutcome` (`id_learningoutcome`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_learningoutcome`
--

LOCK TABLES `user_learningoutcome` WRITE;
/*!40000 ALTER TABLE `user_learningoutcome` DISABLE KEYS */;
INSERT INTO `user_learningoutcome` VALUES (1,10,1,100),(2,10,2,10),(3,10,3,69),(4,10,4,96),(5,10,5,96),(6,10,6,10),(7,10,7,50),(8,10,8,40),(9,10,9,30),(11,14,1,100),(12,14,2,100),(13,14,3,100),(14,14,4,NULL),(15,14,5,100),(16,14,7,100),(17,14,10,100),(18,15,5,0),(19,15,7,0),(20,15,10,0),(21,15,2,0),(22,16,5,80),(23,16,7,80),(24,16,10,80),(25,16,2,80),(26,17,5,40),(27,17,7,40),(28,17,10,40),(29,17,2,40),(30,18,5,60),(31,18,7,65),(32,18,10,60),(33,18,2,50),(34,19,5,60),(35,19,7,65),(36,19,10,60),(37,19,2,50),(38,20,5,60),(39,20,7,65),(40,20,10,60),(41,20,2,50),(42,21,5,60),(43,21,7,65),(44,21,10,60),(45,21,2,50),(46,22,5,60),(47,22,7,65),(48,22,10,60),(49,22,2,50),(50,23,5,60),(51,23,7,65),(52,23,10,60),(53,23,2,50),(54,24,5,60),(55,24,7,65),(56,24,10,60),(57,24,2,50),(58,25,5,80),(59,25,7,80),(60,25,10,60),(61,25,2,80),(62,26,5,60),(63,26,7,65),(64,26,10,60),(65,26,2,50),(66,27,5,60),(67,27,7,65),(68,27,10,60),(69,27,2,50),(70,28,5,60),(71,28,7,65),(72,28,10,60),(73,28,2,50),(74,29,5,60),(75,29,7,65),(76,29,10,60),(77,29,2,50),(78,30,5,60),(79,30,7,65),(80,30,10,60),(81,30,2,50),(82,31,5,60),(83,31,7,65),(84,31,10,60),(85,31,2,50),(86,32,5,60),(87,32,7,65),(88,32,10,60),(89,32,2,50),(90,33,5,60),(91,33,7,65),(92,33,10,60),(93,33,2,50);
/*!40000 ALTER TABLE `user_learningoutcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `fk_role_idx` (`id_role`),
  CONSTRAINT `fcasdasd` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `fkSADf` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (10,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1),(11,2),(13,2),(1035,2),(1036,2),(1037,2),(1038,2),(1039,2),(1040,2),(1041,2),(1042,2),(1043,2),(12,3),(13,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subject`
--

DROP TABLE IF EXISTS `user_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_subject` (
  `id_user` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_subject`),
  KEY `fk_monhoc_usermonhoc_idx` (`id_subject`),
  CONSTRAINT `dfsdfsdf` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`),
  CONSTRAINT `sdff` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subject`
--

LOCK TABLES `user_subject` WRITE;
/*!40000 ALTER TABLE `user_subject` DISABLE KEYS */;
INSERT INTO `user_subject` VALUES (10,1),(11,1),(13,1),(10,2),(11,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(10,3),(11,3),(13,3),(10,4),(11,4),(13,4),(10,5),(10,6),(10,7),(10,8),(11,8),(11,9);
/*!40000 ALTER TABLE `user_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subject_coursesgoal`
--

DROP TABLE IF EXISTS `user_subject_coursesgoal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_subject_coursesgoal` (
  `id_user_subject_coursesgoal` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `id_coursesgoal` int(11) NOT NULL,
  `percent` float DEFAULT '0',
  PRIMARY KEY (`id_user_subject_coursesgoal`),
  KEY `fk_subject_idsubject_idx` (`id_subject`),
  KEY `fk_coursesgoal_idx` (`id_coursesgoal`),
  KEY `fk_asdf_idx` (`id_user`),
  CONSTRAINT `fk_asdf` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `sadf` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id_subject`),
  CONSTRAINT `sadfsdf` FOREIGN KEY (`id_coursesgoal`) REFERENCES `coursegoal` (`id_course_goal`)
) ENGINE=InnoDB AUTO_INCREMENT=936 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subject_coursesgoal`
--

LOCK TABLES `user_subject_coursesgoal` WRITE;
/*!40000 ALTER TABLE `user_subject_coursesgoal` DISABLE KEYS */;
INSERT INTO `user_subject_coursesgoal` VALUES (8,10,2,1,100),(9,10,2,2,69),(696,14,2,1,100),(697,14,2,4,100),(698,14,2,20,100),(699,14,2,2,100),(700,14,2,3,100),(701,14,2,5,100),(702,15,2,1,0),(703,15,2,4,0),(704,15,2,20,0),(705,15,2,2,0),(706,15,2,3,0),(707,15,2,5,0),(708,16,2,1,80),(709,16,2,4,80),(710,16,2,20,80),(711,16,2,2,80),(712,16,2,3,80),(713,16,2,5,80),(714,17,2,1,40),(715,17,2,4,40),(716,17,2,20,40),(717,17,2,2,40),(718,17,2,3,40),(719,17,2,5,40),(720,18,2,1,60),(721,18,2,4,65),(722,18,2,20,60),(723,18,2,2,50),(724,18,2,3,65),(725,18,2,5,60),(726,19,2,1,60),(727,19,2,4,65),(728,19,2,20,60),(729,19,2,2,50),(730,19,2,3,65),(731,19,2,5,60),(732,20,2,1,60),(733,20,2,4,65),(734,20,2,20,60),(735,20,2,2,50),(736,20,2,3,65),(737,20,2,5,60),(738,21,2,1,60),(739,21,2,4,65),(740,21,2,20,60),(741,21,2,2,50),(742,21,2,3,65),(743,21,2,5,60),(744,22,2,1,60),(745,22,2,4,65),(746,22,2,20,60),(747,22,2,2,50),(748,22,2,3,65),(749,22,2,5,60),(750,23,2,1,60),(751,23,2,4,65),(752,23,2,20,60),(753,23,2,2,50),(754,23,2,3,65),(755,23,2,5,60),(756,24,2,1,60),(757,24,2,4,65),(758,24,2,20,60),(759,24,2,2,50),(760,24,2,3,65),(761,24,2,5,60),(762,25,2,1,80),(763,25,2,4,80),(764,25,2,20,60),(765,25,2,2,80),(766,25,2,3,80),(767,25,2,5,60),(768,26,2,1,60),(769,26,2,4,65),(770,26,2,20,60),(771,26,2,2,50),(772,26,2,3,65),(773,26,2,5,60),(774,27,2,1,60),(775,27,2,4,65),(776,27,2,20,60),(777,27,2,2,50),(778,27,2,3,65),(779,27,2,5,60),(780,28,2,1,60),(781,28,2,4,65),(782,28,2,20,60),(783,28,2,2,50),(784,28,2,3,65),(785,28,2,5,60),(786,29,2,1,60),(787,29,2,4,65),(788,29,2,20,60),(789,29,2,2,50),(790,29,2,3,65),(791,29,2,5,60),(792,30,2,1,60),(793,30,2,4,65),(794,30,2,20,60),(795,30,2,2,50),(796,30,2,3,65),(797,30,2,5,60),(798,31,2,1,60),(799,31,2,4,65),(800,31,2,20,60),(801,31,2,2,50),(802,31,2,3,65),(803,31,2,5,60),(804,32,2,1,60),(805,32,2,4,65),(806,32,2,20,60),(807,32,2,2,50),(808,32,2,3,65),(809,32,2,5,60),(810,33,2,1,60),(811,33,2,4,65),(812,33,2,20,60),(813,33,2,2,50),(814,33,2,3,65),(815,33,2,5,60),(816,14,1,14,50),(817,15,1,14,0),(818,16,1,14,40),(819,17,1,14,20),(820,18,1,14,20),(821,19,1,14,20),(822,20,1,14,20),(823,21,1,14,20),(824,22,1,14,20),(825,23,1,14,20),(826,24,1,14,20),(827,25,1,14,40),(828,26,1,14,20),(829,27,1,14,20),(830,28,1,14,20),(831,29,1,14,20),(832,30,1,14,20),(833,31,1,14,20),(834,32,1,14,20),(835,33,1,14,20),(836,14,1,15,50),(837,15,1,15,0),(838,16,1,15,40),(839,17,1,15,20),(840,18,1,15,30),(841,19,1,15,30),(842,20,1,15,30),(843,21,1,15,30),(844,22,1,15,30),(845,23,1,15,30),(846,24,1,15,30),(847,25,1,15,40),(848,26,1,15,30),(849,27,1,15,30),(850,28,1,15,30),(851,29,1,15,30),(852,30,1,15,30),(853,31,1,15,30),(854,32,1,15,30),(855,33,1,15,30),(856,14,1,16,60),(857,14,1,17,0),(858,14,1,19,60),(859,14,1,18,60),(860,15,1,16,30),(861,15,1,17,60),(862,15,1,19,0),(863,15,1,18,0),(864,16,1,16,30),(865,16,1,17,60),(866,16,1,19,0),(867,16,1,18,0),(868,17,1,16,30),(869,17,1,17,60),(870,17,1,19,0),(871,17,1,18,0),(872,18,1,16,30),(873,18,1,17,60),(874,18,1,19,0),(875,18,1,18,0),(876,19,1,16,30),(877,19,1,17,60),(878,19,1,19,0),(879,19,1,18,0),(880,20,1,16,30),(881,20,1,17,60),(882,20,1,19,0),(883,20,1,18,0),(884,21,1,16,30),(885,21,1,17,60),(886,21,1,19,90),(887,21,1,18,90),(888,22,1,16,30),(889,22,1,17,60),(890,22,1,19,30),(891,22,1,18,30),(892,23,1,16,30),(893,23,1,17,60),(894,23,1,19,30),(895,23,1,18,30),(896,24,1,16,30),(897,24,1,17,60),(898,24,1,19,30),(899,24,1,18,30),(900,25,1,16,60),(901,25,1,17,60),(902,25,1,19,30),(903,25,1,18,30),(904,26,1,16,30),(905,26,1,17,60),(906,26,1,19,30),(907,26,1,18,30),(908,27,1,16,30),(909,27,1,17,60),(910,27,1,19,30),(911,27,1,18,30),(912,28,1,16,30),(913,28,1,17,60),(914,28,1,19,30),(915,28,1,18,30),(916,29,1,16,30),(917,29,1,17,60),(918,29,1,19,60),(919,29,1,18,60),(920,30,1,16,30),(921,30,1,17,60),(922,30,1,19,60),(923,30,1,18,60),(924,31,1,16,30),(925,31,1,17,60),(926,31,1,19,60),(927,31,1,18,60),(928,32,1,16,30),(929,32,1,17,60),(930,32,1,19,60),(931,32,1,18,60),(932,33,1,16,30),(933,33,1,17,60),(934,33,1,19,60),(935,33,1,18,60);
/*!40000 ALTER TABLE `user_subject_coursesgoal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-11 23:18:06
