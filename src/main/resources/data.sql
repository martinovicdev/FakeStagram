insert into users (username, password, enabled, permission)
				values ('admin', '$2a$10$eG28hqAjihXGfSyrNUji9OZEGnMNh66uQUjjIBU0OaaE4Os4u1tom', 1, 'ROLE_ADMIN'); --adminpass
insert into users (username, password, enabled, permission)
				values ('basic_user', '$2a$10$XUil1gwD8eWVxsCl4T0WmuvWr/u/eOR/colwWalMWa.4rw.BK7Unm', 1, 'ROLE_USER'); --studentpass

insert into authorities (username, authority)
				values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority)
				values ('basic_user', 'ROLE_USER');
