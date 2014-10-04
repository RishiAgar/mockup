# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organization (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  logo                      longblob,
  constraint pk_organization primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_role primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  user_name                 varchar(255),
  email                     varchar(255),
  is_admin                  tinyint(1) default 0,
  organization_id           bigint,
  sha_password              varbinary(64) not null,
  attempts                  tinyint,
  picture                   longblob,
  constraint pk_user primary key (id))
;

create table user_browser (
  id                        bigint,
  user_id                   bigint,
  browser                   varchar(255),
  start                     datetime)
;

alter table user add constraint fk_user_organization_1 foreign key (organization_id) references organization (id) on delete restrict on update restrict;
create index ix_user_organization_1 on user (organization_id);
alter table user_browser add constraint fk_user_browser_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_browser_user_2 on user_browser (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table organization;

drop table role;

drop table user;

drop table user_browser;

SET FOREIGN_KEY_CHECKS=1;

