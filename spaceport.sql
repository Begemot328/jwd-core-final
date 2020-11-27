drop schema if exists spaceport;

create schema if not exists spaceport;

use spaceport;

create table roles
(
    id   tinyint primary key,
    name varchar(20)
);

insert roles (id, name)
VALUES (1, 'MISSION_SPECIALIST');
insert roles (id, name)
VALUES (2, 'FLIGHT_ENGINEER');
insert roles (id, name)
VALUES (3, 'PILOT');
insert roles (id, name)
VALUES (4, 'COMMANDER');

create table ranks
(
    id   tinyint primary key,
    name varchar(20)
);

insert ranks (id, name)
VALUES (1, 'TRAINEE');
insert ranks (id, name)
VALUES (2, 'SECOND_OFFICER');
insert ranks (id, name)
VALUES (3, 'FIRST_OFFICER');
insert ranks (id, name)
VALUES (4, 'CAPTAIN');

create table results
(
    id   tinyint primary key,
    name varchar(20)
);

insert results (id, name)
VALUES (1, 'CANCELLED');
insert results (id, name)
VALUES (2, 'FAILED');
insert results (id, name)
VALUES (3, 'PLANNED');
insert results (id, name)
VALUES (4, 'IN_PROGRESS');
insert results (id, name)
VALUES (5, 'COMPLETED');

create table crewmembers
(
    id      int primary key auto_increment,
    name    varchar(30) not null,
    role    tinyint     not null,
    ranking tinyint     not null,
    ready   boolean default true,
    foreign key (ranking) references ranks (id),
    foreign key (role) references roles (id)
);
#4,Davey Bentley,2;1,Zoe Day,1;3,Petra Potter,4;3,Hafsa Harding,2;2,Frazer Carr,4;
# 4,Sara Barnes,3;
insert crewmembers (role, name, ranking)
VALUES (4, 'Davey Bentley', 2);
insert crewmembers (role, name, ranking)
VALUES (1, 'Zoe Day', 1);
insert crewmembers (role, name, ranking)
VALUES (3, 'Petra Potter', 4);
insert crewmembers (role, name, ranking)
VALUES (3, 'Hafsa Harding', 2);
insert crewmembers (role, name, ranking)
VALUES (2, 'Frazer Carr', 4);
insert crewmembers (role, name, ranking)
VALUES (4, 'Sara Barnes', 3);

create table spaceships
(
    id       int primary key auto_increment,
    name     varchar(30) not null,
    distance long        not null,
    ready    bool        not null default true
);


#Challenger;201117;{1:5,2:9,3:3,4:3}
#Intelligence;758717;{1:7,2:8,3:4,4:1}
#Philadelphia;4870;{1:4,2:8,3:4,4:2}
#Sparta;754471;{1:7,2:7,3:2,4:1}
#Guardian;910740;{1:8,2:9,3:2,4:1}

insert spaceships (name, distance)
VALUES ('Challenger', 201117);
insert spaceships (name, distance)
VALUES ('Intelligence', 758717);
insert spaceships (name, distance)
VALUES ('Philadelphia', 4870);
insert spaceships (name, distance)
VALUES ('Sparta', 754471);
insert spaceships (name, distance)
VALUES ('Guardian', 910740);



create table spaceship_crews
(
    ship_id  int,
    ranking  tinyint not null,
    quantity int     not null,
    primary key (ship_id, ranking),
    foreign key (ranking) references ranks (id),
    foreign key (ship_id) references spaceships (id)
);

#Challenger;201117;{1:5,2:9,3:3,4:3}
#Intelligence;758717;{1:7,2:8,3:4,4:1}
#Philadelphia;4870;{1:4,2:8,3:4,4:2}
#Sparta;754471;{1:7,2:7,3:2,4:1}
#Guardian;910740;{1:8,2:9,3:2,4:1}

insert spaceship_crews (ship_id, ranking, quantity)
VALUES (1, 1, 5);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (1, 2, 9);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (1, 3, 3);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (1, 4, 3);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (2, 1, 7);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (2, 2, 8);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (2, 3, 3);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (2, 4, 4);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (3, 1, 4);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (3, 2, 8);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (3, 3, 4);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (3, 4, 2);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (4, 1, 1); #changed
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (4, 2, 1); #changed
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (4, 3, 1); #changed
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (4, 4, 1); #changed
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (5, 1, 8);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (5, 2, 9);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (5, 3, 2);
insert spaceship_crews (ship_id, ranking, quantity)
VALUES (5, 4, 1);

create table missions
(
    id          int primary key auto_increment,
    name        varchar(30) not null,
    start_date  date        not null,
    finish_date date        not null,
    ship_id     int         not null,
    distance    long        not null,
    result      tinyint     not null,
    foreign key (result) references results (id),
    foreign key (ship_id) references spaceships (id)
);

create table mission_assignments
(
    id_mission int not null,
    id_member  int not null,
    primary key (id_mission, id_member),
    foreign key (id_member) references crewmembers (id),
    foreign key (id_mission) references missions (id)
);

insert missions (name, start_date, finish_date, ship_id, distance, result)
VALUES ('Mars', '2022-11-27', '2026-02-12', 4, 93387000, 3);

#Sparta;754471;{1:7,2:7,3:2,4:1}
#4,Davey Bentley,2;1,Zoe Day,1;3,Petra Potter,4;3,Hafsa Harding,2;2,Frazer Carr,4;4,Sara Barnes,3;
insert mission_assignments (id_mission, id_member)
VALUES (1, 2);
insert mission_assignments (id_mission, id_member)
VALUES (1, 1);
insert mission_assignments (id_mission, id_member)
VALUES (1, 6);
insert mission_assignments (id_mission, id_member)
VALUES (1, 5);

update crewmembers
set ready = false
where id = 1;
update crewmembers
set ready = false
where id = 2;
update crewmembers
set ready = false
where id = 5;
update crewmembers
set ready = false
where id = 6;
update spaceships
set ready = false
where id = 4;

drop view if exists all_members;
create view all_members as
select c.name,
       ranks.name                                                                                 as rankname,
       roles.name                                                                                 as rolename,
       if(c.ready, 'ready', concat('on mission: ', missions.name, ' on ship: ', spaceships.name)) as status
from crewmembers as c
         join ranks on c.ranking = ranks.id
         join roles on c.role = roles.id
         left join mission_assignments on c.id = mission_assignments.id_member
         left join missions
                   on mission_assignments.id_mission = missions.id and (missions.result = 4 or missions.result = 3)
         left join spaceships on missions.ship_id = spaceships.id;

drop view if exists all_ships;
create view all_ships as
select s.name, s.distance, if(s.ready, 'ready', concat('on mission: ', missions.name))
from spaceships as s
         left join missions on missions.ship_id = s.id
    and (missions.result = 4 or missions.result = 3);

drop view if exists all_missions;
create view all_missions as
select m.name, m.start_date, m.finish_date, m.distance, results.name as result
from missions as m join results on m.result = results.id;



