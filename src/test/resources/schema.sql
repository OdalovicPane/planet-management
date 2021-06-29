CREATE SCHEMA IF NOT EXISTS TESTDB;
CREATE TABLE TESTDB.PLANET
(
    id bigint NOT NULL,
    average_surface_temperature integer,
    distance_from_sun bigint NOT NULL,
    mass bigint NOT NULL,
    name character varying(255) NOT NULL,
    surface_area bigint NOT NULL,
    created timestamp without time zone,
    version timestamp without time zone
);
INSERT INTO TESTDB.PLANET(
	id, average_surface_temperature, distance_from_sun, mass, name, surface_area)
	VALUES (1, 3, 5, 6, 'test', 5);
