insert into sessies(naam, dagId, uur, sprekerid, interesses)
values ('test1',
        (select id from dagen where datum = '2024-01-01'),
        '09:00:00', (select id from sprekers where voornaam = 'testVoornaam'),
        0),
       ('test2',
        (select id from dagen where datum = '2024-01-01'),
        '10:00:00', (select id from sprekers where voornaam = 'testVoornaam'),
        0);