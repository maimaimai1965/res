/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     30.04.2016 17:46:33                          */
/*==============================================================*/


drop index I_CERIFICATE__PERSON_ID;

drop table CERTIFICATE;

drop index I_COURSE__PERSON_ID;

drop table COURSE;

drop index I_EDUCATION__PERSON_ID;

drop table EDUCATION;

drop index I_LANG_LEVEL__PERSON;

drop table LANG_LEVEL;

drop index I_PERSON_HOBBY__PERSON;

drop table PERSON_HOBBY;

drop index I_PRACTIC__PERSON_ID;

drop table PRACTIC;

drop index I_SKILL__PERSON_ID;

drop table SKILL;

drop table SKILL_TYPE;

drop table PERSON;

drop table HOBBY;

drop table LANG;



drop sequence SQ_CERTIFICATE;

drop sequence SQ_COURSE;

drop sequence SQ_EDUCATION;

drop sequence SQ_HOBBY;

drop sequence SQ_LANG_LEVEL;

drop sequence SQ_PERSON;

drop sequence SQ_PERSON_HOBBY;

drop sequence SQ_PRACTIC;

drop sequence SQ_SKILL;



create sequence SQ_CERTIFICATE;

create sequence SQ_COURSE;

create sequence SQ_EDUCATION;

create sequence SQ_HOBBY;

create sequence SQ_LANG_LEVEL;

create sequence SQ_PERSON;

create sequence SQ_PERSON_HOBBY;

create sequence SQ_PRACTIC;

create sequence SQ_SKILL;

/*==============================================================*/
/* Table: CERTIFICATE                                           */
/*==============================================================*/
create table CERTIFICATE (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   NAME                 VARCHAR(100)         not null,
   LARGE_IMG            VARCHAR(255)         null,
   SMALL_IMG            VARCHAR(255)         null,
   constraint PK_CERTIFICATE primary key (ID)
);

/*==============================================================*/
/* Index: I_CERIFICATE__PERSON_ID                               */
/*==============================================================*/
create  index I_CERIFICATE__PERSON_ID on CERTIFICATE (
PERSON_ID
);

/*==============================================================*/
/* Table: COURSE                                                */
/*==============================================================*/
create table COURSE (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   NAME                 VARCHAR(100)         not null,
   SCHOOL               VARCHAR(100)         null,
   DATE_FINISH          DATE                 null,
   constraint PK_COURSE primary key (ID)
);

/*==============================================================*/
/* Index: I_COURSE__PERSON_ID                                   */
/*==============================================================*/
create  index I_COURSE__PERSON_ID on COURSE (
PERSON_ID
);

/*==============================================================*/
/* Table: EDUCATION                                             */
/*==============================================================*/
create table EDUCATION (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   SUMMARY              VARCHAR(100)         not null,
   ORGANIZATION         TEXT                 null,
   DEPARTMENT           VARCHAR(100)         null,
   DATE_START           DATE                 not null,
   DATE_FINIH           DATE                 null,
   constraint PK_EDUCATION primary key (ID)
);

/*==============================================================*/
/* Index: I_EDUCATION__PERSON_ID                                */
/*==============================================================*/
create  index I_EDUCATION__PERSON_ID on EDUCATION (
PERSON_ID
);

/*==============================================================*/
/* Table: HOBBY                                                 */
/*==============================================================*/
create table HOBBY (
   ID                   INT4                 not null,
   NAME                 VARCHAR(50)          not null,
   IMG                  VARCHAR(256)         null,
   constraint PK_HOBBY primary key (ID)
);

/*==============================================================*/
/* Table: LANG                                                  */
/*==============================================================*/
create table LANG (
   ID                   INT4                 not null,
   NAME                 VARCHAR(30)          not null,
   constraint PK_LANG primary key (ID)
);

/*==============================================================*/
/* Table: LANG_LEVEL                                            */
/*==============================================================*/
create table LANG_LEVEL (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   LANG_ID              INT4                 not null,
   TYPE                 CHAR                 not null,
   LEVEL                CHAR                 not null,
   constraint PK_LANG_LEVEL primary key (ID),
   constraint AK_LANG_LEVEL unique (PERSON_ID, LANG_ID)
);

/*==============================================================*/
/* Index: I_LANG_LEVEL__PERSON                                  */
/*==============================================================*/
create  index I_LANG_LEVEL__PERSON on LANG_LEVEL (
PERSON_ID
);

