create table if not exists users (id int not null primary key
                                    username varchar(32),
                                    vpsname varchar(32),
                                    password varchar(32),
                                    port varchar(10),
                                    transfer varchar(10),
                                    speedthread varchar(10),
                                    speedport varchar(10),
                                    usernum varchar(10),
                                    months varchar(10));
create table if not exists vps (id int not null primary key
                                    vpsname varchar (32),
                                    ip varchar (32)
                                    username varchar (32)
                                    password varchar (32));
