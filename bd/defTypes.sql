drop type list_;
drop type item_;

CREATE TYPE item_ AS OBJECT ( 
  id_ VARCHAR2(8)
);
/

CREATE OR REPLACE TYPE list_ AS TABLE OF item_;
/
