insert into "user" (id, name) values ('c1ca46a2-6663-462f-944f-31317c51ef22', 'Bobby Flay');
insert into "user" values ('52e867ba-e1ef-44c1-a9db-27ecf00c74de', 'Guy Fierri');

insert into PAPER (id, user_id, title, filename) 
    values ('b7825d87-568f-46f8-ac70-8c4bce5feb59', 'c1ca46a2-6663-462f-944f-31317c51ef22', 'Pineapple on Pizza', 'Pineapplepizza.txt');
insert into PAPER (id, user_id, title, filename) 
    values ('a0ee4ae1-8895-4581-9a07-3dcc64698bf9', '52e867ba-e1ef-44c1-a9db-27ecf00c74de', 'The Universe', 'The_Universe.txt');

insert into POST (id, paper_id, user_id) 
    values ('cd1b638e-a3f4-470a-bede-e67dccc9f956', 'b7825d87-568f-46f8-ac70-8c4bce5feb59', 'c1ca46a2-6663-462f-944f-31317c51ef22');

-- insert into Comment (id, post_id, user_id, message)
--     values ('da2fb95f-e6d8-456a-8e8d-6aeb59b84557', 
