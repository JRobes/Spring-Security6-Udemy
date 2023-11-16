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
  ('account@debuggeandoieas.com', '$2a$10$S.XrR6nW54su0N2l3xmj2eGc2ZQEu4H1dAaNyVJ15r05eOvg7nkz2'),
  ('cards@debuggeandoieas.com', '$2a$10$S.XrR6nW54su0N2l3xmj2eGc2ZQEu4H1dAaNyVJ15r05eOvg7nkz2'),
  ('loans@debuggeandoieas.com', '$2a$10$S.XrR6nW54su0N2l3xmj2eGc2ZQEu4H1dAaNyVJ15r05eOvg7nkz2'),
  ('balance@debuggeandoieas.com', '$2a$10$S.XrR6nW54su0N2l3xmj2eGc2ZQEu4H1dAaNyVJ15r05eOvg7nkz2');

insert into roles(role_name, description, id_customer) values
            ('ROLE_ADMIN', 'cant view account endpoint', 1),
            ('ROLE_ADMIN', 'cant view account endpoint', 2),
            ('ROLE_USER', 'cant view account endpoint', 3),
            ('ROLE_USER', 'cant view account endpoint', 4);

insert into partners(
    client_id,
    client_name,
    client_secret,
    scopes,
    grant_types,
    authentication_methods,
    redirect_uri,
    redirect_uri_logout
)
values ('debuggeandoideas',
            'debuggeando ideas',
            '$2a$10$fwC9OvcPSsOKhvXSzbngne3/nzlDFT6ep66hUEcrE6aN8j0tAuxai', -- secret --
            'read,write',
            'authorization_code,refresh_token',
            'client_secret_basic,client_secret_jwt',
            'https://oauthdebugger.com/debug',
            'https://springone.io/authorized');