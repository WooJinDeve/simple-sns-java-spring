create table Member
(
    id int auto_increment,
    email varchar(20) not null,
    nickname varchar(20) not null,
    birthday date not null,
    created_at datetime not null,
    constraint member_id_uindex
        primary key (id)
);

create table MemberNicknameHistory
(
    id int auto_increment,
    member_id int not null,
    nickname varchar(20) not null,
    created_at datetime not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

create table Follow
(
    id int auto_increment,
    from_member_id int not null,
    to_member_id int not null,
    created_at datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);


create table POST
(
    id int auto_increment,
    member_id int not null,
    contents varchar(100) not null,
    created_date date not null,
    created_at datetime not null,
    constraint POST_id_uindex
        primary key (id)
);

create index POST__index_member_id
    on POST (memberId);

create index POST__index_created_date
    on POST (createdDate);

create table TimeLine
(
    id int auto_increment,
    member_id int not null,
    post_id int not null,
    create_at datetime not null,
    constraint Timeline_it_nindex
        primary key (id)
);

