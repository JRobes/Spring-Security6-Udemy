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
insert into customers (email, pwd, rol) VALUES
  ('super_user@debuggeandoieas.com', 'to_be_encoded', 'admin'),
  ('basic_user@debuggeandoieas.com', 'to_be_encoded', 'user');