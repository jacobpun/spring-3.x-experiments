create or replace TYPE actor_type AS OBJECT (id NUMBER(10), name VARCHAR2(50), age NUMBER, movies movie_array_type);
create or replace TYPE actor_array_type AS VARRAY(200) of actor_type;
create or replace TYPE actor_id_array AS VARRAY(20) OF NUMBER;
create or replace TYPE actor_name_array AS VARRAY(20) OF VARCHAR2(50);
create or replace TYPE movie_type AS OBJECT (id NUMBER(10), name VARCHAR2(50));
create or replace TYPE movie_array_type AS VARRAY(200) of movie_type;

CREATE TABLE ACTOR  (ID NUMBER, NAME VARCHAR2(50), AGE NUMBER);
CREATE TABLE MOVIE(ID NUMBER,NAME VARCHAR2(50),PRIMARY KEY (ID));
CREATE TABLE ACTOR_MOVIE (MOVIE_ID NUMBER, ACTOR_ID NUMBER);

/***Add Actor***/
create or replace PROCEDURE add_actor (in_actor IN actor_type)
AS
movie MOVIE_TYPE;
BEGIN
  INSERT into actor (id, name, age) VALUES(in_actor.id, in_actor.name, in_actor.age);
    FOR i IN 1..in_actor.movies.count LOOP
    movie:=in_actor.movies(i);
    INSERT into MOVIE (id, name) VALUES(movie.id, movie.name);
  END LOOP;
END;

/***Add Actor Array**/
create or replace PROCEDURE add_actor_array (in_actor_array IN ACTOR_ARRAY_TYPE)
AS
in_actor ACTOR_TYPE;
BEGIN
  dbms_output.put_line(in_actor_array.count);
  FOR i IN 1..in_actor_array.count LOOP
    in_actor:=in_actor_array(i);
    INSERT into actor (id, name, age) VALUES(in_actor.id, in_actor.name, in_actor.age);
  END LOOP;
END;

/***Delete Actor **/
create or replace PROCEDURE delete_actors (in_actor_ids IN actor_id_array)
AS
BEGIN
  FOR i IN 1..in_actor_ids.count loop
    DELETE FROM actor WHERE id = in_actor_ids(i);
  END LOOP;
END;

/*** Delete Actor by Name ***/
create or replace PROCEDURE delete_actors_by_name (in_actor_namess IN actor_name_array, out_delete_count OUT NUMBER)
AS
BEGIN
  
  FOR i IN 1..in_actor_namess.count loop
    DELETE FROM actor WHERE name = in_actor_namess(i);
  END LOOP;
END;