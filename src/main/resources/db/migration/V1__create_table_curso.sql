create table curso (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    categoria varchar(100) not null,
    primary key(id)
);

insert into curso values (1, 'Spring Boot', 'Programacao');
insert into curso values (2, 'Kotlin', 'Programacao');
insert into curso values (3, 'HTML', 'Web');