insert into deelnemervoorkeursessies(deelnemerId, sessieId)
values ((select id from deelnemers where voornaam = 'testVoornaam'), (select id from sessies where naam = 'test2'));