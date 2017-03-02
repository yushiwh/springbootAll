insert into sys_user (id,username, password) values (1,'wyf', 'wyf');
insert into sys_user (id,username, password) values (2,'wisely', 'wisely');

insert into sys_role (id,name) values(1,'ROLE_ADMIN');
insert into sys_role(id,name) values(2,'ROLE_USER');

insert into sys_user_roles(SYS_USER_ID,ROLES_ID) values(1,1);
insert into sys_user_roles(SYS_USER_ID,ROLES_ID) values(2,2);