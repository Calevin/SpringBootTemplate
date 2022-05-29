insert into category_example
(name                           , description ) values
('Primera División de Argentina', 'Es el torneo de la primera categoría del fútbol masculino argentino, organizado desde 1893 por la Asociación del Fútbol Argentino'),
('Primera B Nacional'           , 'Cuyo nombre de fantasía es Primera Nacional —también conocida como Nacional B, B Nacional o Torneo Nacional B— es el campeonato de fútbol de segunda división de Argentina.'),
('Primera B'                    , 'También llamada Primera B Metropolitana, o informalmente B Metropolitana o B Metro) es la tercera categoría del fútbol argentino para los clubes directamente afiliados a la AFA.');

insert into entity_example
(name                         , category_example_id,  description ) values
('Club Atlético Boca Juniors' , 1 , 'Es una entidad deportiva argentina con sede en el barrio de La Boca, Buenos Aires. Fue fundado en dicho barrio el 3 de abril de 1905 por seis vecinos adolescentes hijos de italianos.' ),
('Club Atlético River Plate'  , 1 , 'Es una entidad polideportiva con sede en Buenos Aires, Argentina. Fue fundado el 25 de mayo de 1901 en el barrio de La Boca'                                                            ),
('Club Atlético Independiente', 1 , 'Conocido popularmente como Independiente o por su sigla CAI, es una entidad deportiva de Argentina de la ciudad de Avellaneda'                                                          ),
('Racing Club'                , 1 , 'Conocido como Racing Club de Avellaneda o simplemente Racing, es una entidad deportiva oriunda de Argentina, fundada el 25 de marzo de 1903 en la Ciudad de Avellaneda'                 ),
('Club Atlético Atlanta'      , 2 , 'Es una institución social, cultural y deportiva argentina, radicada en el barrio de Villa Crespo'                                                                                       ),
('Club Atlético Belgrano'     , 2 , 'Es una institución deportiva de la ciudad de Córdoba en Argentina. Fue fundado oficialmente un lunes 19 de marzo de 1905'                                                               ),
('Club Atlético San Miguel'   , 3 , 'Es un club cuya actividad de mayor referencia es el fútbol. Fue fundado el 7 de agosto de 1922. Tiene su sede social en el Partido de San Miguel.'                                      );