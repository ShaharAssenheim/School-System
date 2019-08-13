CREATE DATABASE  IF NOT EXISTS `semproject` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `semproject`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: semproject
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `changerequests`
--

DROP TABLE IF EXISTS `changerequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changerequests` (
  `ReqID` varchar(45) NOT NULL,
  `OldTeacher` varchar(45) DEFAULT NULL,
  `ClassName` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  `TeachingUnit` varchar(45) DEFAULT NULL,
  `NewTeacher` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `SemID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ReqID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changerequests`
--

LOCK TABLES `changerequests` WRITE;
/*!40000 ALTER TABLE `changerequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `changerequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_hws`
--

DROP TABLE IF EXISTS `class_hws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_hws` (
  `ClassName` varchar(45) NOT NULL,
  `HWID` varchar(45) NOT NULL,
  PRIMARY KEY (`ClassName`,`HWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_hws`
--

LOCK TABLES `class_hws` WRITE;
/*!40000 ALTER TABLE `class_hws` DISABLE KEYS */;
INSERT INTO `class_hws` VALUES ('1st Grade 1','1'),('1st Grade 1','10'),('1st Grade 1','11'),('1st Grade 1','12'),('1st Grade 1','13'),('1st Grade 1','2'),('1st Grade 1','3'),('1st Grade 1','4'),('1st Grade 1','5'),('1st Grade 1','6'),('1st Grade 1','7'),('1st Grade 1','8'),('1st Grade 1','9');
/*!40000 ALTER TABLE `class_hws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `ClassID` varchar(45) NOT NULL,
  `SemID` varchar(45) NOT NULL,
  `ClassName` varchar(45) NOT NULL,
  `ClassNumber` varchar(45) NOT NULL,
  `MaxStudentsNum` varchar(45) NOT NULL,
  `CurStudentsNum` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ClassID`,`SemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES ('1st Grade 1','20172','1st Grade','1','35','12'),('1st Grade 1','20181','1st Grade','1','30','4'),('2nd Grade 1','20172','2nd Grade','1','35','0'),('2nd Grade 1','20181','2nd Grade','1','30','0');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_pre`
--

DROP TABLE IF EXISTS `course_pre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_pre` (
  `cnum` varchar(45) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `PreCourse` varchar(45) NOT NULL,
  PRIMARY KEY (`cnum`,`CourseName`,`PreCourse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_pre`
--

LOCK TABLES `course_pre` WRITE;
/*!40000 ALTER TABLE `course_pre` DISABLE KEYS */;
INSERT INTO `course_pre` VALUES ('02102','Hedva2','02101'),('02102','Hedva2','02201'),('02202','Algebra2','02101'),('02202','Algebra2','02201'),('04502','Sport2','04501'),('05002','Advanced Music','05001');
/*!40000 ALTER TABLE `course_pre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `cnum` varchar(45) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `WeakHours` varchar(45) NOT NULL,
  `teachunit` varchar(45) DEFAULT NULL,
  `IsActive` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cnum`),
  UNIQUE KEY `CID_UNIQUE` (`cnum`),
  UNIQUE KEY `CourseName_UNIQUE` (`CourseName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('02101','Hedva1','6','Math (02)',NULL),('02102','Hedva2','6','Math (02)',NULL),('02201','Algebra1','5','Math (02)',NULL),('02202','Algebra2','5','Math (02)',NULL),('02999','Math','6','Math (02)',NULL),('03111','English','4','Languages (03)',NULL),('03112','Hebrew','4','Languages (03)',NULL),('03113','Spanish','4','Languages (03)',NULL),('03114','Russian','4','Languages (03)',NULL),('04501','Sport1','2','Sports (04)',NULL),('04502','Sport2','2','Sports (04)',NULL),('05001','Basic Music','4','Music Science (05)',NULL),('05002','Advanced Music','4','Music Science (05)',NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_forms`
--

DROP TABLE IF EXISTS `evaluation_forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation_forms` (
  `EFID` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `HWName` varchar(45) DEFAULT NULL,
  `SubName` varchar(45) DEFAULT NULL,
  `Attachments` varchar(450) DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EFID`),
  UNIQUE KEY `EFID_UNIQUE` (`EFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_forms`
--

LOCK TABLES `evaluation_forms` WRITE;
/*!40000 ALTER TABLE `evaluation_forms` DISABLE KEYS */;
INSERT INTO `evaluation_forms` VALUES ('1','555000003-Evaluation-003_Algebra1_Submission1','Algebra1_Homework1','003_Algebra1_Submission1','C:\\Asis\\Evaluations\\555000003-Evaluation-003_Algebra1_Submission1.txt',NULL),('2','555000002-Evaluation-002_Hedva1_Submission1','Hedva1_Homework1','002_Hedva1_Submission1','C:\\Asis\\Evaluations\\555000002-Evaluation-002_Hedva1_Submission1.txt',NULL),('3','555000002-Evaluation-002_Spanish_Submission1','Spanish_Homework1','002_Spanish_Submission1','C:\\Asis\\Evaluations\\555000002-Evaluation-002_Spanish_Submission1.txt',NULL),('4','555000002-Evaluation-002_BMusic_Submission1','BMusic_Homework1','002_BMusic_Submission1','C:\\Asis\\Evaluations\\555000002-Evaluation-002_BMusic_Submission1.txt',NULL),('5','555000001-Evaluation-001_English_Submission1','English_Homework1','001_English_Submission1','C:\\Asis\\Evaluations\\555000001-Evaluation-001_English_Submission1.txt',NULL),('6','555000001-Evaluation-001_Russian_Submission1','Russian_Homework1','001_Russian_Submission1','C:\\Asis\\Evaluations\\555000001-Evaluation-001_Russian_Submission1.txt',NULL),('7','555000003-Evaluation-003_Sport1_Submission1','Sport1_Homework1','003_Sport1_Submission1','C:\\Asis\\Evaluations\\555000003-Evaluation-003_Sport1_Submission1.txt',NULL),('8','555000001-Evaluation-subtest','test','subtest','C:\\Evaluations\\555000001-Evaluation-subtest.txt','20181'),('9','555000001-Evaluation--','aa','-','C:\\Evaluations\\555000001-Evaluation--.txt','20181');
/*!40000 ALTER TABLE `evaluation_forms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_sheets`
--

DROP TABLE IF EXISTS `grade_sheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_sheets` (
  `GSID` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `HWName` varchar(45) DEFAULT NULL,
  `SubName` varchar(45) DEFAULT NULL,
  `Attachments` varchar(450) DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`GSID`),
  UNIQUE KEY `GSID_UNIQUE` (`GSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_sheets`
--

LOCK TABLES `grade_sheets` WRITE;
/*!40000 ALTER TABLE `grade_sheets` DISABLE KEYS */;
INSERT INTO `grade_sheets` VALUES ('1','555000003-CheckedHW-003_Algebra1_Submission1','Algebra1_Homework1','003_Algebra1_Submission1','C:\\Asis\\GradeSheets\\555000003-CheckedHW-003_Algebra1_Submission1.txt',NULL),('2','555000002-CheckedHW-002_Hedva1_Submission1','Hedva1_Homework1','002_Hedva1_Submission1','C:\\Asis\\GradeSheets\\555000002-CheckedHW-002_Hedva1_Submission1.txt',NULL),('3','555000002-CheckedHW-002_Spanish_Submission1','Spanish_Homework1','002_Spanish_Submission1','C:\\Asis\\GradeSheets\\555000002-CheckedHW-002_Spanish_Submission1.txt',NULL),('4','555000002-CheckedHW-002_BMusic_Submission1','BMusic_Homework1','002_BMusic_Submission1','C:\\Asis\\GradeSheets\\555000002-CheckedHW-002_BMusic_Submission1.txt',NULL),('5','555000001-CheckedHW-001_English_Submission1','English_Homework1','001_English_Submission1','C:\\Asis\\GradeSheets\\555000001-CheckedHW-001_English_Submission1.txt',NULL),('6','555000001-CheckedHW-001_Russian_Submission1','Russian_Homework1','001_Russian_Submission1','C:\\Asis\\GradeSheets\\555000001-CheckedHW-001_Russian_Submission1.txt',NULL),('7','555000003-CheckedHW-003_Sport1_Submission1','Sport1_Homework1','003_Sport1_Submission1','C:\\Asis\\GradeSheets\\555000003-CheckedHW-003_Sport1_Submission1.txt',NULL),('8','555000001-CheckedHW-subtest','test','subtest','C:\\GradeSheets\\555000001-CheckedHW-subtest.txt','20181'),('9','-','aa','-','-','20181');
/*!40000 ALTER TABLE `grade_sheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homeworks`
--

DROP TABLE IF EXISTS `homeworks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homeworks` (
  `HWID` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `DeadLineDate` date NOT NULL,
  `Attachments` varchar(450) DEFAULT NULL,
  `ClassName` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`HWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homeworks`
--

LOCK TABLES `homeworks` WRITE;
/*!40000 ALTER TABLE `homeworks` DISABLE KEYS */;
INSERT INTO `homeworks` VALUES ('1','Algebra1_Homework1','2017-06-30','C:\\Asis\\Homeworks\\20172\\Algebra1\\1st Grade 1\\Algebra1_Homework1.txt','1st Grade 1','Algebra1','20172'),('10','Russian_Homework1','2017-06-29','C:\\Asis\\Homeworks\\20172\\Russian\\1st Grade 1\\Russian_Homework1.txt','1st Grade 1','Russian','20172'),('11','test','2017-06-18','C:\\Homeworks\\20181\\Hedva1\\1st Grade 1\\test.txt','1st Grade 1','Hedva1','20181'),('12','test2','2017-07-04','C:\\Homeworks\\20181\\English\\1st Grade 1\\test2.txt','1st Grade 1','English','20181'),('13','aa','2017-07-01','C:\\Homeworks\\20181\\Hedva1\\1st Grade 1\\aa.txt','1st Grade 1','Hedva1','20181'),('2','Algebra1_Homework2','2017-07-07','C:\\Asis\\Homeworks\\20172\\Algebra1\\1st Grade 1\\Algebra1_Homework2.txt','1st Grade 1','Algebra1','20172'),('3','Hebrew_Homework1','2017-07-05','C:\\Asis\\Homeworks\\20172\\Hebrew\\1st Grade 1\\Hebrew_Homework1.txt','1st Grade 1','Hebrew','20172'),('4','Hebrew_Homework2','2017-07-12','C:\\Asis\\Homeworks\\20172\\Hebrew\\1st Grade 1\\Hebrew_Homework2.txt','1st Grade 1','Hebrew','20172'),('5','Hedva1_Homework1','2017-06-28','C:\\Asis\\Homeworks\\20172\\Hedva1\\1st Grade 1\\Hedva1_Homework1.txt','1st Grade 1','Hedva1','20172'),('6','BMusic_Homework1','2017-06-30','C:\\Asis\\Homeworks\\20172\\Basic Music\\1st Grade 1\\BMusic_Homework1.txt','1st Grade 1','Basic Music','20172'),('7','Spanish_Homework1','2017-07-14','C:\\Asis\\Homeworks\\20172\\Spanish\\1st Grade 1\\Spanish_Homework1.txt','1st Grade 1','Spanish','20172'),('8','Sport1_Homework1','2017-07-14','C:\\Asis\\Homeworks\\20172\\Sport1\\1st Grade 1\\Sport1_Homework1.txt','1st Grade 1','Sport1','20172'),('9','English_Homework1','2017-06-28','C:\\Asis\\Homeworks\\20172\\English\\1st Grade 1\\English_Homework1.txt','1st Grade 1','English','20172');
/*!40000 ALTER TABLE `homeworks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_student`
--

DROP TABLE IF EXISTS `parent_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_student` (
  `PID` varchar(45) NOT NULL,
  `SID` varchar(45) NOT NULL,
  `Blocked` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PID`,`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_student`
--

LOCK TABLES `parent_student` WRITE;
/*!40000 ALTER TABLE `parent_student` DISABLE KEYS */;
INSERT INTO `parent_student` VALUES ('666000001','555000002','No'),('666000002','555000005','No'),('666000002','555000006','No'),('666000003','555000010','No'),('666000003','555000011','No'),('666000004','555000003','No'),('666000005','555000007','No'),('666000005','555000008','No'),('666000005','555000009','No'),('666000006','555000012','No'),('666000006','555000013','No'),('666000007','555000015','No'),('666000007','555000020','No'),('666000008','555000014','No'),('666000008','555000019','No'),('666000009','555000004','No'),('666000010','555000016','No'),('666000011','555000018','No'),('666000012','555000001','No'),('666000013','555000017','No');
/*!40000 ALTER TABLE `parent_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regrequests`
--

DROP TABLE IF EXISTS `regrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regrequests` (
  `ReqID` varchar(45) NOT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  `ClassName` varchar(45) NOT NULL,
  `ReqType` varchar(45) NOT NULL,
  `StudentID` varchar(45) NOT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `SemID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ReqID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regrequests`
--

LOCK TABLES `regrequests` WRITE;
/*!40000 ALTER TABLE `regrequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `regrequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `ReportID` varchar(45) NOT NULL,
  `ReportType` varchar(45) NOT NULL,
  PRIMARY KEY (`ReportID`),
  UNIQUE KEY `ReportID_UNIQUE` (`ReportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `RID` varchar(45) NOT NULL,
  `RName` varchar(45) NOT NULL,
  PRIMARY KEY (`RID`),
  UNIQUE KEY `RID_UNIQUE` (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('1','System Manager'),('2','School Director'),('3','Secretary'),('4','Teacher'),('5','Student'),('6','Parent');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semesters`
--

DROP TABLE IF EXISTS `semesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semesters` (
  `SemID` varchar(45) NOT NULL,
  `SemesterNum` varchar(45) NOT NULL,
  `StartingDate` varchar(45) NOT NULL,
  `EndingDate` varchar(45) NOT NULL,
  `Year` varchar(45) NOT NULL,
  `Current` varchar(45) NOT NULL,
  PRIMARY KEY (`SemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semesters`
--

LOCK TABLES `semesters` WRITE;
/*!40000 ALTER TABLE `semesters` DISABLE KEYS */;
INSERT INTO `semesters` VALUES ('20172','2. Spring','2017-03-26','2017-07-06','2017','0'),('20181','1. Winter','2017-10-29','2018-03-01','2018','1');
/*!40000 ALTER TABLE `semesters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_evaluation_forms`
--

DROP TABLE IF EXISTS `student_evaluation_forms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_evaluation_forms` (
  `UID` varchar(45) NOT NULL,
  `EFID` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`,`EFID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_evaluation_forms`
--

LOCK TABLES `student_evaluation_forms` WRITE;
/*!40000 ALTER TABLE `student_evaluation_forms` DISABLE KEYS */;
INSERT INTO `student_evaluation_forms` VALUES ('555000001','5'),('555000001','6'),('555000001','8'),('555000001','9'),('555000002','2'),('555000002','3'),('555000002','4'),('555000003','1'),('555000003','7');
/*!40000 ALTER TABLE `student_evaluation_forms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grade_sheets`
--

DROP TABLE IF EXISTS `student_grade_sheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_grade_sheets` (
  `UID` varchar(45) NOT NULL,
  `GSID` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`,`GSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grade_sheets`
--

LOCK TABLES `student_grade_sheets` WRITE;
/*!40000 ALTER TABLE `student_grade_sheets` DISABLE KEYS */;
INSERT INTO `student_grade_sheets` VALUES ('555000001','5'),('555000001','6'),('555000001','8'),('555000001','9'),('555000002','2'),('555000002','3'),('555000002','4'),('555000003','1'),('555000003','7');
/*!40000 ALTER TABLE `student_grade_sheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_in_a_class`
--

DROP TABLE IF EXISTS `student_in_a_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_in_a_class` (
  `UID` varchar(45) NOT NULL,
  `ClassID` varchar(45) NOT NULL,
  `SemID` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`,`ClassID`,`SemID`),
  KEY `UID2_idx` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_in_a_class`
--

LOCK TABLES `student_in_a_class` WRITE;
/*!40000 ALTER TABLE `student_in_a_class` DISABLE KEYS */;
INSERT INTO `student_in_a_class` VALUES ('555000001','1st Grade 1','20172'),('555000001','1st Grade 1','20181'),('555000002','1st Grade 1','20172'),('555000002','1st Grade 1','20181'),('555000003','1st Grade 1','20172'),('555000003','1st Grade 1','20181'),('555000004','1st Grade 1','20172'),('555000004','1st Grade 1','20181'),('555000005','1st Grade 1','20172'),('555000007','1st Grade 1','20172'),('555000008','1st Grade 1','20172'),('555000012','1st Grade 1','20172'),('555000015','1st Grade 1','20172'),('555000017','1st Grade 1','20172'),('555000019','1st Grade 1','20172'),('555000020','1st Grade 1','20172');
/*!40000 ALTER TABLE `student_in_a_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_in_a_course`
--

DROP TABLE IF EXISTS `student_in_a_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_in_a_course` (
  `UID` varchar(45) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `Grade` varchar(45) DEFAULT NULL,
  `SemID` varchar(45) NOT NULL,
  `ClassID` varchar(45) NOT NULL,
  `ExceptionalReg` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UID`,`CourseName`,`ClassID`,`SemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_in_a_course`
--

LOCK TABLES `student_in_a_course` WRITE;
/*!40000 ALTER TABLE `student_in_a_course` DISABLE KEYS */;
INSERT INTO `student_in_a_course` VALUES ('555000001','Algebra1',NULL,'20172','1st Grade 1','0'),('555000001','Algebra1',NULL,'20181','1st Grade 1','0'),('555000001','Basic Music',NULL,'20172','1st Grade 1','0'),('555000001','English',NULL,'20172','1st Grade 1','0'),('555000001','English',NULL,'20181','1st Grade 1','0'),('555000001','Hebrew',NULL,'20172','1st Grade 1','0'),('555000001','Hebrew',NULL,'20181','1st Grade 1','0'),('555000001','Hedva1',NULL,'20172','1st Grade 1','0'),('555000001','Hedva1','45','20181','1st Grade 1','0'),('555000001','Russian',NULL,'20172','1st Grade 1','0'),('555000001','Spanish',NULL,'20172','1st Grade 1','0'),('555000001','Spanish',NULL,'20181','1st Grade 1','0'),('555000001','Sport1',NULL,'20172','1st Grade 1','0'),('555000002','Algebra1',NULL,'20172','1st Grade 1','0'),('555000002','Algebra1',NULL,'20181','1st Grade 1','0'),('555000002','Basic Music',NULL,'20172','1st Grade 1','0'),('555000002','English',NULL,'20172','1st Grade 1','0'),('555000002','English',NULL,'20181','1st Grade 1','0'),('555000002','Hebrew',NULL,'20172','1st Grade 1','0'),('555000002','Hebrew',NULL,'20181','1st Grade 1','0'),('555000002','Hedva1',NULL,'20172','1st Grade 1','0'),('555000002','Hedva1',NULL,'20181','1st Grade 1','0'),('555000002','Russian',NULL,'20172','1st Grade 1','0'),('555000002','Spanish',NULL,'20172','1st Grade 1','0'),('555000002','Spanish',NULL,'20181','1st Grade 1','0'),('555000002','Sport1',NULL,'20172','1st Grade 1','0'),('555000003','Algebra1',NULL,'20172','1st Grade 1','0'),('555000003','Algebra1',NULL,'20181','1st Grade 1','0'),('555000003','Basic Music',NULL,'20172','1st Grade 1','0'),('555000003','English',NULL,'20172','1st Grade 1','0'),('555000003','English',NULL,'20181','1st Grade 1','0'),('555000003','Hebrew',NULL,'20172','1st Grade 1','0'),('555000003','Hebrew',NULL,'20181','1st Grade 1','0'),('555000003','Hedva1',NULL,'20172','1st Grade 1','0'),('555000003','Hedva1',NULL,'20181','1st Grade 1','0'),('555000003','Russian',NULL,'20172','1st Grade 1','0'),('555000003','Spanish',NULL,'20172','1st Grade 1','0'),('555000003','Spanish',NULL,'20181','1st Grade 1','0'),('555000003','Sport1',NULL,'20172','1st Grade 1','0'),('555000004','Algebra1',NULL,'20172','1st Grade 1','0'),('555000004','Algebra1',NULL,'20181','1st Grade 1','0'),('555000004','Basic Music',NULL,'20172','1st Grade 1','0'),('555000004','English',NULL,'20172','1st Grade 1','0'),('555000004','English',NULL,'20181','1st Grade 1','0'),('555000004','Hebrew',NULL,'20172','1st Grade 1','0'),('555000004','Hebrew',NULL,'20181','1st Grade 1','0'),('555000004','Hedva1',NULL,'20172','1st Grade 1','0'),('555000004','Hedva1',NULL,'20181','1st Grade 1','0'),('555000004','Russian',NULL,'20172','1st Grade 1','0'),('555000004','Spanish',NULL,'20172','1st Grade 1','0'),('555000004','Spanish',NULL,'20181','1st Grade 1','0'),('555000004','Sport1',NULL,'20172','1st Grade 1','0'),('555000005','Algebra1',NULL,'20172','1st Grade 1','0'),('555000005','Basic Music',NULL,'20172','1st Grade 1','0'),('555000005','English',NULL,'20172','1st Grade 1','0'),('555000005','Hebrew',NULL,'20172','1st Grade 1','0'),('555000005','Hedva1',NULL,'20172','1st Grade 1','0'),('555000005','Russian',NULL,'20172','1st Grade 1','0'),('555000005','Spanish',NULL,'20172','1st Grade 1','0'),('555000005','Sport1',NULL,'20172','1st Grade 1','0'),('555000007','Algebra1',NULL,'20172','1st Grade 1','0'),('555000007','Basic Music',NULL,'20172','1st Grade 1','0'),('555000007','English',NULL,'20172','1st Grade 1','0'),('555000007','Hebrew',NULL,'20172','1st Grade 1','0'),('555000007','Hedva1',NULL,'20172','1st Grade 1','0'),('555000007','Russian',NULL,'20172','1st Grade 1','0'),('555000007','Spanish',NULL,'20172','1st Grade 1','0'),('555000007','Sport1',NULL,'20172','1st Grade 1','0'),('555000008','Algebra1',NULL,'20172','1st Grade 1','0'),('555000008','Basic Music',NULL,'20172','1st Grade 1','0'),('555000008','English',NULL,'20172','1st Grade 1','0'),('555000008','Hebrew',NULL,'20172','1st Grade 1','0'),('555000008','Hedva1',NULL,'20172','1st Grade 1','0'),('555000008','Russian',NULL,'20172','1st Grade 1','0'),('555000008','Spanish',NULL,'20172','1st Grade 1','0'),('555000008','Sport1',NULL,'20172','1st Grade 1','0'),('555000012','Algebra1',NULL,'20172','1st Grade 1','0'),('555000012','Basic Music',NULL,'20172','1st Grade 1','0'),('555000012','English',NULL,'20172','1st Grade 1','0'),('555000012','Hebrew',NULL,'20172','1st Grade 1','0'),('555000012','Hedva1',NULL,'20172','1st Grade 1','0'),('555000012','Russian',NULL,'20172','1st Grade 1','0'),('555000012','Spanish',NULL,'20172','1st Grade 1','0'),('555000012','Sport1',NULL,'20172','1st Grade 1','0'),('555000015','Algebra1',NULL,'20172','1st Grade 1','0'),('555000015','Basic Music',NULL,'20172','1st Grade 1','0'),('555000015','English',NULL,'20172','1st Grade 1','0'),('555000015','Hebrew',NULL,'20172','1st Grade 1','0'),('555000015','Hedva1',NULL,'20172','1st Grade 1','0'),('555000015','Russian',NULL,'20172','1st Grade 1','0'),('555000015','Spanish',NULL,'20172','1st Grade 1','0'),('555000015','Sport1',NULL,'20172','1st Grade 1','0'),('555000017','Algebra1',NULL,'20172','1st Grade 1','0'),('555000017','Basic Music',NULL,'20172','1st Grade 1','0'),('555000017','English',NULL,'20172','1st Grade 1','0'),('555000017','Hebrew',NULL,'20172','1st Grade 1','0'),('555000017','Hedva1',NULL,'20172','1st Grade 1','0'),('555000017','Russian',NULL,'20172','1st Grade 1','0'),('555000017','Spanish',NULL,'20172','1st Grade 1','0'),('555000017','Sport1',NULL,'20172','1st Grade 1','0'),('555000019','Algebra1',NULL,'20172','1st Grade 1','0'),('555000019','Basic Music',NULL,'20172','1st Grade 1','0'),('555000019','English',NULL,'20172','1st Grade 1','0'),('555000019','Hebrew',NULL,'20172','1st Grade 1','0'),('555000019','Hedva1',NULL,'20172','1st Grade 1','0'),('555000019','Russian',NULL,'20172','1st Grade 1','0'),('555000019','Spanish',NULL,'20172','1st Grade 1','0'),('555000019','Sport1',NULL,'20172','1st Grade 1','0'),('555000020','Algebra1',NULL,'20172','1st Grade 1','0'),('555000020','Basic Music',NULL,'20172','1st Grade 1','0'),('555000020','English',NULL,'20172','1st Grade 1','0'),('555000020','Hebrew',NULL,'20172','1st Grade 1','0'),('555000020','Hedva1',NULL,'20172','1st Grade 1','0'),('555000020','Russian',NULL,'20172','1st Grade 1','0'),('555000020','Spanish',NULL,'20172','1st Grade 1','0'),('555000020','Sport1',NULL,'20172','1st Grade 1','0');
/*!40000 ALTER TABLE `student_in_a_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_submissions`
--

DROP TABLE IF EXISTS `student_submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_submissions` (
  `UID` varchar(45) NOT NULL,
  `SID` varchar(45) NOT NULL,
  `Grade` varchar(45) DEFAULT NULL,
  `Checked` varchar(45) DEFAULT 'No',
  PRIMARY KEY (`UID`,`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_submissions`
--

LOCK TABLES `student_submissions` WRITE;
/*!40000 ALTER TABLE `student_submissions` DISABLE KEYS */;
INSERT INTO `student_submissions` VALUES ('555000001','1','90','Yes'),('555000001','10',NULL,'No'),('555000001','11','0','Yes'),('555000001','2','81','Yes'),('555000001','8',NULL,'No'),('555000001','9','90','Yes'),('555000002','3','92','Yes'),('555000002','4','96','Yes'),('555000002','5','87','Yes'),('555000003','6','95','Yes'),('555000003','7','94','Yes');
/*!40000 ALTER TABLE `student_submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submissions` (
  `SID` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `SubmissionDate` varchar(45) DEFAULT NULL,
  `Attachments` varchar(200) DEFAULT NULL,
  `HWName` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  `Semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES ('1','001_English_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\English\\001_English_Submission1.txt','English_Homework1','English','20172'),('10','555000001-qqq','03/07/2017','C:\\Submissions\\20181\\English\\555000001-qqq.txt','test2','English','20181'),('11','-','-','-','aa','Hedva1','20181'),('2','001_Russian_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Russian\\001_Russian_Submission1.txt','Russian_Homework1','Russian','20172'),('3','002_Hedva1_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Hedva1\\002_Hedva1_Submission1.txt','Hedva1_Homework1','Hedva1','20172'),('4','002_Spanish_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Spanish\\002_Spanish_Submission1.txt','Spanish_Homework1','Spanish','20172'),('5','002_BMusic_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Basic Music\\002_BMusic_Submission1.txt','BMusic_Homework1','Basic Music','20172'),('6','003_Algebra1_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Algebra1\\003_Algebra1_Submission1.txt','Algebra1_Homework1','Algebra1','20172'),('7','003_Sport1_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Sport1\\003_Sport1_Submission1.txt','Sport1_Homework1','Sport1','20172'),('8','001_Algebra1_Submission1','24/06/2017','C:\\Asis\\Submissions\\20172\\Algebra1\\001_Algebra1_Submission1.txt','Algebra1_Homework1','Algebra1','20172'),('9','555000001-subtest','03/07/2017','C:\\Submissions\\20181\\Hedva1\\555000001-subtest.txt','test','Hedva1','20181');
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_hws`
--

DROP TABLE IF EXISTS `teacher_hws`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_hws` (
  `UID` varchar(45) NOT NULL,
  `HWID` varchar(45) NOT NULL,
  PRIMARY KEY (`UID`,`HWID`),
  KEY `RID4_idx` (`UID`),
  KEY `HWID_idx` (`HWID`),
  CONSTRAINT `HWID` FOREIGN KEY (`HWID`) REFERENCES `homeworks` (`HWID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `UID4` FOREIGN KEY (`UID`) REFERENCES `users` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_hws`
--

LOCK TABLES `teacher_hws` WRITE;
/*!40000 ALTER TABLE `teacher_hws` DISABLE KEYS */;
INSERT INTO `teacher_hws` VALUES ('444000001','1'),('444000001','11'),('444000001','12'),('444000001','13'),('444000001','2'),('444000001','3'),('444000001','4'),('444000004','5'),('444000005','6'),('444000006','7'),('444000008','10'),('444000008','9'),('444000009','8');
/*!40000 ALTER TABLE `teacher_hws` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_in_class_course`
--

DROP TABLE IF EXISTS `teacher_in_class_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_in_class_course` (
  `TID` varchar(45) DEFAULT NULL,
  `ClassID` varchar(45) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `SemID` varchar(45) NOT NULL,
  `TeachingUnit` varchar(45) NOT NULL,
  `WeeklyHours` varchar(45) NOT NULL,
  PRIMARY KEY (`ClassID`,`CourseName`,`SemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_in_class_course`
--

LOCK TABLES `teacher_in_class_course` WRITE;
/*!40000 ALTER TABLE `teacher_in_class_course` DISABLE KEYS */;
INSERT INTO `teacher_in_class_course` VALUES ('444000004','1st Grade 1','Algebra1','20172','Math (02)','5'),('444000004','1st Grade 1','Algebra1','20181','Math (02)','5'),('444000005','1st Grade 1','Basic Music','20172','Music Science (05)','4'),('444000001','1st Grade 1','English','20172','Languages (03)','4'),('444000001','1st Grade 1','English','20181','Languages (03)','4'),('444000006','1st Grade 1','Hebrew','20172','Languages (03)','4'),('444000006','1st Grade 1','Hebrew','20181','Languages (03)','4'),('444000001','1st Grade 1','Hedva1','20172','Math (02)','6'),('444000001','1st Grade 1','Hedva1','20181','Math (02)','6'),('444000008','1st Grade 1','Russian','20172','Languages (03)','4'),('444000006','1st Grade 1','Spanish','20172','Languages (03)','4'),('0','1st Grade 1','Spanish','20181','Languages (03)','4'),('444000009','1st Grade 1','Sport1','20172','Sports (04)','2'),('444000001','2nd Grade 1','Algebra2','20181','Math (02)','5'),('444000001','2nd Grade 1','Russian','20172','Languages (03)','4'),('444000001','2nd Grade 1','Russian','20181','Languages (03)','4'),('0','2nd Grade 1','Sport2','20172','Sports (04)','2');
/*!40000 ALTER TABLE `teacher_in_class_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_tunit`
--

DROP TABLE IF EXISTS `teacher_tunit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_tunit` (
  `TID` varchar(45) NOT NULL,
  `TeachingUnit` varchar(45) NOT NULL,
  PRIMARY KEY (`TID`,`TeachingUnit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_tunit`
--

LOCK TABLES `teacher_tunit` WRITE;
/*!40000 ALTER TABLE `teacher_tunit` DISABLE KEYS */;
INSERT INTO `teacher_tunit` VALUES ('444000001','Languages (03)'),('444000001','Math (02)'),('444000002','Sports (04)'),('444000003','Music Science (05)'),('444000004','Math (02)'),('444000005','Math (02)'),('444000005','Music Science (05)'),('444000006','Languages (03)'),('444000007','Math (02)'),('444000008','Languages (03)'),('444000009','Sports (04)'),('444000010','Math (02)');
/*!40000 ALTER TABLE `teacher_tunit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `UID` varchar(45) NOT NULL,
  `MaxHours` varchar(45) NOT NULL,
  `ActualHours` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `UID_idx` (`UID`),
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `users` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES ('444000001','30','19'),('444000002','30','0'),('444000003','30','0'),('444000004','30','5'),('444000005','30','0'),('444000006','30','4'),('444000007','30','0'),('444000008','30','0'),('444000009','30','0'),('444000010','30','0');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_units`
--

DROP TABLE IF EXISTS `teaching_units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_units` (
  `UnitName` varchar(45) NOT NULL,
  `UnitNum` varchar(45) NOT NULL,
  PRIMARY KEY (`UnitName`),
  UNIQUE KEY `UnitNum_UNIQUE` (`UnitNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_units`
--

LOCK TABLES `teaching_units` WRITE;
/*!40000 ALTER TABLE `teaching_units` DISABLE KEYS */;
INSERT INTO `teaching_units` VALUES ('Math','02'),('Languages','03'),('Sports','04'),('Music Science','05');
/*!40000 ALTER TABLE `teaching_units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UID` varchar(45) NOT NULL,
  `RID` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `BirthDate` date NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Connected` tinyint(1) NOT NULL,
  `IP` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UID_UNIQUE` (`UID`),
  UNIQUE KEY `IP_UNIQUE` (`IP`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `RID1_idx` (`RID`),
  CONSTRAINT `RID1` FOREIGN KEY (`RID`) REFERENCES `roles` (`RID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('111000001','1','Noah','Assenheim','1973-05-15','101',0,'10.0.0.1','0541000001','Akko','m.siah91@gmail.com'),('222000001','2','Isabella','Johnson','1968-03-12','201',0,'10.0.0.2','0542000001','Akko','eliran3790@gmail.com'),('333000001','3','Sophia','Brown','1982-02-01','301',0,'10.0.0.3','0543000001','Akko','shacharassen3667@gmail.com'),('333000002','3','Abigail','Smith','1981-08-11','302',0,'10.0.0.4','0543000002','Nahareya','nomail4@nomail.com'),('444000001','4','Evelyn','Jackson','1978-06-25','401',0,'10.0.0.5','0544000001','Nahareya','nomail5@nomail.com'),('444000002','4','Emily','Clark','1977-07-07','402',0,'10.0.0.6','0544000002','Haifa','nomail6@nomail.com'),('444000003','4','Mila','Hall','1974-04-23','403',0,'10.0.0.7','0544000003','Akko','nomail7@nomail.com'),('444000004','4','Sofia','Wright','1965-02-03','404',0,'10.0.0.8','0544000004','Haifa','nomail8@nomail.com'),('444000005','4','Scarlett','Johnson','1984-11-22','405',0,'10.0.0.9','0544000005','A\'fula','nomail9@nomail.com'),('444000006','4','Mia','Carter','1986-05-01','406',0,'10.0.0.10','0544000006','A\'fula','nomail10@nomail.com'),('444000007','4','Chloe','Flores','1989-01-05','407',0,'10.0.0.11','0544000007','Akko','nomail11@nomail.com'),('444000008','4','Layla','Coleman','1985-09-01','408',0,'10.0.0.12','0544000008','Nahareya','nomail12@nomail.com'),('444000009','4','Grace','Butler','1985-09-18','409',0,'10.0.0.13','0544000009','Karmeil','nomail13@nomail.com'),('444000010','4','Zoey','Foster','1979-11-12','410',0,'10.0.0.14','0544000010','Karmeil','nomail14@nomail.com'),('555000001','5','Elizabeth','Perry','2010-01-05','501',0,'10.0.0.15','0545000001','Karmeil','nomail15@nomail.com'),('555000002','5','Shachar','Assenheim','2010-06-01','502',0,'10.0.0.16','0545000002','Akko','nomail16@nomail.com'),('555000003','5','Adiel','Turgeman','2010-03-03','503',0,'10.0.0.17','0545000003','Nahareya','nomail17@nomail.com'),('555000004','5','Levi','Cole','2010-02-13','504',0,'10.0.0.18','0545000004','A\'fula','nomail18@nomail.com'),('555000005','5','Ryan','Abergel','2010-12-19','505',0,'10.0.0.19','0545000005','Akko','nomail19@nomail.com'),('555000006','5','Julian','Abergel','2009-12-13','506',0,'10.0.0.20','0545000006','Akko','nomail20@nomail.com'),('555000007','5','Lillian','Owens','2010-04-23','507',0,'10.0.0.21','0545000007','Haifa','nomail21@nomail.com'),('555000008','5','Adam','Owens','2010-04-23','508',0,'10.0.0.22','0545000008','Haifa','nomail22@nomail.com'),('555000009','5','Hazel','Owens','2009-08-13','509',0,'10.0.0.23','0545000009','Haifa','nomail23@nomail.com'),('555000010','5','Caleb','Sullivan','2009-04-11','510',0,'10.0.0.24','0545000010','Akko','nomail24@nomail.com'),('555000011','5','John','Sullivan','2009-04-11','511',0,'10.0.0.25','0545000011','Akko','nomail25@nomail.com'),('555000012','5','Andrew','Fisher','2010-09-01','512',0,'10.0.0.26','0545000012','Haifa','nomail26@nomail.com'),('555000013','5','Aurora','Fisher','2009-10-15','513',0,'10.0.0.27','0545000013','Haifa','nomail27@nomail.com'),('555000014','5','Anna','Webb','2009-01-13','514',0,'10.0.0.28','0545000014','Akko','nomail28@nomail.com'),('555000015','5','Anna','Murray','2010-03-03','515',0,'10.0.0.29','0545000015','Nahareya','nomail29@nomail.com'),('555000016','5','Elizabeth','Dixon','2009-07-07','516',0,'10.0.0.30','0545000016','Karmeil','nomail30@nomail.com'),('555000017','5','Zoey','Hicks','2010-07-12','517',0,'10.0.0.31','0545000017','Nahareya','nomail31@nomail.com'),('555000018','5','Adam','Boyd','2009-06-26','518',0,'10.0.0.32','0545000018','Akko','nomail32@nomail.com'),('555000019','5','Andrew','Webb','2010-05-15','519',0,'10.0.0.33','0545000019','Akko','nomail33@nomail.com'),('555000020','5','Mia','Murray','2010-03-03','520',0,'10.0.0.34','0545000020','Nahareya','nomail34@nomail.com'),('666000001','6','Noah','Assenheim','1984-05-06','601',0,'10.0.0.35','0546000001','Akko','nomail35@nomail.com'),('666000002','6','Eliran','Abergel','1982-02-01','602',0,'10.0.0.36','0546000002','Akko','nomail36@nomail.com'),('666000003','6','Sophia','Sullivan','1984-03-09','603',0,'10.0.0.37','0546000003','Akko','nomail37@nomail.com'),('666000004','6','Isabela','Turgeman','1981-01-22','604',0,'10.0.0.38','0546000004','Nahareya','nomail38@nomail.com'),('666000005','6','Vivian','Owens','1980-05-17','605',0,'10.0.0.39','0546000005','Haifa','nomail39@nomail.com'),('666000006','6','Levi','Fisher','1975-09-28','606',0,'10.0.0.40','0546000006','Haifa','nomail40@nomail.com'),('666000007','6','Etay','Murray','1972-10-31','607',0,'10.0.0.41','0546000007','Nahareya','nomail41@nomail.com'),('666000008','6','Shay','Webb','1976-03-13','608',0,'10.0.0.42','0546000008','Akko','nomail42@nomail.com'),('666000009','6','Sagi','Cole','1976-02-29','609',0,'10.0.0.43','0546000009','A\'fula','nomail43@nomail.com'),('666000010','6','Enbar','Dixon','1977-01-12','610',0,'10.0.0.44','0546000010','Karmeil','nomail44@nomail.com'),('666000011','6','Maya','Boyd','1974-01-01','611',0,'10.0.0.45','0546000011','Akko','nomail45@nomail.com'),('666000012','6','Yael','Perry','1986-06-02','612',0,'10.0.0.46','0546000012','Karmeil','nomail46@nomail.com'),('666000013','6','James','Hicks','1982-05-15','613',0,'10.0.0.47','0546000013','Nahareya','nomail47@nomail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-03 20:09:50
