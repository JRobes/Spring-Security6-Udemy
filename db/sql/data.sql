--Esta es la db por defecto que trabaja Spring. si se quiere usar el JdbcUserDetailsManager
--Si se crea una base de datos personalizada
--insert into users (username, password, enabled) VALUES
--                                                    ('admin', 'to_be_encoded', true),
--                                                    ('user', 'to_be_encoded', true);
--
--insert into authorities (username, authority) VALUES
--                                                  ('admin', 'admin'),
--                                                  ('user', 'user');

-----------------data------------------
insert into customers (email, pwd) VALUES
  ('account@debuggeandoieas.com', 'to_be_encoded'),
  ('cards@debuggeandoieas.com', 'to_be_encoded'),
  ('loans@debuggeandoieas.com', 'to_be_encoded'),
  ('balance@debuggeandoieas.com', 'to_be_encoded');

insert into roles(role_name, description, id_customer) values
            ('ROLE_ADMIN', 'cant view account endpoint', 1),
            ('ROLE_ADMIN', 'cant view account endpoint', 2),
            ('ROLE_USER', 'cant view account endpoint', 3),
            ('ROLE_USER', 'cant view account endpoint', 4);