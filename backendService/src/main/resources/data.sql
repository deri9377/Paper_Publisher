insert into "user" (id, name) values ('c1ca46a2-6663-462f-944f-31317c51ef22', 'Bobby Flay');
insert into "user" (id, name) values ('52e867ba-e1ef-44c1-a9db-27ecf00c74de', 'Guy Fierri');
insert into "user" (id, name) values ('c47d8956-52fc-4929-93f3-67ecb2d0a36b', 'John Doe');

insert into PAPER (id, user_id, title, file) 
    values ('b7825d87-568f-46f8-ac70-8c4bce5feb59', 'c1ca46a2-6663-462f-944f-31317c51ef22', 'Pineapple on Pizza', 'Pineapplepizza.txt');
insert into PAPER (id, user_id, title, file) 
    values ('a0ee4ae1-8895-4581-9a07-3dcc64698bf9', '52e867ba-e1ef-44c1-a9db-27ecf00c74de', 'The Universe', 'The_Universe.txt');
insert into PAPER (id, user_id, title, file) 
    values ('111c36e2-f750-4bfa-b206-f8ff0944444c', 'c47d8956-52fc-4929-93f3-67ecb2d0a36b', 'The Odessey', 'The_Odyssey.txt');

insert into POST (id, paper_id, user_id) 
    values ('cd1b638e-a3f4-470a-bede-e67dccc9f956', 'b7825d87-568f-46f8-ac70-8c4bce5feb59', 'c1ca46a2-6663-462f-944f-31317c51ef22');
insert into POST (id, paper_id, user_id) 
    values ('e0398d33-307c-4b38-94e3-dc98246cf79d', '111c36e2-f750-4bfa-b206-f8ff0944444c', 'c47d8956-52fc-4929-93f3-67ecb2d0a36b');

insert into Comment (id, post_id, user_id, message)
    values ('ff7c9d20-3314-4dd2-8511-201f73d75732', 'cd1b638e-a3f4-470a-bede-e67dccc9f956', 'c1ca46a2-6663-462f-944f-31317c51ef22', 'Cool Book');

insert into Comment (id, post_id, user_id, message)
    values ('c168ca6a-80a1-4068-b2f2-8a3e9896911d', 'cd1b638e-a3f4-470a-bede-e67dccc9f956', '52e867ba-e1ef-44c1-a9db-27ecf00c74de', 'Great read');

insert into Comment (id, post_id, user_id, message)
    values ('c7c6dcd2-4b91-462a-a04c-7536b1108642', 'e0398d33-307c-4b38-94e3-dc98246cf79d', 'c47d8956-52fc-4929-93f3-67ecb2d0a36b', 'never thought about that before');