/*==============================================================*/
/* Table: PERSON                                                */
/*==============================================================*/
create table PERSON (
   ID                   BIGINT                not null,
   UID                  VARCHAR(100)         not null,
   F                    VARCHAR(50)          not null,
   I                    VARCHAR(50)          not null,
   O                    VARCHAR(50)          null,
   BIRTH_DAY            DATE                 null,
   PASSWORD             VARCHAR(60)          not null,
   COUNTRY              VARCHAR(60)          null,
   CITY                 VARCHAR(100)         null,
   PHONE                VARCHAR(30)          null,
   EMAIL                VARCHAR(100)         not null,
   CAREER_OBJECTIVE     TEXT                 null,
   QUALIFICATION        TEXT                 null,
   SMALL_PHOTO          VARCHAR(255)         null,
   LARGE_PHOTO          VARCHAR(255)         null,
   SKYPE                VARCHAR(255)         null,
   VKONTAKTE            VARCHAR(255)         null,
   FACEBOOK             VARCHAR(255)         null,
   LINKEDIN             VARCHAR(255)         null,
   GITHUB               VARCHAR(255)         null,
   STACKOVERFLOW        VARCHAR(255)         null,
   INFO                 TEXT                 null,
   COMPLETED            BOOL                 not null,
   INSERTED             DATE                 not null,
   DELETED              DATE                 null,
   constraint PK_PERSON primary key (ID),
   constraint AK_AK_PERSON_UID_PERSON unique (UID),
   constraint AK_AK_PERSON_PHONE_PERSON unique (PHONE),
   constraint AK_AK_PERSON_EMAIL_PERSON unique (EMAIL)
);

/*==============================================================*/
/* Table: PERSON_HOBBY                                          */
/*==============================================================*/
create table PERSON_HOBBY (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   HOBBY_ID             INT4                 not null,
   constraint PK_PERSON_HOBBY primary key (ID),
   constraint AK_PERSON_HOBBY unique (PERSON_ID, HOBBY_ID)
);

/*==============================================================*/
/* Index: I_PERSON_HOBBY__PERSON                                */
/*==============================================================*/
create  index I_PERSON_HOBBY__PERSON on PERSON_HOBBY (
PERSON_ID
);

/*==============================================================*/
/* Table: PRACTIC                                               */
/*==============================================================*/
create table PRACTIC (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   COMPANY              TEXT                 not null,
   "POSITION"           VARCHAR(100)         not null,
   DATE_START           DATE                 not null,
   DATE_FINISH          DATE                 null,
   INFO                 TEXT                 not null,
   DEMO                 VARCHAR(255)         null,
   SRC                  VARCHAR(255)         null,
   constraint PK_PRACTIC primary key (ID)
);

/*==============================================================*/
/* Index: I_PRACTIC__PERSON_ID                                  */
/*==============================================================*/
create  index I_PRACTIC__PERSON_ID on PRACTIC (
PERSON_ID
);

/*==============================================================*/
/* Table: SKILL                                                 */
/*==============================================================*/
create table SKILL (
   ID                   BIGINT                not null,
   PERSON_ID            BIGINT                not null,
   SKILL_TYPE_ID        INT4                 not null,
   INFO                 TEXT                 not null,
   constraint PK_SKILL primary key (ID)
);

/*==============================================================*/
/* Index: I_SKILL__PERSON_ID                                    */
/*==============================================================*/
create  index I_SKILL__PERSON_ID on SKILL (
PERSON_ID
);

/*==============================================================*/
/* Table: SKILL_TYPE                                            */
/*==============================================================*/
create table SKILL_TYPE (
   ID                   INT4                 not null,
   NAME                 VARCHAR(30)          not null,
   constraint PK_SKILL_TYPE primary key (ID)
);

alter table CERTIFICATE
   add constraint FK_CERTIFICATE__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table COURSE
   add constraint FK_COURSE__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table EDUCATION
   add constraint FK_EDUCATION__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table LANG_LEVEL
   add constraint FK_LANG_LEV__LANG foreign key (LANG_ID)
      references LANG (ID)
      on delete restrict on update restrict;

alter table LANG_LEVEL
   add constraint FK_LANG_LEV__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table PERSON_HOBBY
   add constraint FK_PERSON_HOBBY_PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table PERSON_HOBBY
   add constraint FK_PERSON_HOBBY__HOBBY foreign key (HOBBY_ID)
      references HOBBY (ID)
      on delete restrict on update restrict;

alter table PRACTIC
   add constraint FK_PRACTIC__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

alter table SKILL
   add constraint FK_SKILL__SKILL_TYPE foreign key (SKILL_TYPE_ID)
      references SKILL_TYPE (ID)
      on delete restrict on update restrict;

alter table SKILL
   add constraint FK_SKILL__PERSON foreign key (PERSON_ID)
      references PERSON (ID)
      on delete cascade on update cascade;

