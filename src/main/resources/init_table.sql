create table if not exists users (username varchar(32) primary key not null,
                                    vpsname varchar(32),
                                    password varchar(32),
                                    port varchar(32),
                                    transfer varchar(32),
                                    speedthread varchar(32),
                                    speedport varchar(32),
                                    usernum varchar(10),
                                    months varchar(10));
create table if not exists vps (vpsname varchar (32) primary key not null,
                                  ip varchar (32),
                                  username varchar (32),
                                  password varchar (32));
